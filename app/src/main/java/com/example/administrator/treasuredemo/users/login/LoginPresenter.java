package com.example.administrator.treasuredemo.users.login;

import android.os.AsyncTask;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class LoginPresenter extends MvpNullObjectBasePresenter<LoginView> {
//    public LoginPresenter(LoginView mvpBaseView) {
//        super(mvpBaseView);
//    }
//    private LoginView loginView;
//
//    public LoginPresenter(LoginView loginView) {
//        this.loginView = loginView;
//    }

    public void login() {
        new LoginTask().execute();
    }

    public final class LoginTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getView().showProgress();//显示进度条
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                getView().hideProgress();
                getView().showMessage(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            getView().hideProgress();//隐藏进度条
            getView().navigateHome();

        }
    }
}
