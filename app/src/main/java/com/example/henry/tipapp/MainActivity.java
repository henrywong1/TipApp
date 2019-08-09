package com.example.henry.tipapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    InputMethodManager inputManager;
    EditText amountTextView;
    Button bOne;
    Button bTwo;
    Button bThree;
    TextView sumTextView;
    TextView personSumTextView;
    TextView totalPplTextView;
    String[] numPeople;
    String total;


    public void onClick(View view) {
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if (amountTextView.equals("")) {
            Toast.makeText(this, "Please Enter your Total.", Toast.LENGTH_SHORT).show();
            return;
        }


        String tip = ((Button) view).getText().toString();
        String[] part = tip.split("%");
        String tipper = part[0];


        double amounts = Double.parseDouble(amountTextView.getText().toString());
        double numTip = Double.parseDouble(tipper)* (0.01);
        double totalCalc;
        if (amountTextView.getText().equals("")){
            Toast.makeText(this, "Please enter the total amount before tipping", Toast.LENGTH_SHORT).show();
        } else {
            sumTextView.setVisibility(View.VISIBLE);
            totalCalc = amounts + (numTip*amounts);
            totalCalc = Math.round(totalCalc * 100);
            totalCalc = totalCalc / 100;
            total = String.format("%.2f", totalCalc);
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
        personSumTextView = findViewById(R.id.personSumTextView);
        totalPplTextView = findViewById(R.id.textView4);
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        bOne.setEnabled(false);
        bTwo.setEnabled(false);
        bThree.setEnabled(false);



        final Spinner spinner = findViewById(R.id.spinner);


        numPeople = new String[]{"1","2","3","4","5","6"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numPeople);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i+1 >= 2) {
                    totalPplTextView.setVisibility(View.VISIBLE);
                    personSumTextView.setVisibility(View.VISIBLE);
                    double totalPerPerson = Double.parseDouble(total) / (i+1);

                    totalPerPerson = Math.round(totalPerPerson*100);
                    totalPerPerson = totalPerPerson / 100;

                    String totalPP = String.format("%.2f",totalPerPerson);

                    personSumTextView.setText("$" + totalPP);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        amountTextView.setRawInputType(Configuration.KEYBOARD_QWERTY);


        amountTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(amountTextView.toString().trim().length()==0){
                    bOne.setEnabled(false);
                    bTwo.setEnabled(false);
                    bThree.setEnabled(false);
                } else {
                    bOne.setEnabled(true);
                    bTwo.setEnabled(true);
                    bThree.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Intent returnIntent = getIntent();
        if (returnIntent.getStringExtra("btnOne") != null) {
            bOne.setText(returnIntent.getStringExtra("btnOne") + "%");
        } else if (returnIntent.getStringExtra("btnTwo") != null) {
            bTwo.setText(returnIntent.getStringExtra("btnTwo") + "%");
        } else if (returnIntent.getStringExtra("btnThree")!= null) {
            Log.i("THREEEEEEEEEEEEEEEEEEEEEEEEEE","FREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEZE");
            bThree.setText(returnIntent.getStringExtra("btnThree") + "%");
        }


        bOne.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String fButtonText = bOne.getText().toString();
                String[] part = fButtonText.split("%");
                Double buttonNum = Double.parseDouble(part[0]);
                Intent intent = new Intent(getApplicationContext(), ButtonEdit_Activity.class);
                intent.putExtra("num1", buttonNum);
                intent.putExtra("key","one");
                Log.i("SENDING", "INFO");
                startActivity(intent);

                return true;
            }
        });

        bTwo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String fButtonText = bTwo.getText().toString();
                String[] part = fButtonText.split("%");
                Double buttonNum = Double.parseDouble(part[0]);
                Intent intent = new Intent(getApplicationContext(), ButtonEdit_Activity.class);
                intent.putExtra("num2",buttonNum);
                intent.putExtra("key","two");
                Log.i("SENDING", "INFO");
                startActivity(intent);
                return true;
            }
        });

        bThree.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String fButtonText = bThree.getText().toString();
                String[] part = fButtonText.split("%");
                Double buttonNum = Double.parseDouble(part[0]);
                Intent intent = new Intent(getApplicationContext(), ButtonEdit_Activity.class);
                intent.putExtra("num3", buttonNum);
                intent.putExtra("key","three");
                Log.i("SENDING", "INFO");
                startActivity(intent);

                return true;
            }
        });
        sumTextView.setVisibility(View.INVISIBLE);

    }
}
