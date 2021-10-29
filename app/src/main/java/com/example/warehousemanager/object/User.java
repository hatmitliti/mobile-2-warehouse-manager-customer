package com.example.warehousemanager.object;

public class User {
    private  String id;
    private  String name;
    private  String rank;
    private  int totalMoney;
    private String phone;

    public User() {
    }

    public User(String id, String name, String rank, int totalMoney, String phone) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.totalMoney = totalMoney;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}
