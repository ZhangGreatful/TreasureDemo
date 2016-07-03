package com.example.administrator.treasuredemo.users.login;

import com.google.gson.annotations.SerializedName;

/**
 * 登录结果的响应实体
 * Created by Administrator on 2016/7/3 0003.
 */
public class LoginResult {

    //    "errcode": 1,                  //状态值
//            "errmsg": "登录成功！",        //返回信息
//            "headpic": "add.jpg",          //头像地址
//            "tokenid": 171                 //用户令牌
    @SerializedName("headpic")
    private String iconUrl;

    @SerializedName("tokenid")
    private int tokenId;

    @SerializedName("errcode")
    private int code;

    @SerializedName("errmsg")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public int getTokenId() {
        return tokenId;
    }

    public String getIconUrl() {
        return iconUrl;
    }


}
