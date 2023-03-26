package com.yystudy.qqcommon;
import java.io.Serializable;


//消息类
public class Message implements Serializable {
    private String srcUser;    //消息发送者
    private String destUser;    //消息接收者
    private String content;     //消息内容
    private String sendTime;     //发送时间
    private String mesType;     //消息类型
    private static final long serialVersionUID = 1L;
    private String srcFile;     //文件原路径
    private String destFile;    //文件目标路径

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public byte[] getFilrbytes() {
        return filrbytes;
    }

    public void setFilrbytes(byte[] filrbytes) {
        this.filrbytes = filrbytes;
    }

    private int fileLen = 0;
    private byte[] filrbytes;

    public String getSrcFile() {
        return srcFile;
    }

    public void setSrcFile(String srcFile) {
        this.srcFile = srcFile;
    }

    public String getDestFile() {
        return destFile;
    }

    public void setDestFile(String destFile) {
        this.destFile = destFile;
    }

    public String getSrcUser() {
        return srcUser;
    }

    public void setSrcUser(String srcUser) {
        this.srcUser = srcUser;
    }

    public String getDestUser() {
        return destUser;
    }

    public void setDestUser(String destUser) {
        this.destUser = destUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }
}
