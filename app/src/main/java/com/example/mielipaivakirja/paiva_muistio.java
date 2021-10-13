package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class paiva_muistio extends AppCompatActivity {

    String date;
    TextView editText;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_muistio);
        Bundle bundle = getIntent().getExtras();
        date = bundle.getString("CALENDAR_DATE");
        editText = findViewById(R.id.editText);
        dataBase = new DataBase(paiva_muistio.this);

    }


    private void tallennus(){
        String muistio = editText.getText().toString();
        boolean result = dataBase.addMuistio(date, muistio);
        if(result){
            Toast.makeText(this, "Muistio lisätty " + date, Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Lisääminen epäonnistui " + date, Toast.LENGTH_SHORT).show();
        }
    }

    public void tallennaMuistio(View view){
        tallennus();
        Intent intent = new Intent(this, paivakirja_luonti.class);
        intent.putExtra("CALENDAR_DATE", date);
        startActivity(intent);
    }
}