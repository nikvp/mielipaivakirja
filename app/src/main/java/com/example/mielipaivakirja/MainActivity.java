package com.example.mielipaivakirja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    CalendarView calendarView;
    String currentDate = "";
    TextView tv;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setDate(System.currentTimeMillis());
        tv = findViewById(R.id.paivakirjaIndicator);
        calendarSetup();
        currentDate = sdf.format(new Date(calendarView.getDate()));
        dataBase = new DataBase(MainActivity.this);
        tv.setText("");
    }

    private void calendarSetup(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                currentDate = i2 + "/" + (i1 + 1) + "/" + i;
                checkForPaivakirja(currentDate);
            }
        });
    }

    public void arviointi(View view){
        Intent intent = new Intent(this, paivakirja_luonti.class);
        intent.putExtra("CALENDAR_DATE", currentDate);
        startActivity(intent);
    }

    public void tarkastele(View view){
        Intent intent = new Intent(this, paiva_tarkastelu.class);
        intent.putExtra("CALENDAR_DATE", currentDate);
        startActivity(intent);
    }

    public void apua(View view){
        Intent intent = new Intent(this, Paiva_Apua.class);
        startActivity(intent);
    }


    private void checkForPaivakirja(String current){
        boolean loytyy = dataBase.checkIfAllreadyExists(current);
        if(loytyy){
            tv.setText("Päiväkirja löytyy");
        } else{
            tv.setText("Päiväkirjaa ei löydy");
        }
    }


}