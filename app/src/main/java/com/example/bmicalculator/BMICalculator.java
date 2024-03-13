package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalculator extends Activity {

    private EditText weightEditText;
    private EditText heightEditText;
    private EditText ageEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;

    private Button btnCalculate;
    private TextView bmiTextView;
    private TextView bmrTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);
        bindingView();
        bindingAction();
    }

    private void bindingView() {
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        ageEditText = findViewById(R.id.ageEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);
        btnCalculate = findViewById(R.id.btnCalculate);
        bmiTextView = findViewById(R.id.bmiTextView);
        bmrTextView = findViewById(R.id.bmrTextView);
    }

    private boolean getIsMale() {
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        return selectedId == R.id.maleRadioButton;
    }
    private void bindingAction() {
        btnCalculate.setOnClickListener(this::onBtnCalculate);
    }

    private void onBtnCalculate(View v) {
        try {
            double weight = Double.parseDouble(weightEditText.getText().toString());
            double height = Double.parseDouble(heightEditText.getText().toString()) / 100; // Convert cm to meters
            int age = Integer.parseInt(ageEditText.getText().toString());
            boolean isMale;
            isMale = getIsMale();
            double bmi = calculateBMI(weight, height);
            double bmr = calculateBMR(weight, height, age, isMale);

            String bmiText = "Your BMI is: " + String.format("%.2f", bmi) + "\n";
            bmiText += interpretBmi(bmi);

            String bmrText = "Your BMR is: " + String.format("%.2f", bmr) + " calories/day";

            bmiTextView.setText(bmiText);
            bmrTextView.setText(bmrText);
        } catch (NumberFormatException e) {
            // Handle invalid input gracefully
            Toast.makeText(this, "Invalid input detected. Please check the entered values.", Toast.LENGTH_LONG).show();
        }
    }

    private double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    private double calculateBMR(double weight, double height, int age, boolean isMale) {
        if (isMale) {
            return (10 * weight) + (6.25 * height * 100) - (5 * age) + 5;
        } else {
            return (10 * weight) + (6.25 * height * 100) - (5 * age) - 161;
        }
    }

    private String interpretBmi(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
