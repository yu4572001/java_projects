package com.yystudy.userservice;

import com.yystudy.qqcommon.MessType;
import com.yystudy.qqcommon.Message;
import com.yystudy.qqcommon.User;
import java.io.*;
import java.net.Socket;

//用户验证账号，密码，接收服务器的message
public class ClientService {
    private User user = new User();


    //向服务器验证账号，密码
    public boolean checkUser(String user_id, String password){
        user.setUser_id(user_id);
        user.setPassword(password);
        boolean temp = false;   //临时变量

        //向服务器发送账号，密码
        try {
            //连接服务器
            Socket socket = new Socket("127.0.0.1", 8888);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);

            //接收服务器传回的验证信息，是否登录成功
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();

            if (message.getMesType().equals(MessType.MESSAGE_LOGIN_SUCCESS)){   //成功登录
                //创建一个保持通信的线程Socket，定义类ClientToServerThread
                ClientToServerThread clientToServerThread = new ClientToServerThread(socket);
                clientToServerThread.start();
                temp = true;

                //创建一个管理线程的类，用集合HashMap来存放线程，因为有可能一个客户端开启了多个Socket通信线程
                //加入集合
                ManageConnectThread.add(user.getUser_id(),clientToServerThread);


            }else {
                //登录失败
                socket.close();
                oos.close();
                ois.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return temp;

    }

    //当前在线用户
    public void onlineClient(){
        Message message = new Message();
        message.setMesType(MessType.MESSAGE_GET_ONLINE);
        message.setSrcUser(user.getUser_id());

        //得到该对象的socket发送请求信息
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageConnectThread.getThread(user.getUser_id()).
                            getSocket().getOutputStream());
            oos.writeObject(message);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //安全退出方法，给服务器发送一条退出信息，要关闭socket
    public void exitUser(){
        Message message = new Message();
        message.setMesType(MessType.MESSAGE_CLIENT_EXIT);
        message.setSrcUser(user.getUser_id());
        try {
            OutputStream outputStream =
                    ManageConnectThread.getThread(user.getUser_id()).getSocket().getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(message);
            System.exit(0);   //结束进程
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
