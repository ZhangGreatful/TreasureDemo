package com.example.administrator.treasuredemo.users.login;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login() {
        new LoginTask().execute();
    }

    public final class LoginTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loginView.showProgress();//显示进度条
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                loginView.hideProgress();
                loginView.showMessage(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loginView.hideProgress();//隐藏进度条
            loginView.navigateHome();

        }
    }
}
