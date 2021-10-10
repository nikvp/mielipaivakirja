package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

public class paiva_arviointi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_arviointi);
        SeekBar seekBarArvio = (SeekBar) findViewById(R.id.seekBarAhdistunut);
        seekBarArvio.setMax(100);
    }

}