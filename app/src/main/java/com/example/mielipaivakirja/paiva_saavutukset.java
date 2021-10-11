package com.example.mielipaivakirja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class paiva_saavutukset extends AppCompatActivity {

    TextView textView;
    EditText inputText;
    String saavutukset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_saavutukset);

        inputText = (EditText) findViewById(R.id.inputSaavutukset);
    }

    public void saveText(View view) {
        saavutukset = String.valueOf(inputText.getText());
        System.out.println(saavutukset);
    }
}