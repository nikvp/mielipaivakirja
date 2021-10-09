package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DataBase dataBase = new DataBase(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void arviointi(View view){
        Intent intent = new Intent(this, paiva_arviointi.class);
        startActivity(intent);
    }

    public void tarkastele(View view){
        Intent intent = new Intent(this, paiva_tarkastelu.class);
        startActivity(intent);
    }



    protected void onDestroy(){
        dataBase.close();
        super.onDestroy();
    }
}