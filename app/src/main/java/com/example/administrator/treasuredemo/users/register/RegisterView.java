package com.example.administrator.treasuredemo.users.register;

import com.example.administrator.treasuredemo.mvpbase.MvpBaseView;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public interface RegisterView extends MvpBaseView {
    //    显示进度条
    void showProgress();

    //    隐藏进度条
    void hideProgress();

    //    显示提示信息Message
    void showMessage(String msg);

    //    跳转到HomeActivity
    void nacigateHome();
}
