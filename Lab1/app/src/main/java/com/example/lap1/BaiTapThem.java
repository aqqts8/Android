package com.example.lap1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BaiTapThem extends AppCompatActivity {

    private EditText etSoA, etSoB;
    private TextView ketqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai_tap_them);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etSoA = findViewById(R.id.etSoA);
        etSoB = findViewById(R.id.etSoB);
        ketqua = findViewById(R.id.ketqua);
    }

    public void tong(View view) {
        double a = getNumberFromEditText(etSoA);
        double b = getNumberFromEditText(etSoB);
        double result = a + b;
        ketqua.setText(String.valueOf(result));
    }

    public void hieu(View view) {
        double a = getNumberFromEditText(etSoA);
        double b = getNumberFromEditText(etSoB);
        double result = a - b;
        ketqua.setText(String.valueOf(result));
    }

    public void tich(View view) {
        double a = getNumberFromEditText(etSoA);
        double b = getNumberFromEditText(etSoB);
        double result = a * b;
        ketqua.setText(String.valueOf(result));
    }

    public void thuong(View view) {
        double a = getNumberFromEditText(etSoA);
        double b = getNumberFromEditText(etSoB);
        if (b != 0) {
            double result = a / b;
            ketqua.setText(String.valueOf(result));
        } else {
            ketqua.setText("Không thể chia cho 0");
        }
    }

    private double getNumberFromEditText(EditText editText) {
        String text = editText.getText().toString();
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}