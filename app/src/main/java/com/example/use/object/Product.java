package com.example.use.object;

public class Product {
    private int hinhAnh;
    private String ten;
    private int giaTien;

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public Product() {
    }

    public Product(int hinhAnh, String ten, int giaTien) {
        this.hinhAnh = hinhAnh;
        this.ten = ten;
        this.giaTien = giaTien;
    }
}
