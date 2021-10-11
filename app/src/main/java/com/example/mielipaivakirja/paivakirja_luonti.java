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
    private ArrayList<Integer> arvio = null;
    private CharSequence muistio = null;
    TextView pvm;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivakirja_luonti);
        dataBase = new DataBase(paivakirja_luonti.this);
        date = getIntent().getStringExtra("CALENDAR_DATE");
        if(date.equals("")){
            date = findLastDate();
        }
        pvm = findViewById(R.id.paivamaara);
        pvm.setText(date);
        arvio = getIntent().getIntegerArrayListExtra("PAIVA_ARVIO");
        muistio = getIntent().getCharSequenceExtra("PAIVA_MUISTIO");
    }


    public void lisaaArvio(View view){
        Intent intent = new Intent(this, paiva_arviointi.class);
        startActivity(intent);
    }

    public void lisaaMuistio(View view){
        Intent intent = new Intent(this, paiva_muistio.class);
        if(onkoMuistioTallessa()){
            intent.putExtra("PAIVA_MUISTIO",dataBase.viimeisinTallennus().getSaavutukset());
        }
        startActivity(intent);
    }


    public void tallenna(View view){
        if(voikoTallentaa()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(this, "lisää vähintään arvio", Toast.LENGTH_LONG).show();
        }
    }

    private void autoSave(){
        checkArvio(dataBase.viimeisinTallennus());
        checkMuistio(dataBase.viimeisinTallennus());
    }
    private void checkArvio(paivakirja paivakirja){
        if(arvio.size()> 0){
            int summa = 0;
            for(int i = 0; i < arvio.size(); i++){
                summa += arvio.get(i);
            }
            int keskiarvo = summa / arvio.size();
            dataBase.addArvio(paivakirja, keskiarvo);
        }
    }

    private boolean onkoMuistioTallessa(){
        paivakirja checkaus = dataBase.viimeisinTallennus();
        if(checkaus.getSaavutukset().equals("")){
            return false;
        } else {
            return true;
        }
    }

    private boolean onkoArvioTallessa(){
        paivakirja chekkaus = dataBase.viimeisinTallennus();
        if(chekkaus.getArviointi()==0){
            return false;
        } else{
            return true;
        }
    }

    private void checkMuistio(paivakirja paivakirja){
        if(muistio.length()>0){
            dataBase.addMuistio(paivakirja, muistio.toString());
        }
    }

    private void checkSave(){
        paivakirja viimeisin = dataBase.viimeisinTallennus();
        if(viimeisin != null){
            if(date!= viimeisin.getPaivamaara()){
                paivakirja lisattava = new paivakirja(date, 0, "");
                dataBase.addData(lisattava);
            }
        }

    }

    private String findLastDate(){
        paivakirja viimeisin = dataBase.viimeisinTallennus();
        return viimeisin.getPaivamaara();
    }

    private boolean voikoTallentaa(){
        if(date == dataBase.viimeisinTallennus().getPaivamaara()){
            if(dataBase.viimeisinTallennus().getArviointi()!=0){
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }

}