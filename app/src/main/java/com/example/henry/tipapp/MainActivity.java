package com.example.henry.tipapp;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    InputMethodManager inputManager;
    EditText amountTextView;
    Button bOne;
    Button bTwo;
    Button bThree;
    TextView sumTextView;



    public void onClick(View view) {
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        String tip = ((Button) view).getText().toString();
        String[] part = tip.split("%");
        String tipper = part[0];


        double amounts = Double.parseDouble(amountTextView.getText().toString());
        double numTip = Integer.parseInt(tipper)* (0.01);
        double totalCalc;
        if (amountTextView.getText().equals("")){
            Toast.makeText(this, "Please enter the total amount before tipping", Toast.LENGTH_SHORT).show();
        } else {
            sumTextView.setVisibility(View.VISIBLE);
            totalCalc = amounts + (numTip*amounts);
            totalCalc = Math.round(totalCalc * 100);
            totalCalc = totalCalc / 100;
            String total = String.format("%.2f", totalCalc);
            sumTextView.setText("$" + total);
        }





    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = findViewById(R.id.amountTextView);
        bOne = findViewById(R.id.button);
        bTwo = findViewById(R.id.button2);
        bThree = findViewById(R.id.button3);
        sumTextView = findViewById(R.id.sumTextView);
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        amountTextView.setRawInputType(Configuration.KEYBOARD_QWERTY);


        sumTextView.setVisibility(View.INVISIBLE);


    }
}
