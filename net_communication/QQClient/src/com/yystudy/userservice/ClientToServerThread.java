package com.yystudy.userservice;

import com.yystudy.qqcommon.MessType;
import com.yystudy.qqcommon.Message;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

//将socket放入一个线程中一直运行，用于通信
public class ClientToServerThread extends Thread {
    private Socket socket;

    public ClientToServerThread(Socket socket) {
        this.socket = socket;
    }

    //线程运行时要做的事情：一直等待接收服务器传送的数据
    @Override
    public void run() {
        while (true) {
            try {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                //一直等待接收信息，没有就阻塞
                Message message = (Message) ois.readObject();

                //处理收到的message信息
                //获取在线用户消息
                if (message.getMesType().equals(MessType.MESSAGE_RETURN_ONLINE)) {
                    String count = message.getContent();
                    String[] counts = count.split(" ");
                    System.out.println("\n====当前在线用户列表====");
                    for (int i = 0; i < counts.length; i++) {
                        System.out.println("用户：" + counts[i]);
                    }
                } else if (message.getMesType().equals(MessType.MESSAGE_COMM_MES)) {
                    System.out.println("\n" + message.getSrcUser() + "对你说："
                            + message.getContent() + "   "
                            + message.getSendTime());
                } else if (message.getMesType().equals(MessType.MESSAGE_ToAll_Client)) {
                    System.out.println("\n" + message.getSrcUser() + "群发消息：" + message.getContent() + "   " + message.getSendTime());

                } else if (message.getMesType().equals(MessType.MESSAGE_FILE_MESS)) {
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getDestFile());
                    fileOutputStream.write(message.getFilrbytes());
                    fileOutputStream.close();
                    System.out.println("\n用户" + message.getSrcUser() + "给你发的文件" + message.getDestFile() + "保存成功---" + message.getDestFile());

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Socket getSocket() {
        return socket;
    }
}
