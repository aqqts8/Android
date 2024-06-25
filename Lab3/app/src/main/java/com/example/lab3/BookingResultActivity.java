package com.example.lab3;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookingResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_result);

        TextView resultTextView = findViewById(R.id.resultTextView);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String date = getIntent().getStringExtra("date");
        String nights = getIntent().getStringExtra("nights");

        String result = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Date of Arrival: " + date + "\n" +
                "Number of Nights: " + nights;

        resultTextView.setText(result);
    }
}
