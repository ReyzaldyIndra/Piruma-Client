package app.piruma_java;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import app.piruma_java.search.SearchActivity;
import app.piruma_java.selectroom.SelectRoomActivity;

public class ScheduleActivity extends AppCompatActivity {
private CalendarView calendar;
private Button btnCariRuang;
private long date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        calendar = findViewById(R.id.calendar);
        btnCariRuang = findViewById(R.id.btn_cari_ruang);
        btnCariRuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


    }




}
