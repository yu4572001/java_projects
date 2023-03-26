package com.yystudy.qq_view;

import com.yystudy.userservice.ChatService;
import com.yystudy.userservice.ClientService;
import com.yystudy.utils.Utility;

public class QQView {
    private boolean loop = true;
    private String key = "";
    private String userid = "";
    private String password = "";
    private ClientService clientService = new ClientService();

    public static void main(String[] args) {
        new QQView().MainMenu();
    }
    public void  MainMenu(){
        while (loop){
            System.out.println("==================欢迎登录网络通信系统==================");
            System.out.println("\t\t1.登录系统");
            System.out.println("\t\t9.退出系统");
            System.out.print("请输入你的选择：");
            key = Utility.readString(5);
            switch (key){
                case "1":
                    System.out.println("登录系统");
                    System.out.print("请输入用户号：");
                    userid = Utility.readString(20);
                    System.out.print("请输入密  码：");
                    password = Utility.readString(20);

                    //这里调用判断账号密码是否正确方法，与服务器联系
                    if (clientService.checkUser(userid,password)){
                        System.out.println("==============欢迎(" + userid + ")用户==============");
                        System.out.println();
                        System.out.println();
                        SecondMenu();

                    }else {
                        System.out.println("密码错误！");
                    }
                    break;
                case "9":
                    System.out.println("系统退出");
                    loop = false;
                    break;

            }
        }
    }

    public void SecondMenu(){
        boolean loop_ = true;
        while (loop_){
            System.out.println("==================欢迎登录网络通信系统二级菜单(" + userid + ")==================");
            System.out.println("1.显示在线用户列表");
            System.out.println("2.群发消息");
            System.out.println("3.私聊消息");
            System.out.println("4.发送文件");
            System.out.println("9.退出系统");
            System.out.print("请输入你的选择：");
            key = Utility.readString(5);

            switch (key){
                case "1":
                    //当前在线用户列表
                    clientService.onlineClient();
                    break;
                case "2":
                    System.out.print("输入群发信息：");
                    String toAllMess = key = Utility.readString(100);
                    ChatService.sendMessToAll(userid,toAllMess);
                    break;
                case "3":
                    System.out.println("====私聊消息====");
                    System.out.println("请输入要聊天的用户名(在线)：");
                    String user = Utility.readString(20);
                    System.out.println("请输入私聊消息：");
                    String content = Utility.readString(100);
                    ChatService.sendMess(userid,user,content);
                    break;
                case "4":
                    System.out.println("====发送文件====");
                    System.out.print("请输入要发送的用户：");
                    String dest = Utility.readString(20);
                    System.out.print("请输入要发送的文件路径(e:\\\\src.jpg)：");
                    String srcfile = Utility.readString(100);
                    System.out.print("请输入文件接收者的保存路径(e:\\\\src.jpg)：");
                    String destfile = Utility.readString(100);
                    ChatService.toFileMess(srcfile,destfile,userid,dest);
                    break;
                case "9":
                    System.out.println("退出账号。。。");
                    clientService.exitUser();
                    loop_ = false;
                    break;
            }
        }
    }
}
