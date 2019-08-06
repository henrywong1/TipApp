package com.example.henry.tipapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText amountTextView;
    Button bOne;
    Button bTwo;
    Button bThree;
    TextView sumTextView;



    public void onClick(View view) {

        String tip = ((Button) view).getText().toString();
        String[] part = tip.split("%");
        String tipper = part[0];


        double amounts = Double.parseDouble(amountTextView.getText().toString());
        double numTip = Integer.parseInt(tipper)* (0.01);
        double totalCalc;
        if (amountTextView.getText().equals("")){
            Toast.makeText(this, "Please enter the total amount before tipping", Toast.LENGTH_SHORT).show();
        } else {
            totalCalc = amounts + (numTip*amounts);
            
            sumTextView.setText("$" + Double.toString(totalCalc));
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


    }
}
