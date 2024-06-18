package com.example.lab2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.clear:
                AlertDialog.Builder message = new AlertDialog.Builder(this);
                message.setTitle(R.string.message_caption);
                message.setMessage(R.string.message_content);
                message.setNeutralButton(R.string.close, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText et = (EditText)findViewById(R.id.note);
                                et.setText("");
                            }
                        }).show();
                break;

            case R.id.setting:
                Intent intent = new Intent(this, SecondActivity.class);
                final int result = 1;
//                startActivityForResult(intent, result);
                break;

            case R.id.exit:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.exit_caption)
                        .setMessage(R.string.exit_content)
                        .setNeutralButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).show();
                break;
        }
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Lấy Bundle dữ liệu
        Bundle bundle = data.getExtras();
        int index1 = bundle.getInt("forecolor");
        int index2 = bundle.getInt("backcolor");
        // Lấy mảng màu
        String colorArray[] = getResources().getStringArray(R.array.color_array);
        //Tham thiếu đến editText
        EditText et = (EditText) findViewById(R.id.note);
        //Thiết lập màu
        et.setTextColor(Color.parseColor(colorArray[index1]));
        et.setBackgroundColor(Color.parseColor(colorArray[index2]));
    }

}