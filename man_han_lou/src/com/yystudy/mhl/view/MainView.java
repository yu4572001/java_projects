package com.yystudy.mhl.view;


import com.yystudy.mhl.domain.Employee;
import com.yystudy.mhl.domain.Menu;
import com.yystudy.mhl.domain.MutitableBean;
import com.yystudy.mhl.service.BillService;
import com.yystudy.mhl.service.EmployeeService;
import com.yystudy.mhl.service.FoodTableService;
import com.yystudy.mhl.service.MenuService;
import com.yystudy.mhl.utils.Utility;

import java.util.List;

//满汉楼主菜单视图
public class MainView {
    private boolean loop = true;
    private String key = "";
    private EmployeeService employeeSer = new EmployeeService();
    private FoodTableService foodTableSer = new FoodTableService();
    private MenuService menuSer = new MenuService();
    private BillService billSer = new BillService();
    public static void main(String[] args) {
        new MainView().menuView();
    }


    //预定餐桌方法
    public void bookingTab(){
        System.out.println("============预定餐桌============");
        foodTableSer.emptyTable();

        System.out.print("请输入要预定的餐桌编号(-1退出)：");
        int id = Utility.readInt();

        if (id == -1){
            System.out.println("============取消预定============");
            return;
        }

        char key = Utility.readConfirmSelection();
        if (key == 'Y'){
            System.out.print("请输入预定人的名字：");
            String name = Utility.readString(20);
            System.out.print("请输入预定人的联系电话：");
            String phone = Utility.readString(20);

            //调用预定方法
            if(foodTableSer.bookingTable(id,name,phone)){
                System.out.println("============预定成功============");
            }else {
                System.out.println("==========该餐桌无法预定==========");
            }

        }else {
            System.out.println("============取消预定============");
        }


    }

    //点餐服务
    public void orderMenu(){
        System.out.println("============点餐服务============");
        System.out.print("请选择点餐的桌号(-1退出)：");
        int tableid = Utility.readInt();
        if (tableid == -1){
            return;
        }

        //检查该桌号是否存在
        if (!(foodTableSer.checkTableis(tableid))){
            System.out.println("该桌号不存在。。。");
            return;
        }

        System.out.print("请选择要点的菜品编号：");
        int menuid = Utility.readInt();

        //检查该菜品是否存在
        Menu menyById = menuSer.getMenyById(menuid);
        if (menyById == null){
            System.out.println("该菜品不存在。。。");
            return;
        }

        System.out.print("请选择要点的数量：");
        int nums = Utility.readInt();
        if (nums < 0 || nums >10){
            System.out.println("请输入合理的数量。。。");
            return;
        }

        //如果上述都没有问题，则开始点餐
        if (billSer.orderMenu(tableid,menuid,nums)){
            System.out.println("============点餐成功============");
        } else {
            System.out.println("点餐失败。。。");
        }

    }


    //查看账单
    public void showAllBill(){
        List<MutitableBean> billList = billSer.getBillList();
        System.out.println("编号\t\t桌号\t\t菜单号\t\t菜品名\t\t数量\t\t金额\t\t日期\t\t\t\t\t\t\t状态");
        for (MutitableBean bill : billList) {
            System.out.println(bill);
        }
    }


    //结账服务
    public void payMoney(){
        System.out.println("============结账服务============");
        System.out.print("请选择要结账的餐桌编号(-1退出)：");
        int tableid = Utility.readInt();
        if (tableid == -1){
            System.out.println("退出结账。。。");
            return;
        }

        //查看该餐桌编号是否有未结账
        if (!(billSer.notPay(tableid))){
            System.out.println("该餐桌没有未结账信息");
            return;
        }

        System.out.print("请输入结账的方式(现金/支付宝/微信)：");
        String payMethod = Utility.readString(10,"");
        if ("".equals(payMethod)){
            System.out.println("取消结账。。。");
            return;
        }

        if (billSer.payBill(tableid, payMethod)){
            System.out.println("============结账成功============");
        }else {
            System.out.println("结账失败。。。");
        }
    }





    public void menuView(){
        while (loop){

            System.out.println("======================满汉楼======================");
            System.out.println("\t\t  1.登录满汉楼");
            System.out.println("\t\t  2.退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);
            switch (key){
                case "1":
                    System.out.println("登录满汉楼：");
                    System.out.print("请输入你的账户：");
                    String emp_id = Utility.readString(32);
                    System.out.print("请输入你的密码：");
                    String emp_pass = Utility.readString(32);
                    //检查账户密码：
                    Employee employee = employeeSer.checkEmployee(emp_id, emp_pass);

                    if (employee != null){    //如果返回的employee对象不为null，则代表有这个账户密码
                        System.out.println("===============登录成功,欢迎(" + employee.getName() +")===============");
                        //显示二级菜单
                        boolean secondLoop = true;
                        while (secondLoop){
                            System.out.println("=================满汉楼操作菜单=================");
                            System.out.println("\t\t  1 显示餐桌状态");
                            System.out.println("\t\t  2 预定餐桌");
                            System.out.println("\t\t  3 显示所有菜品");
                            System.out.println("\t\t  4 点餐服务");
                            System.out.println("\t\t  5 查看账单");
                            System.out.println("\t\t  6 结账");
                            System.out.println("\t\t  9 退出账户");
                            System.out.print("请输入你的操作数字：");
                            key = Utility.readString(1);

                            switch (key){
                                case "1":     //显示餐桌状态
                                    foodTableSer.getTableinfo();
                                    break;
                                case "2":     //预定餐桌
                                    bookingTab();
                                    break;
                                case "3":     //显示所有菜品
                                    menuSer.getAllFood();
                                    break;
                                case "4":     //点餐服务
                                    orderMenu();
                                    break;
                                case "5":    //查看账单
                                    showAllBill();
                                    break;
                                case "6":    //结账
                                    payMoney();
                                    break;
                                case "9":    //退出账户，返回上一级菜单
                                    secondLoop = false;
                                    break;
                                default:
                                    System.out.println("你的输入有误，请重新输入~");
                            }
                        }


                    }else {
                        System.out.println("登录失败，请验证你的账户密码");
                    }
                    break;
                case "2":
                    System.out.println("退出系统");
                    loop = false;
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入~");
            }
        }
    }


}
