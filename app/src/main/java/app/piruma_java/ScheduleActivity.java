package app.piruma_java;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.applandeo.materialcalendarview.utils.SelectedDay;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

import app.piruma_java.network.VolleyNetwork;
import app.piruma_java.search.SearchActivity;
import app.piruma_java.selectroom.SelectRoomActivity;

public class ScheduleActivity extends AppCompatActivity  {
private com.applandeo.materialcalendarview.CalendarView calendarView;
private EditText kapasitas;
private Button btnCariRuang;
private TextView txJadwal1, txJadwal2;

private String TAG = ScheduleActivity.class.getSimpleName();
private long date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        calendarView = findViewById(R.id.calendarview);
        txJadwal1 = findViewById(R.id.txJadwal1);
        txJadwal2 =  findViewById(R.id.txJadwal2);
        getDate();
        kapasitas = findViewById(R.id.edittextKapasitas);
        btnCariRuang = findViewById(R.id.btn_cari_ruang);
        btnCariRuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCapacity();
            }
        });

    }
public void getDate(){

calendarView.setOnDayClickListener(eventDay ->
        Toast.makeText(getApplicationContext(),
                eventDay.getCalendar().getTime().toString() + ""
                + eventDay.isEnabled(), Toast.LENGTH_SHORT).show());

}

public void getCapacity(){
        String url = "https://piruma.au-syd.mybluemix.net/api/ruangan/search";
        JSONObject schedule = new JSONObject();
        JSONObject timestamp =  new JSONObject();

        try {
            schedule.put("kapasitas", "30");
            timestamp.put("timestamp_start", "1" );
            timestamp.put("timestamp_end", "2");

        }catch (JSONException e){
            e.printStackTrace();
        }
    VolleyNetwork request = new VolleyNetwork(url, schedule, TAG);
    request.postRequest(new VolleyNetwork.VolleyCallback() {
        @Override
        public void onSuccess(JSONObject result) {
            try {
                JSONObject test = result.getJSONObject("count");
                String cap = test.getString("count");

                Toast.makeText(ScheduleActivity.this, cap, Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(ScheduleActivity.this, SearchActivity.class);
//                intent.putExtra("message", "kapasitas");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onError(VolleyError error) {

        }
    }, this);

}


    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

    }

}
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }



}
