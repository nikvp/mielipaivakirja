package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class paivakirja_luonti extends AppCompatActivity {


    private String date;
    TextView pvm;
    DataBase dataBase;

    /*
    onCreatessa haetaan valittua päivämäärää ja annetaan widgeteille perusarvot jos voi
    aktivoidaan lisaaPaivamaara()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivakirja_luonti);
        date = getIntent().getStringExtra("CALENDAR_DATE");
        pvm = findViewById(R.id.paivamaara);
        pvm.setText(date);
        dataBase = new DataBase(paivakirja_luonti.this);
        lisaaPaivamaara(date);
    }

    /*
    tämä luo tyhjän paivakirjan, joka lisätään DataBaseen
     */
    private void lisaaPaivamaara(String date){
        paivakirja uusi = new paivakirja(date, 0, "");
        dataBase.addData(uusi);
    }

    /*
    methodi vie paiva_arviointi activiteettiin
     */
    public void lisaaArvio(View view){
        Intent intent = new Intent(this, paiva_arviointi.class);
        intent.putExtra("CALENDAR_DATE", date);
        startActivity(intent);
    }
    /*
    methodi vie paiva_muistio activiteettiin
    */
    public void lisaaMuistio(View view){
        Intent intent = new Intent(this, paiva_muistio.class);
        intent.putExtra("CALENDAR_DATE", date);
        startActivity(intent);
    }
    /*
    methodi vie takaisin MainActivityyn activiteettiin
    */
    public void tallenna(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}