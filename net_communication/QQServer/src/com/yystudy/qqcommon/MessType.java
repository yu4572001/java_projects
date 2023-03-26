package com.yystudy.qqcommon;


//定义消息类型
public interface MessType {
    String MESSAGE_LOGIN_SUCCESS = "1";    //表示登录成功
    String MESSAGE_LOGIN_FAIL = "0";     //表示登录失败
    String MESSAGE_COMM_MES = "2";       //普通消息
    String MESSAGE_GET_ONLINE = "3";   //要求返回在先人数
    String MESSAGE_RETURN_ONLINE = "4";   //返回在线人数
    String MESSAGE_CLIENT_EXIT = "5";    //客户端退出消息
    String MESSAGE_ToAll_Client = "6";    //群发消息类型
    String MESSAGE_FILE_MESS = "7";    //发送文件消息类型

}
