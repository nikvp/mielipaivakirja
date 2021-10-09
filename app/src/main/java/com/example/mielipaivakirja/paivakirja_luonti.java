package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class paivakirja_luonti extends AppCompatActivity {

    TextView pvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivakirja_luonti);
        Intent intent = getIntent();
        String date = intent.getStringExtra(MainActivity.CALENDAR_DATE);
        pvm = findViewById(R.id.paivamaara);
        pvm.setText(date);


    }


    public void lisaaArvio(View view){

    }

    public void lisaaMuistio(View view){


    }


    public void tallenna(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}