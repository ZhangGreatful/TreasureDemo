package com.example.administrator.treasuredemo.users;

import com.google.gson.annotations.SerializedName;

/**
 * 用户实体类
 * Created by Administrator on 2016/7/3 0003.
 */
public class Users {

    @SerializedName("UserName")
    private String username;

    @SerializedName("Password")
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }

}
