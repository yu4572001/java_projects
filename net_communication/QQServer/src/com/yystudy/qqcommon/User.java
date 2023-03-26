package com.yystudy.qqcommon;
import java.io.Serializable;


//用户类
public class User implements Serializable {
    private String user_id;      //用户id
    private String password;     //用户密码

    private static final long serialVersionUID = 1L;

    public User(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
