package app.piruma_java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.piruma_java.Activity.LoginActivity;
import app.piruma_java.Activity.SignupActivity;
import app.piruma_java.selectroom.*;
import app.piruma_java.network.VolleyNetwork;

public class FormActivity extends AppCompatActivity {
    private ImageButton arrow_back;
    private TextView txOrderSchedule, txOrderRoom;
    private EditText etPic, etJurusan, etKeperluan, etNoHP, etKeterangan;
    private String TAG = FormActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        arrow_back = findViewById(R.id.arrow_back);
        arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(FormActivity.this, SelectRoomActivity.class);
                startActivity(intent);
            }
        });
        txOrderSchedule = findViewById(R.id.tx_order_schedule);
        txOrderRoom = findViewById(R.id.tx_order_room);
        etPic = findViewById(R.id.et_pic);
        etJurusan = findViewById(R.id.et_jurusan);
        etKeperluan = findViewById(R.id.et_keperluan);
        etNoHP =  findViewById(R.id.et_no_hp);
        etKeterangan = findViewById(R.id.et_keterangan);

        Order();
    }

    void Order(){
        String url = " https://piruma.au-syd.mybluemix.net/api/order";
        JSONObject order = new JSONObject();

        try{
            order.put("penanggung_jawab",etPic.getText().toString());
            order.put("departemen",etJurusan.getText().toString());
//            order.put("nim",etKeperluan.getText().toString());
            order.put("telepon",etNoHP.getText().toString());
            order.put("keterangan",etKeterangan.getText().toString());

        }catch (JSONException e){
            e.printStackTrace();
        }

        Log.d("body order", order.toString());
        VolleyNetwork request = new VolleyNetwork(url,order,TAG);
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
