package com.example.administrator.treasuredemo.users;

import com.example.administrator.treasuredemo.users.login.LoginResult;
import com.example.administrator.treasuredemo.users.register.RegisterResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 将用户模块API,转为java接口
 * Created by Administrator on 2016/7/14 0014.
 */
public interface UserApi {
    /****如果没有添加转化器,则返回的是Response***/
    /****
     * 我们添加了转化器(在NetClient中),所以返回的是RegisterResult对象
     ***/

    @POST("/Handler/UserHandler.ashx?action=register")
    Call<RegisterResult> register(@Body Users user);

    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginResult> login(@Body Users user);


}
