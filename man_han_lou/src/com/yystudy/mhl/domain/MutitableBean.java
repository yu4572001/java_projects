package com.yystudy.mhl.domain;

import java.util.Date;

//演示多表查询以获取信息

/**
 * 通过再创建一个javaBean类集成两个表之间需要显示的字段信息，
 * 当中的属性要与这些字段信息一一对应(建议属性名与字段名一致，
 * 不一致的话可以通过sql语句中的as其一个与属性名相同的别名)，
 * 再创建一个该javabean的DAO类，提供无参构造器与set与get方法
 */
public class MutitableBean {
    private Integer id;
    private String billid;
    private Integer foodTableid;
    private Integer menuid;
    private String name;
    private Integer nums;
    private Double money;
    private Date billdate;
    private String state;

    public MutitableBean() {
    }

    //有参构造器可有可无，无参一定要有(反射要用)


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "\t\t\t" + name +
                "\t\t" + nums +
                "\t\t" + money +
                "\t" + billdate +
                "\t\t" + state;

    }
}
