package com.yystudy.userservice;

import com.yystudy.qqcommon.MessType;
import com.yystudy.qqcommon.Message;

import java.io.*;
import java.util.Date;

//提供发送聊天，群发消息的方法，
public class ChatService {

    /**
     *
     * @param srcUser  发送用户id
     * @param destUser  接收用户id
     * @param content   信息内容
     */
    public static void sendMess(String srcUser,String destUser,String content){
        Message message = new Message();
        message.setSrcUser(srcUser);
        message.setDestUser(destUser);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        message.setMesType(MessType.MESSAGE_COMM_MES);

        try {
            //发送信息到服务器
            OutputStream outputStream =
                    ManageConnectThread.getThread(srcUser).getSocket().getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(message);

            System.out.println("你对" + message.getDestUser()
                    + "说：" + message.getContent() + "   "
                    + message.getSendTime());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    //群发消息方法
    public static void sendMessToAll(String srcUser,String content) {
        Message message = new Message();
        message.setSrcUser(srcUser);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        message.setMesType(MessType.MESSAGE_ToAll_Client);

        try {
            //发送信息到服务器
            OutputStream outputStream =
                    ManageConnectThread.getThread(srcUser).getSocket().getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(message);

            System.out.println("你对大家说：" + message.getContent() + "   "
                    + message.getSendTime());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //发送文件方法
    /**
     *
     * @param src    文件源路径
     * @param dest   文件目标路径
     * @param src_id    发送用户
     * @param dest_id   目标用户
     */
    public static void toFileMess(String src, String dest,String src_id,String dest_id){
        Message message = new Message();
        message.setMesType(MessType.MESSAGE_FILE_MESS);
        message.setSrcUser(src_id);
        message.setDestUser(dest_id);
        message.setDestFile(dest);
        //得到src路径文件的字节长度
        byte[] fileBytes = new byte[(int) new File(src).length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(src);
            //读取到内存中，即文件现在在fileBytes数组中
            fileInputStream.read(fileBytes);
            message.setFilrbytes(fileBytes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //输出到服务器
        try {
            OutputStream outputStream =
                    ManageConnectThread.getThread(src_id).getSocket().getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(src_id + "将路径" + src +
                "的文件" + "发送给了");
    }
}
