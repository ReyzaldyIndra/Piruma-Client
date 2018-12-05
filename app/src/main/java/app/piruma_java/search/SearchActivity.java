package app.piruma_java.search;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.piruma_java.R;
import app.piruma_java.model.SelectRoom;
import app.piruma_java.selectroom.SelectRoomActivity;
import app.piruma_java.model.RoomAvail;
import app.piruma_java.network.VolleyNetwork;
import app.piruma_java.selectroom.SelectRoomAdapter;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView searchRecyclerView;
    private List<RoomAvail> roomAvails = new ArrayList<>();
    private String TAG = SearchActivity.class.getSimpleName();
    private String kapasitas;
    private SearchAdapter searchAdapter;



    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        b = getIntent().getExtras();

        searchRecyclerView = findViewById(R.id.rv_room_avail);


        String kapasitas = b.getCharSequence("kapasitas").toString();
        Long timestamp_start = b.getLong("timestamp_start");
        Long timestamp_end = b.getLong("timestamp_end");
        Toast.makeText(this, timestamp_start.toString(), Toast.LENGTH_SHORT).show();



        getSearchList(kapasitas,timestamp_start,timestamp_end);




    }


    void getSearchList(String kapasitas, Long timestamp_start, Long timestamp_end){
        final String link = "https://piruma.au-syd.mybluemix.net/api/ruangan/search";
        JSONObject body = new JSONObject();
        JSONObject timeStamp = new JSONObject();

        try {
            body.put("kapasitas", kapasitas);
            timeStamp.put("timestamp_start",timestamp_start.toString());
            timeStamp.put("timestamp_end",timestamp_end.toString());
            body.put("TimeStamp",timeStamp);

        } catch (JSONException e){
            e.printStackTrace();
        }

        final VolleyNetwork request = new VolleyNetwork(link, body, TAG );
        request.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try{
                    Log.d("Result :",result.toString());
                    JSONArray array = result.getJSONArray("result");
                    for(int i = 0;i<array.length();i++) {
                    JSONObject search = array.getJSONObject(i);
                    String namaDepartemen = search.getString("departemen");
                    String namaFakultas = "Teknik";
                    String jumlah = search.getString("count");
                    RoomAvail roomAvail = new RoomAvail(namaDepartemen, namaFakultas, jumlah);
                    roomAvails.add(roomAvail);
                    }
                    searchAdapter = new SearchAdapter(SearchActivity.this, roomAvails, item -> {



                    });

                    searchAdapter = new SearchAdapter(SearchActivity.this, roomAvails, new SearchAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RoomAvail item) {
                            Intent intent = new Intent(SearchActivity.this, SelectRoomActivity.class);
                            intent.putExtra("TimeStamp", timeStamp.toString());
                            intent.putExtra("kapasitas", kapasitas);
                            intent.putExtra("fakultas", item.getFakultas());
                            intent.putExtra("departemen", item.getDepartemen());
                            intent.putExtra("count", item.getJumlah());
                            startActivity(intent);
                        }
                    });

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
