package app.piruma_java;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.load.model.ByteArrayLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import app.piruma_java.Activity.LoginActivity;
import app.piruma_java.Activity.SignupActivity;
import app.piruma_java.selectroom.*;
import app.piruma_java.network.VolleyNetwork;

public class FormActivity extends AppCompatActivity {
    private ImageButton arrow_back;
    private TextView txOrderSchedule, txOrderRoom;
    private EditText etPic, etJurusan, etEmail, etNoHP, etKeterangan, etTimeStart, etTimeEnd;
    private String TAG = FormActivity.class.getSimpleName();
    private Button btnPinjam;
    SessionManager session;

    Bundle b;
    ConvertDate convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        convert = new ConvertDate();
        session = new SessionManager(this);
        b = getIntent().getExtras();
        String kapasitas = b.getCharSequence("kapasitas").toString();
        Long timestamp_start = b.getLong("timestamp_start");
        Long timeStamp = b.getLong("TimeStamp");
        Toast.makeText(this, timestamp_start.toString(), Toast.LENGTH_SHORT).show();
        Long timestamp_end = b.getLong("timestamp_end");
        String idRuangan = b.getCharSequence("id_ruangan").toString();
        String namaRuang = b.getCharSequence("nama_ruangan").toString();
//        Toast.makeText(this, timeStamp.toString(), Toast.LENGTH_SHORT).show();



        txOrderSchedule = findViewById(R.id.tx_order_schedule);
        txOrderSchedule.setText(convert.convertComplete(timestamp_start*1000));
        txOrderRoom = findViewById(R.id.tx_order_room);
        txOrderRoom.setText(namaRuang);
        etPic = findViewById(R.id.et_pic);
        etJurusan = findViewById(R.id.et_jurusan);
        etEmail = findViewById(R.id.et_email);
        etNoHP =  findViewById(R.id.et_no_hp);
        etKeterangan = findViewById(R.id.et_keterangan);

        etTimeStart = findViewById(R.id.et_jadwal1);
        etTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mStartTime =  Calendar.getInstance();
                int hour_start = mStartTime.get(Calendar.HOUR_OF_DAY);
                int minute_start = mStartTime.get(Calendar.MINUTE);

                TimePickerDialog starttimePicker;
                starttimePicker = new TimePickerDialog(FormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        etTimeStart.setText(hour + ":" + minute);
                        mStartTime.set(Calendar.HOUR_OF_DAY, hour);
                        mStartTime.set(Calendar.MINUTE, minute);
                        long p = (hour*3600) + minute*60;
                        long result_start = timestamp_start + p;


                        Log.d("result_start:", String.valueOf(result_start));
                    }
                }, hour_start, minute_start, true);
                starttimePicker.setTitle("Select Time");
                starttimePicker.show();
            }
        });
        etTimeEnd = findViewById(R.id.et_jadwal2);
        etTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mEndTime = Calendar.getInstance();
                int hour_end = mEndTime.get(Calendar.HOUR_OF_DAY);
                int minute_end = mEndTime.get(Calendar.MINUTE);
                TimePickerDialog endtimePicker;
                endtimePicker = new TimePickerDialog(FormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        etTimeEnd.setText(hour + ":" + minute);
                        mEndTime.set(Calendar.HOUR_OF_DAY, hour);
                        mEndTime.set(Calendar.MINUTE, minute);
                        long p = (hour*3600) + minute*60;
                        long result_end = timestamp_start + timestamp_end + p;

                       Log.d("result_end:", String.valueOf(result_end));
                    }
                }, hour_end, minute_end, true);
                endtimePicker.setTitle("Select Time");
                endtimePicker.show();
            }
        });
        btnPinjam = findViewById(R.id.btn_pinjam);
        btnPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order(idRuangan, namaRuang);
            }
        });
        arrow_back = findViewById(R.id.arrow_back);
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(FormActivity.this, SelectRoomActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void Order(String idRuangan, String namaRuang){

        String url = " https://piruma.au-syd.mybluemix.net/api/order";
        JSONObject order = new JSONObject();

        try{
            order.put("id_ruangan", idRuangan);
            order.put("penanggung_jawab",etPic.getText());
            order.put("id_departemen",etJurusan.getText());
            order.put("ruangan", namaRuang);
            order.put("email", etEmail.getText());
            order.put("telepon",etNoHP.getText());
            order.put("keterangan",etKeterangan.getText().toString());
            order.put("timestamp_start", etTimeStart.getText());
            order.put("timestamp_end", etTimeEnd.getText());

        }catch (JSONException e){
            e.printStackTrace();
        }

        Log.d("body order", order.toString());
        final HashMap<String, String> user = session.getUserDetails();
        VolleyNetwork request = new VolleyNetwork(url,order,TAG);
        request.setToken(user.get(SessionManager.KEY_TOKEN));
        request.postRequest(new VolleyNetwork.VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                Toast.makeText(FormActivity.this, "Order Tercatat!", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(FormActivity.this, "Order Gagal", Toast.LENGTH_SHORT).show();
            }
        },this);

    }
}
