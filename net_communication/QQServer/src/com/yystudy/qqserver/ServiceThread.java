package com.yystudy.qqserver;

import com.yystudy.qqcommon.MessType;
import com.yystudy.qqcommon.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ServiceThread extends Thread{
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private String user_id;
    public ServiceThread(Socket socket,String user_id){
        this.socket = socket;
        this.user_id = user_id;
    }

    //线程执行：保持与客户端的连接，持续接收数据与发送数据
    @Override
    public void run() {
        while (true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();

                //处理收到客户端的信息：
                //请求获取在线用户的信息
                if (message.getMesType().equals(MessType.MESSAGE_GET_ONLINE)){
                    System.out.println("用户 " + message.getSrcUser() + "请求获取在线用户列表");
                    String onlineClient = ManageThread.onlineClientList();

                    //将信息返回给客户端
                    Message re_message = new Message();
                    re_message.setContent(onlineClient);
                    re_message.setMesType(MessType.MESSAGE_RETURN_ONLINE);   //消息类型为返回用户列表信息
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(re_message);
                    //请求退出的消息
                }else if (message.getMesType().equals(MessType.MESSAGE_CLIENT_EXIT)){
                    System.out.println(message.getSrcUser() + "请求退出账号");
                    //将该通信线程从集合中去除
                    ManageThread.removeClient(message.getSrcUser());
                    //关闭soeket
                    socket.close();
                    //结束线程
                    break;
                    //发送普通聊天信息
                }else if (message.getMesType().equals(MessType.MESSAGE_COMM_MES)){
                    if (QQService.isOnLine(message.getDestUser())) {    //判断是否在线
                        ObjectOutputStream oos = new ObjectOutputStream(ManageThread.getServiceThread
                                (message.getDestUser()).socket.getOutputStream());
                        System.out.println(message.getSrcUser() + "对" + message.getDestUser()
                                + "说：" + message.getContent() + "   "
                                + message.getSendTime());
                        oos.writeObject(message);
                    }else {
                        if ((!OffLineMes.getOffLineMess().containsKey(message.getDestUser()))){
                            ArrayList<Message> messages = new ArrayList<>();
                            messages.add(message);
                            OffLineMes.add(message.getDestUser(),messages);
                        }else {
                            OffLineMes.getOffLineMess().get(message.getDestUser()).add(message);
                        }

                    }

                    //处理群发消息
                }else if (message.getMesType().equals(MessType.MESSAGE_ToAll_Client)){
                    Iterator<String> iterator = ManageThread.getManage().keySet().iterator();
                    while (iterator.hasNext()){
                        String onlineClient = iterator.next();
                        //将信息发给除了发送者以外的所有在线用户
                        if (!onlineClient.equals(message.getSrcUser())){
                            OutputStream outputStream = ManageThread.getServiceThread(onlineClient).socket.getOutputStream();
                            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                            oos.writeObject(message);
                        }
                    }
                    //处理转发接收到的文件信息
                }else if (message.getMesType().equals(MessType.MESSAGE_FILE_MESS)){
                    OutputStream outputStream = ManageThread.getServiceThread(message.getDestUser()).socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                    oos.writeObject(message);
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
