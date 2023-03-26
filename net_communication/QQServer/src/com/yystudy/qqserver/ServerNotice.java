package com.yystudy.qqserver;

import com.yystudy.qqcommon.MessType;
import com.yystudy.qqcommon.Message;
import com.yystudy.utils.Utility;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


//服务发布通告线程
public class ServerNotice extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println("请输入公告信息(exit退出布告)：");
            String notice = Utility.readString(200);
            if ("exit".equals(notice)){
                break;
            }

            Message message = new Message();
            message.setSrcUser("服务器");
            message.setSendTime(new Date().toString());
            message.setMesType(MessType.MESSAGE_ToAll_Client);
            message.setContent(notice);
            System.out.println("服务器对所有人说：" + notice);

            //向所有在线的用户发送这条信息（遍历线程集合）
            HashMap<String, ServiceThread> manage = ManageThread.getManage();
            Iterator<ServiceThread> iterator = manage.values().iterator();
            while (iterator.hasNext()){
                try {
                    OutputStream outputStream = iterator.next().getSocket().getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                    oos.writeObject(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
