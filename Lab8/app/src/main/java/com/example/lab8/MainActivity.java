package com.example.lab8;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnThem, btnXoa, btnCapNhat, btnTimKiem;
    EditText edId, edMsv, edHoten;
    ListView lvDssv;
    DataSqlite dataSqlite;
    ArrayList<String> arrayListSV;

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

        AnhXaCacControl();
        KhoiTaoDatabase();
        LoadData();

        lvDssv.setOnItemClickListener((parent, view, position, id) -> {
            String sv = arrayListSV.get(position);
            String[] arr = sv.split("\\ - ", 0);
            edId.setText(arr[0]);
            edMsv.setText(arr[1]);
            edHoten.setText(arr[2]);
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edMsv.getText().toString().equals("") || edHoten.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    String sqlThem = "INSERT INTO QLSV(maSV, tenSV) VALUES('" + edMsv.getText().toString() + "','" + edHoten.getText().toString() + "')";
                    dataSqlite.TruyVanKhongTraVe(sqlThem);
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    LoadData();
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edId.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập ID", Toast.LENGTH_SHORT).show();
                } else {
                    String sqlXoa = "DELETE FROM QLSV WHERE id = " + edId.getText().toString();
                    dataSqlite.TruyVanKhongTraVe(sqlXoa);
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    LoadData();
                }
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edId.getText().toString().equals("") || edMsv.getText().toString().equals("") || edHoten.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    String sqlCapNhat = "UPDATE QLSV SET maSV = '" + edMsv.getText().toString() + "', tenSV = '" + edHoten.getText().toString() + "' WHERE id = " + edId.getText().toString();
                    dataSqlite.TruyVanKhongTraVe(sqlCapNhat);
                    Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    LoadData();
                }
            }
        });

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = edHoten.getText().toString();
                arrayListSV.clear();
                Cursor cursor = dataSqlite.TruyVanTraVe("SELECT * FROM QLSV WHERE tenSV LIKE '%" + searchQuery + "%'");
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        ObjectSinhVien sv = new ObjectSinhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                        arrayListSV.add(sv.toString());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayListSV);
                    lvDssv.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXaCacControl() {
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        edId = findViewById(R.id.edId);
        edMsv = findViewById(R.id.edMsv);
        edHoten = findViewById(R.id.edHoten);
        lvDssv = findViewById(R.id.lvDssv);
    }

    private void KhoiTaoDatabase() {
        dataSqlite = new DataSqlite(this, "QLSV.db", null, 1);
        String sqlTaoDB = "CREATE TABLE IF NOT EXISTS QLSV (id INTEGER PRIMARY KEY AUTOINCREMENT, maSV TEXT, tenSV TEXT)";
        dataSqlite.TruyVanKhongTraVe(sqlTaoDB);
    }

    private void LoadData() {
        arrayListSV = new ArrayList<>();
        Cursor cursor = dataSqlite.TruyVanTraVe("SELECT * FROM QLSV");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ObjectSinhVien sv = new ObjectSinhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                arrayListSV.add(sv.toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayListSV);
            adapter.notifyDataSetChanged();
            lvDssv.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }
}
