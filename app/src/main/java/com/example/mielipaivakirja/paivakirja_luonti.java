package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class paivakirja_luonti extends AppCompatActivity {


    private String date;
    DataBase dataBase;
    TextView pvm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivakirja_luonti);
        Intent intent = getIntent();
        date = intent.getStringExtra(MainActivity.CALENDAR_DATE);
        pvm = findViewById(R.id.paivamaara);
        pvm.setText(date);


    }


    public void lisaaArvio(View view){
        Intent intent = new Intent(this, paiva_arviointi.class);
        startActivity(intent);
    }

    public void lisaaMuistio(View view){
        Intent intent = new Intent(this, paiva_muistio.class);
        startActivity(intent);
    }


    public void tallenna(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}