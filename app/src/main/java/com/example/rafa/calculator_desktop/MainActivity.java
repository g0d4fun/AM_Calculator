package com.example.rafa.calculator_desktop;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean isNewNumber;
    private boolean isLastASignal;
    private TextView calculatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorView = (TextView) findViewById(R.id.CalculatorView);
        if(savedInstanceState != null) {
            isNewNumber = savedInstanceState.getBoolean("isNewNumber");
            isLastASignal = savedInstanceState.getBoolean("isLastASignal");
            calculatorView.setText(savedInstanceState.getString("calculatorView"));
            return;
        }
        else {
            isNewNumber = true;
            isLastASignal = false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) { // Bundle
        // Base constructor shall be called to save state
        super.onSaveInstanceState(outState);

        outState.putBoolean("isNewNumber",isNewNumber);
        outState.putBoolean("isLastASignal",isLastASignal);
        outState.putString("calculatorView",calculatorView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        isNewNumber = savedInstanceState.getBoolean("isNewNumber");
        isLastASignal = savedInstanceState.getBoolean("isLastASignal");
        calculatorView = (TextView) findViewById(R.id.CalculatorView);
        calculatorView.setText(savedInstanceState.getString("calculatorView"));
    }

    public void onClickClear(View v) {
        calculatorView.setText("0.0");
        isNewNumber = true;
    }

    public void onClickButtonNumber(View v) {
        Button buttonNumber = (Button) v;
        String str = buttonNumber.getText().toString();

        if (!isNewNumber) {
            calculatorView.setText(calculatorView.getText() + str);
        } else {
            calculatorView.setText(str);
            isNewNumber = false;
        }

        isLastASignal = false;
    }

    public void onClickDot(View v) {
        if (isNewNumber) return;

        Button buttonDot = (Button) v;
        calculatorView.setText(calculatorView.getText() + buttonDot.getText().toString());
    }

    public void onClickSignal(View v) {
        if (isNewNumber || isLastASignal) return;

        isLastASignal = true;

        Button buttonSignal = (Button) v;
        String str = buttonSignal.getText().toString();

        calculatorView.setText(calculatorView.getText() + buttonSignal.getText().toString());
    }

    public void onClickResult(View v) {
        if(isNewNumber) return;

        String toCal = calculatorView.getText().toString();
    }

    public void showToastOnClickCalculatorView(View v){
        Toast toast = getToast("Calculator made by Rafa.");
        toast.show();
    }

    public Toast getToast(String textToPrint){
        Context context = getApplicationContext();
        CharSequence text = textToPrint;
        int duration = Toast.LENGTH_SHORT;

        return Toast.makeText(context, text, duration);
    }
}

