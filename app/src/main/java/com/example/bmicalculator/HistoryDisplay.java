package com.example.bmicalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmicalculator.database.AppDatabase;
import com.example.bmicalculator.database.Dao.UserDao;
import com.example.bmicalculator.models.User;

import java.util.ArrayList;
import java.util.List;

public class HistoryDisplay extends AppCompatActivity {

    private RecyclerView recyclerViewHistory;
    private List<User> userList;

    private Button btnHeightHistory;
    private Button btnWeightHistory;
    private Button btnBMIHistory;

    private HeightHistoryAdapter heightAdapter;
    private WeightHistoryAdapter weightAdapter;
    private BMIHistoryAdapter BMIAdapter;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_history);
        bindingView();
        bindingAction();
        heightAdapter = new HeightHistoryAdapter();
        userList = new ArrayList<>();
        heightAdapter.setData(userList);

        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHistory.setAdapter(heightAdapter);
        loadHeightData();
        Log.d("HistoryDisplay", "Retrieved data: " + userList.size());

    }

    private void bindingAction() {
        btnHeightHistory.setOnClickListener(this::onBtnHeightHistory);
        btnWeightHistory.setOnClickListener(this::onBtnWeightHistory);
        btnBMIHistory.setOnClickListener(this::onBtnBMIHistory);
        btnBack.setOnClickListener(this::onBtnBack);
    }

    private void onBtnBack(View view) {
        finish();
    }

    private void onBtnBMIHistory(View view) {
        BMIAdapter = new BMIHistoryAdapter();
        userList = new ArrayList<>();
        BMIAdapter.setData(userList);

        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHistory.setAdapter(BMIAdapter);
        loadBMIData();
    }

    private void loadBMIData() {
        userList = AppDatabase.getInstance(this).userDao().getAllUser();
        BMIAdapter.setData(userList);
    }

    private void onBtnWeightHistory(View view) {
        weightAdapter = new WeightHistoryAdapter();
        userList = new ArrayList<>();
        weightAdapter.setData(userList);

        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHistory.setAdapter(weightAdapter);
        loadWeightData();

    }

    private void loadWeightData() {
        userList = AppDatabase.getInstance(this).userDao().getAllUser();
        weightAdapter.setData(userList);
    }

    private void onBtnHeightHistory(View view) {
        heightAdapter = new HeightHistoryAdapter();
        userList = new ArrayList<>();
        heightAdapter.setData(userList);

        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHistory.setAdapter(heightAdapter);
        loadHeightData();

    }

    private void loadHeightData() {
        userList = AppDatabase.getInstance(this).userDao().getAllUser();
        heightAdapter.setData(userList);
    }


    private void bindingView() {
        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        btnHeightHistory = findViewById(R.id.btnHeight);
        btnWeightHistory = findViewById(R.id.btnWeight);
        btnBMIHistory = findViewById(R.id.btnBMI);
        btnBack = findViewById(R.id.btnBack);
    }
}

