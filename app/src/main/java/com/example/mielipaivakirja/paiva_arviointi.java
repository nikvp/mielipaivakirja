package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class paiva_arviointi extends AppCompatActivity {

    String date;
    SeekBar one;
    SeekBar two;
    SeekBar three;
    SeekBar four;
    SeekBar five;
    SeekBar six;
    int valueOne;
    int valueTwo;
    int valueThree;
    int valueFour;
    int valueFive;
    int valueSix;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_arviointi);
        one = findViewById(R.id.seekBarAhdistunut);
        two = findViewById(R.id.seekBarSurullinen);
        three = findViewById(R.id.seekBarVapaaAika);
        four = findViewById(R.id.seekBarElama);
        five = findViewById(R.id.seekBarYstavat);
        six = findViewById(R.id.seekBarStressi);
        seekbarOne();
        seekbartwo();
        seekbarthree();
        seekbarfour();
        seekbarfive();
        seekbarsix();
        Bundle bundle = getIntent().getExtras();
        date = bundle.getString("CALENDAR_DATE");
        dataBase = new DataBase(paiva_arviointi.this);
    }

    private void seekbarOne(){
        one.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int prog, boolean b) {
                valueOne = prog;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void seekbartwo(){
        two.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueTwo = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void seekbarthree(){
        three.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueThree = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void seekbarfour(){
        four.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueFour = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void seekbarfive(){
        five.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueFive = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void seekbarsix(){
        six.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueSix = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void lisaaArvio(){
        double keskiarvo = (float)(valueOne + valueTwo + valueThree + valueFour + valueFive + valueSix)/6;
        boolean result = dataBase.addArvio(date, keskiarvo);
        if(result){
            Toast.makeText(this, "Arviointi lisätty: " + date, Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "lisääminen epäonnistui " + date, Toast.LENGTH_SHORT).show();
        }
    }

    public void save(View view){
        lisaaArvio();
        Intent intent = new Intent(this, paivakirja_luonti.class);
        startActivity(intent);
    }
}