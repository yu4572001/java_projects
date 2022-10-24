package com.yysduty.houserent.house;

public class Domain {
    //房屋基本信息：
    //编号，房主，电话，地址，月租，状态
    private int id;
    private String name;
    private String phone;
    private String add;
    private int rent;
    private String state;


    public Domain(String name, String phone, String add, int rent, String state) {
        this.name = name;
        this.phone = phone;
        this.add = add;
        this.rent = rent;
        this.state = state;
    }


    //输出对象房屋基本信息
    @Override
    public String toString() {
        return id +
                "\t\t" + name +
                "\t" + phone +
                "\t" + add +
                "\t" + rent +
                "\t" + state;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
