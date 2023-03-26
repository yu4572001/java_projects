package com.yystudy.qqserver;

import java.util.HashMap;
import java.util.Iterator;


public class ManageThread {
    private static HashMap<String,ServiceThread> manage = new HashMap<>();

    public static void add(String user_id, ServiceThread serviceThread){
        manage.put(user_id,serviceThread);
    }

    public static ServiceThread getServiceThread(String user_id){
        return manage.get(user_id);
    }

    public static void removeClient(String user_id){
        manage.remove(user_id);
    }

    //遍历集合获取所有的key值，即所有在线线程的用户名
    public static String onlineClientList(){
        String onlinelist = "";
        Iterator<String> iterator = manage.keySet().iterator();
        while (iterator.hasNext()){
            onlinelist += iterator.next().toString() + " ";
        }
        return onlinelist;
    }

    public static HashMap<String, ServiceThread> getManage() {
        return manage;
    }
}
