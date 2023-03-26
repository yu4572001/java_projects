package com.yystudy.userservice;

import java.util.HashMap;

public class ManageConnectThread {
    //K - 用户名  V - 启动的线程
    private static HashMap<String, ClientToServerThread> manage = new HashMap<>();


    public static void add(String user_id,ClientToServerThread ctst){
        manage.put(user_id,ctst);
    }

    public static ClientToServerThread getThread(String user_id){
        return manage.get(user_id);
    }

}
