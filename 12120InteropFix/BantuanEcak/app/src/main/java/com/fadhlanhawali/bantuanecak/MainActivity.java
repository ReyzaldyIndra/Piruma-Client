package com.fadhlanhawali.bantuanecak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fadhlanhawali.bantuanecak.Network.VolleyNetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private List<Departemen> departemenList = new ArrayList<>();
    private DepartemenAdapter departemenAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        getListRuangan();
    }

    void getListRuangan(){
        JSONObject body = new JSONObject();
        JSONObject timeStamp = new JSONObject();

        try {

            body.put("kapasitas", "30");
            timeStamp.put("timestamp_start","1");
            timeStamp.put("timestamp_end","9999");
            body.put("TimeStamp",timeStamp);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "https://dteti.au-syd.mybluemix.net/api/search";

        //POST REQUEST
        VolleyNetwork request = new VolleyNetwork(url, body, TAG);
        request.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray array = result.getJSONArray("result");
                    for(int i = 0;i<array.length();i++){
                        JSONObject detail = array.getJSONObject(i);
                        String namaDepartemen = detail.getString("departemen");
                        String namaFakultas = "Fakultas Teknik";
                        String jumlah = detail.getString("count");
                        Departemen ruangan = new Departemen(namaDepartemen,namaFakultas,jumlah);
                        departemenList.add(ruangan);

                    }

                    departemenAdapter = new DepartemenAdapter(MainActivity.this,departemenList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 1);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(departemenAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VolleyError error) {

            }
        },this);


    }
}
