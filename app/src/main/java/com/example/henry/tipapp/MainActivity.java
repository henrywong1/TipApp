package com.example.henry.tipapp;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity {
    InputMethodManager inputManager;
    EditText amountTextView;
    SeekBar seekBar;
    TextView sumTextView;
    TextView personSumTextView;
    TextView taxSumTextView;
    TextView totalPplTextView;
    TextView percentProgressTextView;
    Spinner spinner;
    String[] numPeople = new String[20];
    String total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        percentProgressTextView = findViewById(R.id.percentProgressTextView);

        amountTextView = findViewById(R.id.amountTextView);
        seekBar = findViewById(R.id.seekBar);
        sumTextView = findViewById(R.id.sumTextView2);
        personSumTextView = findViewById(R.id.personSumTextView);
        taxSumTextView = findViewById(R.id.taxSumTextView);
        totalPplTextView = findViewById(R.id.textView4);
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        taxSumTextView.setVisibility(View.INVISIBLE);
        sumTextView.setVisibility(View.INVISIBLE);
        seekBar.setMin(0);
        seekBar.setMax(30);
        seekBar.setEnabled(false);

        spinner = findViewById(R.id.spinner);


        for (int i = 0; i < numPeople.length; i++) {
            numPeople[i] = Integer.toString(i + 1);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numPeople);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        spinner.setEnabled(false);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentProgressTextView.setText(Integer.toString(i));
                String s = amountTextView.getText().toString();
                double numTip = seekBar.getProgress() * 0.01;

                double amounts = Double.parseDouble(amountTextView.getText().toString());
                double totalCalc = amounts + (numTip * amounts);
                total = String.format("%.2f", totalCalc);
                sumTextView.setText("$" + total);

                String taxAmounts = String.format("%.2f", (numTip * amounts));

                taxSumTextView.setText("$" + taxAmounts);

                if (spinner.getSelectedItemPosition() > 0){
                    Log.i("Current Total", total);
                    Double temp = Double.parseDouble(total);
                    temp = temp / (spinner.getSelectedItemPosition()+ 1);
                    String temp1 = String.format("%.2f", temp);

                    personSumTextView.setText("$" + temp1);

                }
                if (s.equals("")) {
                    spinner.setEnabled(false);
                }
                // calculate total.
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i + 1 >= 2) {
                    totalPplTextView.setVisibility(View.VISIBLE);
                    personSumTextView.setVisibility(View.VISIBLE);

                    double ppls = Double.parseDouble(total);

                    double totalPerPerson = ppls / (i + 1);
                    String perPpl = String.format("%.2f", totalPerPerson);
                    personSumTextView.setText("$" + perPpl);
                } else if ( i == 0) {
                    totalPplTextView.setVisibility(View.INVISIBLE);
                    personSumTextView.setVisibility(View.INVISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                if (amountTextView.getText().toString().equals("") || amountTextView.getText().toString().equals(",")) {
                    seekBar.setEnabled(false);
                    spinner.setEnabled(false);
                    sumTextView.setVisibility(View.INVISIBLE);
                    taxSumTextView.setVisibility(View.INVISIBLE);
                } else {
                    seekBar.setEnabled(true);
                    spinner.setEnabled(true);
                    sumTextView.setVisibility(View.VISIBLE);
                    taxSumTextView.setVisibility(View.VISIBLE);
                    total = amountTextView.getText().toString();
                    sumTextView.setText("$" + amountTextView.getText().toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }
}
