package com.example.lab8;

public class ObjectSinhVien {
    private Integer id;
    private String maSV, tenSV;

    public String toString() {
        return id + " - " + maSV + " - " + tenSV;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public ObjectSinhVien(Integer id, String maSV, String tenSV) {
        this.id = id;
        this.maSV = maSV;
        this.tenSV = tenSV;
    }
}
