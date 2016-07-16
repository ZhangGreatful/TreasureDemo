package com.example.administrator.treasuredemo.users.register;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public interface RegisterView extends MvpView {
    //    显示进度条
    void showProgress();

    //    隐藏进度条
    void hideProgress();

    //    显示提示信息Message
    void showMessage(String msg);

    //    跳转到HomeActivity
    void navigateToHome();
//      清理
    void clearEditView();
}
