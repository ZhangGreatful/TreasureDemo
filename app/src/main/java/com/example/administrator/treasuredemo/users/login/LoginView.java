package com.example.administrator.treasuredemo.users.login;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public interface LoginView {
    //      显示进度条
    void showProgress();

    //    隐藏进度条
    void hideProgress();

    //    显示信息Message
    void showMessage(String msg);

    //    跳转页面Activity
    void navigateHome();

}
