package app.piruma_java.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.piruma_java.R;
import app.piruma_java.model.RoomItem;
import app.piruma_java.network.VolleyNetwork;
import app.piruma_java.search.SearchActivity;

public class MainActivity extends AppCompatActivity {
    private RecyclerView RoomRecyclerView;
    private List<RoomItem> roomItemList = new ArrayList<>();
    private String TAG = MainActivity.class.getSimpleName();
    private MainAdapter mainAdapter;
    private FloatingActionButton btnAddRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RoomRecyclerView = findViewById(R.id.main_recycler_view);
        btnAddRoom = findViewById(R.id.btn_add_room);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        getRoom();
    }

    void getRoom() {
        final String url = "https://dteti.au-syd.mybluemix.net/api/ruang/list";
        JSONObject body = new JSONObject();
//        JSONObject timeStamp = new JSONObject();

        try {
            body.put("result", "nama_ruangan");
            body.put("result", "id_departemen");
//            timeStamp.put("timestamp_start","1");
//            timeStamp.put("timestamp_end","9999");
//            body.put("TimeStamp",timeStamp);
        } catch (JSONException e){
            e.printStackTrace();
        }

        //POST Request
        final VolleyNetwork request = new VolleyNetwork(url, body, TAG);
        request.getRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray array = result.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject detail = array.getJSONObject(i);
                        String namaRuang = detail.getString("nama_ruangan");
                        String namaDept = detail.getString("id_departemen");
                        RoomItem room = new RoomItem(namaRuang, namaDept);
                        roomItemList.add(room);
                    }
                    mainAdapter = new MainAdapter(MainActivity.this, roomItemList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 1);
                    RoomRecyclerView.setLayoutManager(mLayoutManager);
                    RoomRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    RoomRecyclerView.setAdapter(mainAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {
            }
        }, this);
    }
}

