package com.example.warehousemanager.object;

public class Product {
   private  String category;
   private  String description;
   private  int giaTien;
   private String hinhAnh;
   private  String id;
   private  int sold;
   private  int stock;
   private String tenHinhAnh;
   private  String tenSanPham;

    public Product() {
    }

    public Product(String category, String description, int giaTien, String hinhAnh, String id, int sold, int stock, String tenHinhAnh, String tenSanPham) {
        this.category = category;
        this.description = description;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.id = id;
        this.sold = sold;
        this.stock = stock;
        this.tenHinhAnh = tenHinhAnh;
        this.tenSanPham = tenSanPham;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTenHinhAnh() {
        return tenHinhAnh;
    }

    public void setTenHinhAnh(String tenHinhAnh) {
        this.tenHinhAnh = tenHinhAnh;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }
}
