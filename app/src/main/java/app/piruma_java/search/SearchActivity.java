package app.piruma_java.search;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.piruma_java.R;
import app.piruma_java.model.RoomAvail;
import app.piruma_java.network.VolleyNetwork;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView searchRecyclerView;
    private List<RoomAvail> roomAvails = new ArrayList<>();
    private String TAG = SearchActivity.class.getSimpleName();
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchRecyclerView = findViewById(R.id.rv_room_avail);

    }

    void getSearchList(){
        final String url = "https://piruma.au-syd.mybluemix.net/api/ruangan/search";
        JSONObject body = new JSONObject();
        JSONObject timeStamp = new JSONObject();

        try {
            body.put("kapasitas", "30");
            timeStamp.put("timestamp_start","1");
            timeStamp.put("timestamp_end","9999");
            body.put("TimeStamp",timeStamp);
//kontol
        } catch (JSONException e){
            e.printStackTrace();
        }

        final VolleyNetwork request = new VolleyNetwork(url, body, TAG );
        request.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try{
                    JSONArray array = result.getJSONArray("result");
                    for(int i = 0;i<array.length();i++) {
                    JSONObject search = array.getJSONObject(i);
                    String namaDepartemen = search.getString("departemen");
                    String namaFakultas = search.getString("Teknik");
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
