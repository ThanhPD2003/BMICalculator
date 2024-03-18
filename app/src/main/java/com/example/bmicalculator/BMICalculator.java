package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bmicalculator.database.AppDatabase;
import com.example.bmicalculator.models.User;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class BMICalculator extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private EditText ageEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;

    private Button btnCalculate;
    
    private Button btnBack;


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
        btnBack=findViewById(R.id.btnBack);
    }

    private boolean getIsMale() {
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        return selectedId == R.id.maleRadioButton;
    }

    private void bindingAction() {
        btnCalculate.setOnClickListener(this::onBtnCalculate);
        btnBack.setOnClickListener(this::onBtnBack);
    }

    private void onBtnBack(View view) {
        // Assuming you have a MainActivity class
        finish();
    }

    private void onBtnCalculate(View v) {
        try {
            double weight = Double.parseDouble(weightEditText.getText().toString());
            double height = Double.parseDouble(heightEditText.getText().toString()) / 100; // Convert cm to meters
            int age = Integer.parseInt(ageEditText.getText().toString());
            boolean isMale = getIsMale();
            double bmi = calculateBMI(weight, height);
            double bmr = calculateBMR(weight, height, age, isMale);

            String bmiText = "Your BMI is: " + String.format("%.2f", bmi) + "\n";
            String status = interpretBmi(bmi);
            bmiText += status;

            String bmrText = "Your BMR is: " + String.format("%.2f", bmr) + " calories/day";
            LocalDateTime localTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                localTime = LocalDateTime.now();
            }
            // Display the results
            showResultDialog(bmiText + "\n\n" + bmrText);
            insertUser(weight, height, age, isMale,bmi,bmr,localTime,status);

        } catch (NumberFormatException e) {
            // Handle invalid input gracefully
            Toast.makeText(this, "Invalid input detected. Please check the entered values.", Toast.LENGTH_LONG).show();
        }
    }

    private void insertUser(double weight, double height, int age, boolean isMale, double bmi, double bmr, LocalDateTime date, String status) {
        User user = new User(weight, height, age, isMale, bmi, bmr, date.toString(), status);
        AppDatabase.getInstance(this).userDao().insert(user);

    }




    private void showResultDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("BMI & BMR Results");
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
