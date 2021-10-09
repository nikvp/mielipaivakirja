package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class paiva_arviointi extends AppCompatActivity {

    SeekBar one;
    SeekBar two;
    SeekBar three;
    TextView top;
    TextView mid;
    TextView bot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_arviointi);
        one = findViewById(R.id.seekBar1);
        two = findViewById(R.id.seekBar2);
        three = findViewById(R.id.seekBar3);

        seekbarOne();
        seekbartwo();
        seekbarthree();

        top = findViewById(R.id.textViewtop);
        mid = findViewById(R.id.textViewmid);
        bot = findViewById(R.id.textViewbot);
    }

    public void seekbarOne(){
        one.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int prog, boolean b) {
                top.setText(String.valueOf(prog));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void seekbartwo(){
        two.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mid.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void seekbarthree(){
        three.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                bot.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void haeKeskiarvo(){
        int keskiarvo = (one.getProgress() + two.getProgress() + three.getProgress())/3;

    }

    public void save(View view){
        haeKeskiarvo();

        Intent intent = new Intent(this, paivakirja_luonti.class);
        startActivity(intent);
    }
}