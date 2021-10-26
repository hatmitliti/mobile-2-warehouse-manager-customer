package com.example.use.object;

public class Cart {
    private int hinhAnh;
    private String ten;
    private int giaTien;
    private String soLuong;

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

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public Cart() {
    }

    public Cart(int hinhAnh, String ten, int giaTien, String soLuong) {
        this.hinhAnh = hinhAnh;
        this.ten = ten;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
    }
}
