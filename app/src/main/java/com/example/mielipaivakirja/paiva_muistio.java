package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class paiva_muistio extends AppCompatActivity {

    CharSequence muistio;
    TextView editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_muistio);
        editText = findViewById(R.id.editText);
    }


    public void tallennaMuistio(View view){
        CharSequence tallennettava = editText.getText();
        Intent intent = new Intent(this, paivakirja_luonti.class);
        intent.putExtra("PAIVA_MUISTIO", tallennettava);
        startActivity(intent);
    }
}