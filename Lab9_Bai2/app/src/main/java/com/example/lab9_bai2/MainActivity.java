package com.example.lab9_bai2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SeekBar sbSound, sbBrightness;
    RadioButton rbtnEasy, rbtnMedium, rbtnHard;
    Button btnSave;

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

        sbSound = (SeekBar) findViewById(R.id.sbSound);
        sbBrightness = (SeekBar) findViewById(R.id.sbBrightness);
        rbtnEasy = (RadioButton) findViewById(R.id.rbtnEasy);
        rbtnMedium = (RadioButton) findViewById(R.id.rbtnMedium);
        rbtnHard = (RadioButton) findViewById(R.id.rbtnHard);
        btnSave = (Button) findViewById(R.id.btnSave);

        ReadDataFromSharedPreferences();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDataToSharedPreferences();
            }
        });
    }

    private void ReadDataFromSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("settings.xml", MODE_PRIVATE);

        int sound = sp.getInt("sound", 10);
        int brightness = sp.getInt("brightness", 80);
        String difficulty = sp.getString("difficulty", "Easy");

        sbSound.setProgress(sound);
        sbBrightness.setProgress(brightness);

        if (difficulty.equals("Easy")) {
            rbtnEasy.setChecked(true);
        } else if (difficulty.equals("Medium")) {
            rbtnMedium.setChecked(true);
        } else if (difficulty.equals("Hard")) {
            rbtnHard.setChecked(true);
        }

        Toast.makeText(this, "Read", Toast.LENGTH_SHORT).show();
    }

    private void SaveDataToSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("settings.xml", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        int sound = sbSound.getProgress();
        int brightness = sbBrightness.getProgress();
        String difficulty = "Easy";

        if (rbtnMedium.isChecked()) {
            difficulty = "Medium";
        } else if (rbtnHard.isChecked()) {
            difficulty = "Hard";
        }

        editor.putInt("sound", sound);
        editor.putInt("brightness", brightness);
        editor.putString("difficulty", difficulty);
        editor.commit();

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }
}