package com.example.mielipaivakirja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class paiva_tarkastelu extends AppCompatActivity {

    TextView paivamaara;
    TextView arvio;
    TextView muistio;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_tarkastelu);

        date = getIntent().getStringExtra("CALENDAR_DATE");
        paivamaara = findViewById(R.id.textViewPaivamaara);
        arvio = findViewById(R.id.textViewIndexi);
        muistio = findViewById(R.id.textViewMuistio);

        paivamaara.setText(date);

        DataBase dataBase = new DataBase(paiva_tarkastelu.this);
        paivakirja check = new paivakirja(date, 0, "");
        boolean result = dataBase.checkIfAllreadyExists(check.getPaiva());
        if(result){

            List<paivakirja> kaikki = dataBase.kaikkiPaivakirjat();
            if(kaikki.size()>0){
                for(int i = 0; i < kaikki.size(); i++){
                    if(date.equals(kaikki.get(i).getPaiva())){
                        paivakirja palautettava = kaikki.get(i);
                        check = palautettava;

                    }
                }
            }
            arvio.setText(check.getArvio() + "");
            muistio.setText(check.getMuistio());
        } else{
            arvio.setText("--");
            muistio.setText("");
        }
        dataBase.close();
    }


}

