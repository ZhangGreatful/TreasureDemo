package com.example.administrator.treasuredemo.mvpbase;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class MvpBasePresenter<V extends MvpBaseView> {
//      若直接传入MvpBaseView,则只能调用MvpBaseView里面的方法
//    不能调用子类里面的方法
//private MvpBaseView mvpBaseView;

    private V mvpBaseView;


    public MvpBasePresenter(V mvpBaseView) {
        this.mvpBaseView = mvpBaseView;
    }

    public V getMvpBaseView() {
        return mvpBaseView;
    }

}
