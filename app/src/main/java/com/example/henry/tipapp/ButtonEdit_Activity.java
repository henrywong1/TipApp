package com.example.henry.tipapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ButtonEdit_Activity extends AppCompatActivity {
    EditText editText;
    String keyNum;
    Button btn;


    public void onClick(View view) {
        Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
        if(keyNum.equals("one")){
            returnIntent.putExtra("btnOne",editText.getText().toString());
        } else if (keyNum.equals("two")){
            returnIntent.putExtra("btnTwo",editText.getText().toString());
        } else if (keyNum.equals("three")){
            returnIntent.putExtra("btnThree",editText.getText().toString());
        }



        startActivity(returnIntent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_edit_);

        editText = findViewById(R.id.editText);

        editText.setRawInputType(Configuration.KEYBOARD_QWERTY);

        Intent intent = getIntent();

        keyNum = intent.getStringExtra("key");
        Log.i("GETTING INFO FROM BUTTON NUMBER", keyNum);

        if (keyNum.equals("one")){
            Log.i("One","ONEEEEEEEEEEEEEEEEEEEEE");
            String savedNum = Double.toString(intent.getDoubleExtra("num1", 0));
            editText.setText(savedNum);
        } else if (keyNum.equals("two")) {
            Log.i("two","twotwotwotwotwotwotwotwotwo");
            String savedNum = Double.toString(intent.getDoubleExtra("num2", 0));
            editText.setText(savedNum);
        } else if (keyNum.equals("three")) {
            Log.i("three","threethreethreethreethreethreethreethreethreethreethreethree");
            String savedNum = Double.toString(intent.getDoubleExtra("num3", 0));
            editText.setText(savedNum);
        }

    }
}
