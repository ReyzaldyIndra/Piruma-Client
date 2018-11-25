package app.piruma_java.selectroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import app.piruma_java.model.SelectRoom;
import app.piruma_java.model.SelectRoom2;
import app.piruma_java.network.VolleyNetwork;

public class SelectRoomActivity extends AppCompatActivity{
private RecyclerView rvListRoomDept;
private List<SelectRoom> selectRoomList = new ArrayList<>();
private List<SelectRoom2> selectRoom2List = new ArrayList<>();
private String TAG = SelectRoomActivity.class.getSimpleName();
private SelectRoomAdapter selectRoomAdapter;


private Button btnBookRoom;
private TextView txNamaDept, txNamaFak, txJumlah, txFasilitas, txJadwal, txKapasitas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);
        rvListRoomDept = findViewById(R.id.rv_list_room_dept);
        btnBookRoom = findViewById(R.id.sel_btn_book_room);
        txNamaDept = findViewById(R.id.sel_tx_nama_dept);
        txNamaFak = findViewById(R.id.sel_tx_nama_fak);
        txJumlah = findViewById(R.id.sel_tx_jumlah);
        txFasilitas = findViewById(R.id.sel_tx_fasilitas);
        txJadwal = findViewById(R.id.sel_tx_jadwal);
        txKapasitas = findViewById(R.id.sel_tx_kapasitas);

        selectRoomAdapter = new SelectRoomAdapter(SelectRoomActivity.this, selectRoomList);
        selectRoomAdapter.setOnItemClickedListener(new SelectRoomAdapter.itemClickedListener() {
            @Override
            public void onItemClickedListener(String fasilitas, String kapasitas) {
                Toast.makeText(SelectRoomActivity.this, "cobo", Toast.LENGTH_SHORT).show();
                getList_2();
            }
        });

        getList();

    }

    void getList(){
        String url = "https://piruma.au-syd.mybluemix.net/api/ruangan/listroom";
        JSONObject body = new JSONObject();

        try{
            body.put("id_departemen","Dept-1");
            body.put("kapasitas", "10");

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
                        txNamaDept.setText(selectRoom.getDept());
                    }


                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SelectRoomActivity.this, 1);
                    rvListRoomDept.setLayoutManager(layoutManager);
                    rvListRoomDept.setItemAnimator(new DefaultItemAnimator());
                    rvListRoomDept.setAdapter(selectRoomAdapter);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        }, this);
    }

    void getList_2(){
        final String url_2 = "https://piruma.au-syd.mybluemix.net/api/ruangan/schedule";
        JSONObject body_2 = new JSONObject();

        try{
            body_2.put("id_ruangan", "");
            body_2.put("timestamp_start", "0");
            body_2.put("timestamp_end", "9999");

        }catch (JSONException e){
            e.printStackTrace();
        }

        final VolleyNetwork request_2 = new VolleyNetwork (url_2, body_2, TAG);
        request_2.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try{
                    JSONObject detail = result.getJSONObject("detail");
                    String fasilitas = detail.getString("fasilitas");
                    String kapasitas = result.getString("kapasitas");

                    final SelectRoom2 selectRoom2 = new SelectRoom2(fasilitas, kapasitas);
                    selectRoom2List.add(selectRoom2);
                    txFasilitas.setText(selectRoom2.getFasilitas());
                    txKapasitas.setText(selectRoom2.getKapasitas());

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(VolleyError error) {

            }
        }, this);
    }



}
