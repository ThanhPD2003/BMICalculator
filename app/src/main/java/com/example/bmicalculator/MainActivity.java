package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnOpenBmiCalculator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
    }

    private void bindingView() {
        btnOpenBmiCalculator = findViewById(R.id.btnOpenBmiCalculator);
    }

    private void bindingAction() {
        btnOpenBmiCalculator.setOnClickListener(this::onBtnOpenBmiCalculator);
    }

    public void onBtnOpenBmiCalculator(View view) {
        Log.d("MainActivity", "Opening BMI Calculator");
        Intent intent = new Intent(this, BMICalculator.class);
        startActivity(intent);
    }

}
