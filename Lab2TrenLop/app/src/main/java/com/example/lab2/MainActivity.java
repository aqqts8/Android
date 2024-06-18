package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView nhap, ketqua;
    private StringBuilder input;
    private double firstNumber;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nhap = findViewById(R.id.nhap);
        ketqua = findViewById(R.id.ketqua);
        input = new StringBuilder();
        operator = "";
        firstNumber = 0;

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberIds = {R.id.number0, R.id.number1, R.id.number2, R.id.number3, R.id.number4, R.id.number5, R.id.number6, R.id.number7, R.id.number8, R.id.number9, R.id.numberCham};

        View.OnClickListener numberListener = v -> {
            Button button = (Button) v;
            input.append(button.getText());
            nhap.setText(input.toString());
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberListener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorIds = {R.id.numberCong, R.id.numberTru, R.id.numberNhan, R.id.numberChia};

        View.OnClickListener operatorListener = v -> {
            Button button = (Button) v;
            if (input.length() > 0) {
                if (operator.isEmpty()) {
                    firstNumber = Double.parseDouble(input.toString());
                    input.append(" ").append(button.getText()).append(" ");
                    nhap.setText(input.toString());
                    operator = button.getText().toString();
                } else {
                    input.replace(input.length() - 2, input.length(), button.getText().toString());
                    nhap.setText(input.toString());
                    operator = button.getText().toString();
                }
            }
        };

        findViewById(R.id.buttonResult).setOnClickListener(v -> calculateResult());
        findViewById(R.id.delete).setOnClickListener(v -> {
            input.setLength(0);
            operator = "";
            firstNumber = 0;
            nhap.setText("");
            ketqua.setText("");
        });

        for (int id : operatorIds) {
            findViewById(id).setOnClickListener(operatorListener);
        }
    }

    private void calculateResult() {
        if (input.length() > 0 && !operator.isEmpty()) {
            String[] parts = input.toString().split("\\s+");
            double secondNumber = Double.parseDouble(parts[2]);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "x":
                    result = firstNumber * secondNumber;
                    break;
                case "%":
                    result = firstNumber / secondNumber;
                    break;
            }

            ketqua.setText(String.valueOf(result));
            input.setLength(0);
            input.append(result);
            operator = "";
            nhap.setText(input.toString());
        }
    }
}
