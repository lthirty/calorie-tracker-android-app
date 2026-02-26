package com.example.calorietracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText foodNameEditText;
    private EditText caloriesEditText;
    private Button addButton;
    private Button resetButton;
    private TextView totalCaloriesTextView;
    private TextView foodListTextView;
    
    private List<String> foodEntries = new ArrayList<>();
    private int totalCalories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeViews();
        setupClickListeners();
    }
    
    private void initializeViews() {
        foodNameEditText = findViewById(R.id.foodNameEditText);
        caloriesEditText = findViewById(R.id.caloriesEditText);
        addButton = findViewById(R.id.addButton);
        resetButton = findViewById(R.id.resetButton);
        totalCaloriesTextView = findViewById(R.id.totalCaloriesTextView);
        foodListTextView = findViewById(R.id.foodListTextView);
    }
    
    private void setupClickListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodEntry();
            }
        });
        
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAllData();
            }
        });
    }
    
    private void addFoodEntry() {
        String foodName = foodNameEditText.getText().toString().trim();
        String caloriesStr = caloriesEditText.getText().toString().trim();
        
        if (foodName.isEmpty() || caloriesStr.isEmpty()) {
            Toast.makeText(this, "请输入食物名称和卡路里", Toast.LENGTH_SHORT).show();
            return;
        }
        
        try {
            int calories = Integer.parseInt(caloriesStr);
            if (calories <= 0) {
                Toast.makeText(this, "卡路里必须大于0", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // 添加到列表
            foodEntries.add(foodName + ": " + calories + " 卡路里");
            totalCalories += calories;
            
            // 更新UI
            updateUI();
            
            // 清空输入框
            foodNameEditText.setText("");
            caloriesEditText.setText("");
            
            Toast.makeText(this, "已添加食物记录", Toast.LENGTH_SHORT).show();
            
        } catch (NumberFormatException e) {
            Toast.makeText(this, "请输入有效的数字", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void resetAllData() {
        foodEntries.clear();
        totalCalories = 0;
        updateUI();
        foodNameEditText.setText("");
        caloriesEditText.setText("");
        Toast.makeText(this, "所有数据已重置", Toast.LENGTH_SHORT).show();
    }
    
    private void updateUI() {
        // 更新总卡路里
        totalCaloriesTextView.setText("总卡路里: " + totalCalories);
        
        // 更新食物列表
        StringBuilder foodList = new StringBuilder();
        for (String entry : foodEntries) {
            foodList.append("• ").append(entry).append("\n");
        }
        foodListTextView.setText(foodList.toString());
    }
}