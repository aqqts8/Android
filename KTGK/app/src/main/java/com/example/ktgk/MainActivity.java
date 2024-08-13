package com.example.ktgk;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etId, etTenSanPham, etSoLuong;
    CheckBox cbMoi;
    Button btnThem, btnXoa, btnCapNhat, btnTimKiem;
    ListView listView;
    ArrayList<String> sanPhamList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.etId);
        etTenSanPham = findViewById(R.id.etTenSanPham);
        etSoLuong = findViewById(R.id.etSoLuong);
        cbMoi = findViewById(R.id.cbMoi);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        listView = findViewById(R.id.listView);

        sanPhamList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sanPhamList);
        listView.setAdapter(adapter);

        btnThem.setOnClickListener(v -> themSanPham());
    }

    private void themSanPham() {
        String id = etId.getText().toString();
        String tenSanPham = etTenSanPham.getText().toString();
        String soLuong = etSoLuong.getText().toString();
        String tinhTrang = cbMoi.isChecked() ? "Mới 100%" : "Đã qua sử dụng";

        String sanPham = id + " - " + tenSanPham + " - " + soLuong + " - " + tinhTrang;
        sanPhamList.add(sanPham);
        adapter.notifyDataSetChanged();

        // Clear input fields
        etId.getText().clear();
        etTenSanPham.getText().clear();
        etSoLuong.getText().clear();
        cbMoi.setChecked(false);
    }
}