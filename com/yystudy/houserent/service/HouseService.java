package com.yysduty.houserent.service;

import com.yysduty.houserent.house.Domain;
import com.yysduty.houserent.utils.Utility;

public class HouseService {
    private Domain houses[];      //对象数组
    private int houCounter = 1;      //记录当前存了多少个房屋
    private int idIndex = 0;     //id号自增
    private int size = 10;       //对象数组大小

    //初始化房屋对象，默认有一个jeck，id为0
    public HouseService(int size_) {
        houses = new Domain[size_];
        //房屋默认信息
        houses[0] = new Domain("jeck","123456","北京市",3000,"未出租");
        houses[0].setId(0);
    }

    /**
     * 以下用于提供房屋出租系统各选项功能的方法
     */


    //增加房源
    public void addHouse(){
        System.out.println("---------------添加房屋---------------");
        System.out.print("姓名：");
        String name = Utility.readString(6);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(15);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态：");
        String state = Utility.readString(3);
        Domain newhouse = new Domain(name, phone, address, rent, state);
        if (isAdd(newhouse)){      //调用isAdd()方法
            System.out.println("房屋添加成功");
        }else {
            System.out.println("房屋添加失败");
        }
    }



    //将添加的房屋对象加入数组并设置id
    public boolean isAdd(Domain newhouse){
        if (houCounter == houses.length){        //判断数组中是否满
            arrAdd();       //房屋对象数组扩容，增加10个空间
        }
        //设置id值并自增
        newhouse.setId(++idIndex);
        //将此对象加入对象数组,且对象数组中数量加1
        houses[houCounter++] = newhouse;
        return true;
    }



    //打印房屋列表
    public void houseList(){
        System.out.println("---------------房屋列表---------------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null){
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println();
    }


    //对象数组扩容
    public void arrAdd(){
        size = size + 10;
        Domain houseadd[] = new Domain[size];
        for (int i = 0; i < houses.length; i++) {
            houseadd[i] = houses[i];    //将原本数组中的对象放到新数组中
        }
        houses = houseadd;       //地址赋值，指向原本数组
    }


    //根据房屋id号查找到该房屋，并返回其在对象数组中的下标值
    public int isExist(int n){
        for (int i = 0; i < houCounter; i++) {
            if (n == houses[i].getId()){
                System.out.println("---------------查找成功---------------");
                System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
                System.out.println(houses[i]);
                return i;
            }
        }
        return -1;
    }


    //查找房屋
    public void selectHouse(){
        System.out.print("请输入你要查找的房屋id：");
        int n = Utility.readInt();
        if (isExist(n) != -1){
            System.out.println("查找完毕...");
        }else {
            System.out.println("没有该房屋信息...");
        }
    }



    //删除房屋
    public void deleteHouse(){
        System.out.print("请输入你要删除的房屋id：(-1 退出)");
        int n = Utility.readInt();
        if (n == -1){
            return;
        }
        int index = isExist(n);     //记录要删除的元素的下标值
        if (index != -1){
            System.out.println("你确定要删除这个房屋信息吗？");
            char key = Utility.readConfirmSelection();
            if (key == 'Y'){
                for (int i = index; i < houCounter - 1; i++) {
                    houses[i] = houses[i+1];       //元素前移
                }
                houses[--houCounter] = null;     //移动之后，将最后一个元素指空，同时已存储元素减1
                System.out.println("删除成功...");
            }
        }else {
            System.out.println("没有该房屋信息...");
        }
    }


    public void update(){
        System.out.print("请输入你想要更新的房屋id：(-1 退出)");
        int n = Utility.readInt();
        if (n == -1){
            return;
        }
        int index = isExist(n);     //得到下标
        if (index != -1){
            System.out.println("请输入更新信息：");
            System.out.print("姓名：");
            String name = Utility.readString(6,houses[index].getName());
            houses[index].setName(name);
            System.out.print("电话：");
            String phone = Utility.readString(12,houses[index].getPhone());
            houses[index].setPhone(phone);
            System.out.print("地址：");
            String address = Utility.readString(15,houses[index].getAdd());
            houses[index].setAdd(address);
            System.out.print("月租：");
            int rent = Utility.readInt(houses[index].getRent());
            houses[index].setRent(rent);
            System.out.print("状态：");
            String state = Utility.readString(3,houses[index].getState());
            houses[index].setState(state);

            System.out.println("更新完毕...");
            System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
            System.out.println(houses[index]);

        }else {
            System.out.println("没有该房屋信息...");
        }
    }




}
