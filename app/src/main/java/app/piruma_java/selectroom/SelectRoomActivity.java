package app.piruma_java.selectroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.piruma_java.R;
import app.piruma_java.model.SelectRoom;
import app.piruma_java.network.VolleyNetwork;

public class SelectRoomActivity extends AppCompatActivity{
private RecyclerView rvListRoomDept;
private List<SelectRoom> selectRoomList = new ArrayList<>();
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


        getList();
    }

    void getList(){
        final String url = "https://piruma.au-syd.mybluemix.net/api/ruangan/listroom";

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
                        String kapasitas = list.getString("kapasitas");
                        String fasilitas = list.getString("fasilitas");
                        SelectRoom selectRoom = new SelectRoom(namaRuang, idRuangan, namaDept, kapasitas, fasilitas);
                        selectRoomList.add(selectRoom);


                    }

                    selectRoomAdapter = new SelectRoomAdapter(SelectRoomActivity.this, selectRoomList);
                    selectRoomAdapter.setOnItemClickedListener(new SelectRoomAdapter.itemClickedListener() {
                        @Override
                        public void onItemClickedListener(String fasilitas, String Jadwal) {
                            Toast.makeText(SelectRoomActivity.this, "cobo", Toast.LENGTH_SHORT).show();
                        }
                    });
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

}
