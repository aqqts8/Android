package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class BookRoomActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, dateEditText, nightsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        dateEditText = findViewById(R.id.dateEditText);
        nightsEditText = findViewById(R.id.nightsEditText);
        Button bookButton = findViewById(R.id.bookButton);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookRoomActivity.this, BookingResultActivity.class);
                intent.putExtra("name", nameEditText.getText().toString());
                intent.putExtra("email", emailEditText.getText().toString());
                intent.putExtra("date", dateEditText.getText().toString());
                intent.putExtra("nights", nightsEditText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
