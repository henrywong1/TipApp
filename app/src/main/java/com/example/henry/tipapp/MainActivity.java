package com.example.henry.tipapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    InputMethodManager inputManager;
    EditText amountTextView;
    SeekBar seekBar;
    TextView sumTextView;
    TextView personSumTextView;
    TextView totalPplTextView;
    TextView percentProgressTextView;
    Spinner spinner;
    String[] numPeople = new String[20];
    String total;


    public void onClick(View view) {
        double totalCalc;
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        String s = amountTextView.getText().toString();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',') {
                Toast.makeText(this, "Invalid entry, please enter again", Toast.LENGTH_SHORT).show();
                spinner.setEnabled(false);
                return;
            }
        }

        if (s.equals("")) {
            spinner.setEnabled(false);
        }


        String tip = ((Button) view).getText().toString();
        String[] part = tip.split("%");
        String tipper = part[0];


        double amounts = Double.parseDouble(amountTextView.getText().toString());
        double numTip = Double.parseDouble(tipper) * (0.01);

        if (amountTextView.getText().equals("")) {
            Toast.makeText(this, "Please enter the total amount before tipping", Toast.LENGTH_SHORT).show();
        } else {


            sumTextView.setVisibility(View.VISIBLE);
            totalCalc = amounts + (numTip * amounts);
            totalCalc = Math.round(totalCalc * 100);
            totalCalc = totalCalc / 100;

            if (spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().equals("1")) {
                totalPplTextView.setVisibility(View.INVISIBLE);
                personSumTextView.setVisibility(View.INVISIBLE);
            } else {
                Double numOfPeople = Double.parseDouble(spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString());
                String total2 = String.format("%.2f", (totalCalc / numOfPeople));
                personSumTextView.setText("$" + total2);
            }


            total = String.format("%.2f", totalCalc);
            sumTextView.setText("$" + total);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        percentProgressTextView = findViewById(R.id.percentProgressTextView);
        amountTextView = findViewById(R.id.amountTextView);
        seekBar = findViewById(R.id.seekBar);
        sumTextView = findViewById(R.id.sumTextView);
        personSumTextView = findViewById(R.id.personSumTextView);
        totalPplTextView = findViewById(R.id.textView4);
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        sumTextView.setVisibility(View.INVISIBLE);
        seekBar.setMin(0);
        seekBar.setMax(30);
        seekBar.setProgress(15);
        seekBar.setEnabled(false);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentProgressTextView.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinner = findViewById(R.id.spinner);


        for (int i = 0; i < numPeople.length; i++) {
            numPeople[i] = Integer.toString(i + 1);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numPeople);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i + 1 >= 2) {
                    totalPplTextView.setVisibility(View.VISIBLE);
                    personSumTextView.setVisibility(View.VISIBLE);
                    double totalPerPerson = Double.parseDouble(total) / (i + 1);

                    totalPerPerson = Math.round(totalPerPerson * 100);
                    totalPerPerson = totalPerPerson / 100;

                    String totalPP = String.format("%.2f", totalPerPerson);

                    personSumTextView.setText("$" + totalPP);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner.setEnabled(false);
        amountTextView.setRawInputType(Configuration.KEYBOARD_QWERTY);
        amountTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    inputManager.hideSoftInputFromWindow(textView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    return true;
                }
                return false;
            }
        });
        amountTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (amountTextView.getText().toString().equals("")) {
                    seekBar.setEnabled(false);
                    sumTextView.setVisibility(View.INVISIBLE);
                } else {
                    seekBar.setEnabled(true);
                    sumTextView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }
}
