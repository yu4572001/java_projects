package com.yysduty.smallchangesys;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSysCode {
    //属性
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");          //日期格式化
    Date date = null;
    Scanner myscanner = new Scanner(System.in);
    private boolean loop = true;
    private String chioce;           //选择
    private double balance;         //余额
    private String detail = " ";          //明细详情
    private String type;            //明细方式
    private double income;           //收入
    private double paid;            //支出


   //程序入口
    public void start(){
        do{
            this.printMenu();
            switch (chioce){
                case "1":
                    this.detail();
                break;
                case "2":
                    this.income_();
                    break;
                case "3":
                    this.paid_();
                    break;
                case "4":
                    this.exit();
                    break;
                default:
                    System.out.println("选择有误，请重新选择！");
            }

        }while (loop);
    }



    //首页菜单
    public void printMenu(){
        System.out.println("================零钱通菜单================");
        System.out.println("\t\t\t1 零钱通明细");
        System.out.println("\t\t\t2 收益入账");
        System.out.println("\t\t\t3 消     费");
        System.out.println("\t\t\t4 退     出");
        System.out.print("请输入(1-4)：");
        chioce = myscanner.next();
    }

    //零钱账单
    public void detail(){
        System.out.println("-----------------零钱通明细-----------------");
        System.out.println(detail);

    }

    //收入金额
    public void income_(){
        System.out.print("请输入入账金额：");
        //验证金额合法
        income = myscanner.nextDouble();
        if (income <= 0){
            System.out.println("请输入合法金额！");
            return;
        }

        date = new Date();        //获取当前日期
        balance = balance + income;
        //入账后，记录该条信息，拼接到上一条的信息
        detail = detail + "\n收益入账：" + "+" + income + "\t" + sdf.format(date) + "\t" + "余额：" + balance;
    }


    //支出
    public void paid_(){
        System.out.print("请输入消费方式：");
        type = myscanner.next();
        System.out.print("请输入消费金额：");

        //验证金额合法
        paid = myscanner.nextDouble();
        if (paid <= 0 || paid > balance){
            System.out.println("请输入(0-" + balance + ")之间的金额！");
            return;
        }

        date = new Date();      //获取当前日期
        balance = balance - paid;
        //入账后，记录该条信息，拼接到上一条的信息
        detail = detail + "\n" + type + "\t-"+ paid + "\t" + sdf.format(date) + "\t" + "余额：" + balance;
    }


    //退出程序
    public void exit(){
        String confirm = " ";          //验证输出合法性
        while (true){
            System.out.print("请问你要退出吗？(y/n)");
            confirm = myscanner.next();
            if ("y".equals(confirm) || "n".equals(confirm)){
                break;
            }
        }

        if ("y".equals(confirm)){
            System.out.println("你已退出程序...");
            loop = false;          //退出程序
        }

    }
}


