package com.example.use.object;

public class XemDonHang {
    private String date;
    private String ten;
    private String soLuong;
    private int gia;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public XemDonHang() {
    }

    public XemDonHang(String date, String ten, String soLuong, int gia) {
        this.date = date;
        this.ten = ten;
        this.soLuong = soLuong;
        this.gia = gia;
    }
}
