package app.piruma_java.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.piruma_java.R;
import app.piruma_java.ScheduleActivity;
import app.piruma_java.SessionManager;
import app.piruma_java.model.RoomItem;
import app.piruma_java.ConvertDate;
import app.piruma_java.main.MainAdapter;
import app.piruma_java.network.VolleyNetwork;
import app.piruma_java.search.SearchActivity;

public class MainActivity extends AppCompatActivity {
    private RecyclerView RoomRecyclerView;
    private List<RoomItem> roomItemList = new ArrayList<>();
    private String TAG = MainActivity.class.getSimpleName();
    private MainAdapter mainAdapter;
    SessionManager session;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    private FloatingActionButton btnAddRoom;
    TextView username, departemen,ruangan,date,time,penanggung_jawab,jurusan,keperluan,telepon,status_peminjaman,status_surat;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.txtUserName);
        session = new SessionManager(this);
        RoomRecyclerView = findViewById(R.id.main_recycler_view);
        getData();
        btnAddRoom = findViewById(R.id.btn_add_room);
        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

    }


    void getData(){

        final HashMap<String, String> user = session.getUserDetails();
        String url = "https://piruma.au-syd.mybluemix.net/api/list_history";
        JSONObject data = new JSONObject();
        VolleyNetwork request = new VolleyNetwork(url,data,TAG);
        request.setToken(user.get(SessionManager.KEY_TOKEN));

        request.getRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    JSONArray detail = result.getJSONArray("result");
                    for (int i = 0;i <detail.length();i++){
                        JSONObject deskripsi = detail.getJSONObject(i);
                        String id_pemesanan = deskripsi.getString("id_pemesanan");
                        String ruangan = deskripsi.getString("ruangan");
                        String departemen = deskripsi.getString("departemen");
                        String penanggung_jawab = deskripsi.getString("penanggung_jawab");
                        String telepon = deskripsi.getString("telepon");
                        String keterangan = deskripsi.getString("keterangan");
                        Long timestamp_start = Long.valueOf(deskripsi.getString("timestamp_start"));
                        Long timestamp_end = Long.valueOf(deskripsi.getString("timestap_end"));
                        Toast.makeText(MainActivity.this, "History", Toast.LENGTH_SHORT).show();

                        ConvertDate convertDate = new ConvertDate();
                        String time_start = convertDate.convertTime(timestamp_start);
                        String time_end = convertDate.convertTime(timestamp_end);
                        String time = time_start + " - " + time_end;
                        username.setText(penanggung_jawab);
                        Log.d("History", ruangan);
                        RoomItem history = new RoomItem(id_pemesanan,ruangan,departemen,penanggung_jawab,telepon,keterangan,convertDate.convertComplete(timestamp_start),time);
                        roomItemList.add(history);
                    }
                    mainAdapter = new MainAdapter(getApplicationContext(), roomItemList, item ->{
                        Toast.makeText(MainActivity.this, item.getName_room(), Toast.LENGTH_SHORT).show();
                         DialogForm(item.getId_pemesanan(),item.getName_dept(),item.getName_room(),item.getDate(),item.getTime(),item.getPenanggung_jawab(),"Teknologi Informasi",item.getKeterangan(),item.getTelepon());
                    });
                    GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    RoomRecyclerView.setLayoutManager(mLayoutManager);
                    RoomRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    RoomRecyclerView.setAdapter(mainAdapter);
//                });
                }catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("MAIN", e.toString());
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(VolleyError error) {

            }
        },this);

    }

        private void DialogForm(String idPemesanan,String strDepartemen,String strRuangan,String strDate,String strTime, String strNama,String strJurusan,String strKeperluan,String strTelepon) {
            dialog = new AlertDialog.Builder(MainActivity.this,R.style.MyDialogTheme);
            inflater = getLayoutInflater();
            dialogView = inflater.inflate(R.layout.dialog_history, null);
            dialog.setView(dialogView);
            departemen = dialogView.findViewById(R.id.txtDepartemen);
            ruangan = dialogView.findViewById(R.id.txtRuangan);
            date = dialogView.findViewById(R.id.txtDate);
            time = dialogView.findViewById(R.id.txtTime);
            penanggung_jawab = dialogView.findViewById(R.id.txtPenanggungJawab);
            jurusan = dialogView.findViewById(R.id.txtJurusan);
            keperluan = dialogView.findViewById(R.id.txtKeterangan);
            telepon = dialogView.findViewById(R.id.txtTelepon);
            status_peminjaman = dialogView.findViewById(R.id.txtStatusPeminjaman);
            status_surat = dialogView.findViewById(R.id.txtStatusSurat);

            departemen.setText(strDepartemen);
            ruangan.setText(strRuangan);
            date.setText(strDate);
            time.setText(strTime);
            penanggung_jawab.setText(strNama);
            jurusan.setText(strJurusan);
            keperluan.setText(strKeperluan);
            telepon.setText(strTelepon);

            final HashMap<String, String> user = session.getUserDetails();
            String url = " https://piruma.au-syd.mybluemix.net/api/order/check?idPemesanan=" + idPemesanan;
            JSONObject data = new JSONObject();
            VolleyNetwork request = new VolleyNetwork(url,data,TAG);
            request.setToken(user.get(SessionManager.KEY_TOKEN));

            request.postRequest(new VolleyNetwork.VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                        Boolean status_pinjam = result.getBoolean("status_peminjaman");
                        if(status_pinjam == true){
                            status_peminjaman.setText("ACC");
                        }else{
                            status_peminjaman.setText("Ditolak");
                        }

                        status_surat.setText(result.getString("status_surat"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(VolleyError error) {

                }
            },this);

            dialog.setCancelable(true);

            dialog.show();
        }
    }


