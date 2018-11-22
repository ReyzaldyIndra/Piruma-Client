package app.piruma_java.search;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.piruma_java.R;
import app.piruma_java.selectroom.SelectRoomActivity;
import app.piruma_java.model.RoomAvail;
import app.piruma_java.network.VolleyNetwork;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView searchRecyclerView;
    private List<RoomAvail> roomAvails = new ArrayList<>();
    private String TAG = SearchActivity.class.getSimpleName();
    private SearchAdapter searchAdapter;
    private Button btnPindah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchRecyclerView = findViewById(R.id.rv_room_avail);
        btnPindah = findViewById(R.id.btn_pindah);
        btnPindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, SelectRoomActivity.class);
                startActivity(intent);
            }
        });
    getSearchList();
    }

    void getSearchList(){
        final String link = "https://piruma.au-syd.mybluemix.net/api/ruangan/search";
        JSONObject body = new JSONObject();
//        JSONObject timeStamp = new JSONObject();

        try {
            body.put("result", "count");
            body.put("result", "departemen");
//            timeStamp.put("timestamp_start","1");
//            timeStamp.put("timestamp_end","9999");
//            body.put("TimeStamp",timeStamp);

        } catch (JSONException e){
            e.printStackTrace();
        }

        final VolleyNetwork request = new VolleyNetwork(link, body, TAG );
        request.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try{
                    JSONArray array = result.getJSONArray("result");
                    for(int i = 0;i<array.length();i++) {
                    JSONObject search = array.getJSONObject(i);
                    String namaDepartemen = search.getString("departemen");
                    String namaFakultas = "Teknik";
                    String jumlah = search.getString("count");
                    RoomAvail roomAvail = new RoomAvail(namaDepartemen, namaFakultas, jumlah);
                    roomAvails.add(roomAvail);
                    }
                    searchAdapter = new SearchAdapter(SearchActivity.this, roomAvails);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SearchActivity.this, 1);
                    searchRecyclerView.setLayoutManager(layoutManager);
                    searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    searchRecyclerView.setAdapter(searchAdapter);

                    }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        }, this);
    }
}
