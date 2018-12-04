package app.piruma_java.selectroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import app.piruma_java.R;
import app.piruma_java.ScheduleActivity;
import app.piruma_java.model.SelectRoom;
import app.piruma_java.model.SelectRoom2;
import app.piruma_java.network.VolleyNetwork;
import app.piruma_java.search.SearchActivity;
import app.piruma_java.FormActivity;

public class SelectRoomActivity extends AppCompatActivity{
private RecyclerView rvListRoomDept;
private List<SelectRoom> selectRoomList = new ArrayList<>();
List<SelectRoom2> selectRoom2List = new ArrayList<>();
private String TAG = SelectRoomActivity.class.getSimpleName();
private SelectRoomAdapter selectRoomAdapter;
private SelectRoomAdapter2 selectRoomAdapter2;
Bundle b;

private Button btnBookRoom;
private TextView txNamaDept, txNamaFak, txJumlah, txFasilitas, txJadwal_Start, txJadwal_End, txJadwal_Keterangan, txKapasitas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);
        b = getIntent().getExtras();
        rvListRoomDept = findViewById(R.id.rv_list_room_dept);
        txNamaDept = findViewById(R.id.sel_tx_nama_dept);
        txNamaFak = findViewById(R.id.sel_tx_nama_fak);
        txJumlah = findViewById(R.id.sel_tx_jumlah);
        txFasilitas = findViewById(R.id.sel_tx_fasilitas);
        txJadwal_Start = findViewById(R.id.tx_jadwal_start);
        txJadwal_End = findViewById(R.id.tx_jadwal_end);
        txJadwal_Keterangan = findViewById(R.id.tx_jadwal_keterangan);
        txKapasitas = findViewById(R.id.sel_tx_kapasitas);
        btnBookRoom = findViewById(R.id.sel_btn_book_room);


//        String id_departemen = b.getCharSequence("id_departemen").toString();
        String departemen = b.getCharSequence("departemen").toString();
        String kapasitas = b.getCharSequence("kapasitas").toString();
        Long time = b.getLong("timestamp_start");
        Long timestamp_end = b.getLong("timestamp_end");
        String jumlah = b.getCharSequence("count").toString();
        String fakultas = b.getCharSequence("fakultas").toString();





//        selectRoomAdapter.setOnItemClickedListener(new SelectRoomAdapter.itemClickedListener() {
//            @Override
//            public void onItemClickedListener(String fasilitas, String kapasitas) {
//                Toast.makeText(SelectRoomActivity.this, "cobo", Toast.LENGTH_SHORT).show();
//                getList_2();
//            }
//        });
getList(departemen, kapasitas, time, timestamp_end, jumlah, fakultas);

    }

    void getList(String departemen, String kapasitas, Long time, Long timestamp_end, String jumlah, String fakultas){
        String url = "https://piruma.au-syd.mybluemix.net/api/ruangan/listroom";
        JSONObject body = new JSONObject();
//        JSONObject timeStamp = new JSONObject();

        try{
            body.put("id_departemen",departemen);
//            timeStamp.put("timestamp_start", timestamp_start.toString());
//            timeStamp.put("timestamp_end", timestamp_end.toString());
//            body.put("TimeStamp", timeStamp);
            body.put("kapasitas", kapasitas);

        } catch (JSONException e){
            e.printStackTrace();
        }

        final VolleyNetwork request = new VolleyNetwork(url, body, TAG);
        request.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try{
                    JSONArray array = result.getJSONArray("result");
                    for (int i=0; i<array.length(); i++){
                        JSONObject list = array.getJSONObject(i);
                        String namaRuang = list.getString("nama_ruangan");
                        String idRuangan = list.getString("id_ruangan");
                        String namaDept = list.getString("id_departemen");


                        SelectRoom selectRoom = new SelectRoom(namaRuang, idRuangan, namaDept);
                        selectRoomList.add(selectRoom);
                        Toast.makeText(SelectRoomActivity.this, idRuangan, Toast.LENGTH_SHORT).show();
                        txNamaDept.setText(selectRoom.getDept());
                        txNamaFak.setText(fakultas);
                        txJumlah.setText(jumlah);
                        btnBookRoom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                btnBookRoom.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(SelectRoomActivity.this, FormActivity.class);
                                        intent.putExtra("id_ruangan", idRuangan);
                                        intent.putExtra("kapasitas", kapasitas);
                                        intent.putExtra("timestamp_start", time);
                                        intent.putExtra("nama_ruangan", namaRuang);

                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                    }


                    selectRoomAdapter = new SelectRoomAdapter(SelectRoomActivity.this, selectRoomList, new SelectRoomAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(SelectRoom item) {
                            Toast.makeText(SelectRoomActivity.this, item.getId_ruangan(), Toast.LENGTH_SHORT).show();
                            getList_2(item.getId_ruangan(),b.getLong("timestamp_start"),b.getLong("timestamp_end"));
                        }
                    });
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SelectRoomActivity.this, 1);
                    rvListRoomDept.setLayoutManager(layoutManager);
                    rvListRoomDept.setItemAnimator(new DefaultItemAnimator());
                    rvListRoomDept.setAdapter(selectRoomAdapter);

                }catch (JSONException e){
                    e.printStackTrace();
                    Log.d("Ruangan", result.toString());
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        }, this);
    }

    void getList_2(String id_ruangan, Long timestamp_start, Long timestamp_end){
        final String url_2 = "https://piruma.au-syd.mybluemix.net/api/ruangan/schedule";
        JSONObject body_2 = new JSONObject();
        JSONObject timestamp = new JSONObject();
        try{
            body_2.put("id_ruangan", id_ruangan);
            timestamp.put("timestamp_start", String.valueOf(timestamp_start));
            timestamp.put("timestamp_end", String.valueOf(timestamp_end));
            body_2.put("TimeStamp",timestamp);

        }catch (JSONException e){
            e.printStackTrace();
        }

        final VolleyNetwork request_2 = new VolleyNetwork (url_2, body_2, TAG);
        request_2.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try{
                    Log.d("Jadwal : ",result.toString());
                    JSONObject detail = result.getJSONObject("detail");
                    String fasilitas = detail.getString("fasilitas");
                    String kapasitas = detail.getString("kapasitas");
                    txFasilitas.setText(fasilitas);
                    txKapasitas.setText(kapasitas);

                    JSONArray jadwal = result.getJSONArray("result");
                    for(int i = 0;i<jadwal.length();i++){
                        JSONObject deskripsi = jadwal.getJSONObject(i);
                        String keterangan = deskripsi.getString("keterangan");
                        String timestamp_start = deskripsi.getString("timestamp_start");
                        String timestamp_end = deskripsi.getString("timestamp_end");
                        SelectRoom2 selectRoom2 = new SelectRoom2(keterangan, timestamp_start, timestamp_end);
                        selectRoom2List.add(selectRoom2);
                        txJadwal_Start.setText(selectRoom2.getJadwal_start());
                        txJadwal_End.setText(selectRoom2.getJadwal_end());
                        txJadwal_Keterangan.setText(selectRoom2.getJadwal_keterangan());
                    }

                }catch (JSONException e){
                    e.printStackTrace();

                }

            }

            @Override
            public void onError(VolleyError error) {
                Log.d("Jadwal : ",error.toString());
            }
        }, this);
    }



}
