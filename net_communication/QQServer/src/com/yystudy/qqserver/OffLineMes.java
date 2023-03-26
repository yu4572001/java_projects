package com.yystudy.qqserver;
import com.yystudy.qqcommon.Message;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class OffLineMes {
    //存放离线信息集合
    private static ConcurrentHashMap<String, ArrayList<Message>> offLineMess = new ConcurrentHashMap<>();

    public static void add(String user,ArrayList<Message> messages){
        offLineMess.put(user,messages);
    }

    public static void remove(String user){
        offLineMess.remove(user);
    }

    public static ConcurrentHashMap<String, ArrayList<Message>> getOffLineMess() {
        return offLineMess;
    }

}
