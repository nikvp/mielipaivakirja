package com.example.mielipaivakirja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    public static final String CALENDAR_DATE = "";
    DataBase dataBase;
    CalendarView calendarView;
    String currentDate = "";
    TextView tv;
    private Object Key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("alku", "toimii 1");
        dataBase = new DataBase(MainActivity.this);
        Log.d("database", "toimii");
        calendarView = findViewById(R.id.calendarView);
        calendarView.setDate(System.currentTimeMillis());
        tv = findViewById(R.id.paivakirjaIndicator);
        calendarSetup();

    }

    private void calendarSetup(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                currentDate = i2 + "/" + (i1 + 1) + "/" + i;

            }
        });
    }

    public void arviointi(View view){
        Intent intent = new Intent(this, paivakirja_luonti.class);
        intent.putExtra(CALENDAR_DATE, currentDate);
        startActivity(intent);
    }

    public void tarkastele(View view){
        Intent intent = new Intent(this, paiva_tarkastelu.class);
        intent.putExtra(CALENDAR_DATE, currentDate);
        startActivity(intent);
    }



    protected void onDestroy(){
        dataBase.close();
        super.onDestroy();
    }
}