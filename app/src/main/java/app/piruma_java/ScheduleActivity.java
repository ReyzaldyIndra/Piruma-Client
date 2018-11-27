package app.piruma_java;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.applandeo.materialcalendarview.utils.SelectedDay;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import app.piruma_java.network.VolleyNetwork;
import app.piruma_java.search.SearchActivity;
import app.piruma_java.selectroom.SelectRoomActivity;

public class ScheduleActivity extends AppCompatActivity {
    private EditText kapasitas;
    private Button btnCariRuang;
    private CalendarView calendarView;

    private String TAG = ScheduleActivity.class.getSimpleName();
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        calendarView = findViewById(R.id.calendar);
        kapasitas = findViewById(R.id.edittextKapasitas);
        btnCariRuang = findViewById(R.id.btn_cari_ruang);
        btnCariRuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ScheduleActivity.this, String.valueOf(time), Toast.LENGTH_SHORT).show();

//                Toast.makeText(ScheduleActivity.this, String.valueOf(getDate()), Toast.LENGTH_SHORT).show();
//                Log.d("Waktu awal : ", String.valueOf(getDate()));
                Intent intent = new Intent(ScheduleActivity.this, SearchActivity.class);
                intent.putExtra("kapasitas",kapasitas.getText().toString());
                intent.putExtra("timestamp_start",time);
                intent.putExtra("timestamp_end",time+86400);
                startActivity(intent);

            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayofMonth) {

                Calendar c = new GregorianCalendar();
                c.set(year,month,dayofMonth);

                long timeMili = c.getTimeInMillis()/1000;
                time = timeMili - (timeMili % 86400) - 25200;
                Log.d("Waktu dipilih :",String.valueOf(time));
            }

        });
    }
}


