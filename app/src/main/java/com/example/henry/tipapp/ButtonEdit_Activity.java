package com.example.henry.tipapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ButtonEdit_Activity extends AppCompatActivity {
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_edit_);

        editText = findViewById(R.id.editText);

        editText.setRawInputType(Configuration.KEYBOARD_QWERTY);

        Intent intent = getIntent();
        String savedNum = Double.toString(intent.getDoubleExtra("num", 0));
        editText.setText(savedNum);

    }
}
