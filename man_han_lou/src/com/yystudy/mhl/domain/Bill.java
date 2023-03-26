package com.yystudy.mhl.domain;

import java.util.Date;

public class Bill {
    private Integer id;
    private String billid;
    private Integer foodTableid;
    private Integer menuid;
    private Integer nums;
    private Double money;
    private Date billdate;
    private String state;

    public Bill() {
    }

    public Bill(Integer id, String billid, Integer foodTableid, Integer menuid, Integer nums, Double money, Date billdate, String state) {
        this.id = id;
        this.billid = billid;
        this.foodTableid = foodTableid;
        this.menuid = menuid;
        this.nums = nums;
        this.money = money;
        this.billdate = billdate;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public Integer getFoodTableid() {
        return foodTableid;
    }

    public void setFoodTableid(Integer foodTableid) {
        this.foodTableid = foodTableid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getBilldate() {
        return billdate;
    }

    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return id +
                "\t\t" + foodTableid +
                "\t\t" + menuid +
                "\t\t\t" + nums +
                "\t\t" + money +
                "\t" + billdate +
                "\t\t" + state;

    }
}
