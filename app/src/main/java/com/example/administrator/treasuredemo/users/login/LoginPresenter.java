package com.example.administrator.treasuredemo.users.login;

import android.os.AsyncTask;

import com.example.administrator.treasuredemo.mvpbase.MvpBasePresenter;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class LoginPresenter extends MvpBasePresenter<LoginView> {
    public LoginPresenter(LoginView mvpBaseView) {
        super(mvpBaseView);
    }
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
            getMvpBaseView().showProgress();//显示进度条
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                getMvpBaseView().hideProgress();
                getMvpBaseView().showMessage(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            getMvpBaseView().hideProgress();//隐藏进度条
            getMvpBaseView().navigateHome();

        }
    }
}
