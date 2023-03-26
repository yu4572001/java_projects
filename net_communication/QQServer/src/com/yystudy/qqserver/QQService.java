package com.yystudy.qqserver;

import com.yystudy.qqcommon.MessType;
import com.yystudy.qqcommon.Message;
import com.yystudy.qqcommon.User;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class QQService {
    private ServerSocket  ss = null;

    //ConcurrentHashMap是线程同步安全的HashMap
    private static ConcurrentHashMap<String,User> validUser = new ConcurrentHashMap<>();

    //定义一些账号密码：
    static {
        validUser.put("100",new User("100","123456"));
        validUser.put("200",new User("200","123456"));
        validUser.put("300",new User("300","123456"));
        validUser.put("jack",new User("jack","123456"));
        validUser.put("rose",new User("rose","123456"));
    }

    //匹配集合中的账号密码
    public boolean checkUser(String user_id,String password){
        User user = validUser.get(user_id);
        if (user == null){
            return false;
        }
        if (!(user.getPassword().equals(password))){
            return false;
        }
        return true;
    }

    //判断某用户是否在线
    public static boolean isOnLine(String user){
        HashMap<String, ServiceThread> manage = ManageThread.getManage();
        if (manage.containsKey(user)){
            return true;
        }
        return false;
    }


    public QQService(){
        try {
            System.out.println("服务器在8888端口监听");
            ss = new ServerSocket(8888);
            //启动服务器通告线程
            new ServerNotice().start();
            //一直循环监听，和某个客户端连接后，会继续监听
            while (true){
                Socket socket = ss.accept();
                Message message = new Message();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                User user = (User)ois.readObject();

                //验证账号密码：
                if (checkUser(user.getUser_id(),user.getPassword())){
                    //登录成功则返回一个message信息，即MESSAGE_LOGIN_SUCCESS
                    message.setMesType(MessType.MESSAGE_LOGIN_SUCCESS);
                    oos.writeObject(message);
                    System.out.println("用户" + user.getUser_id() + "登录了聊天系统");

                    //创建一个线程供客户端的socket与服务器的socket保持连接
                    ServiceThread serviceThread = new ServiceThread(socket, user.getUser_id());
                    serviceThread.start();
                    //添加进集合中
                    ManageThread.add(user.getUser_id(), serviceThread);

                    //发送接收到的离线消息
                    if (OffLineMes.getOffLineMess().containsKey(user.getUser_id())){
                        ArrayList<Message> mes = OffLineMes.getOffLineMess().get(user.getUser_id());
                        Socket socket1 = ManageThread.getServiceThread(user.getUser_id()).getSocket();
                        ObjectOutputStream oos_ = new ObjectOutputStream(socket1.getOutputStream());
                        for (Message message1 : mes) {
                            oos_.writeObject(message1);
                        }
                        OffLineMes.remove(user.getUser_id());
                    }
                }else {
                    //登录失败
                    message.setMesType(MessType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
