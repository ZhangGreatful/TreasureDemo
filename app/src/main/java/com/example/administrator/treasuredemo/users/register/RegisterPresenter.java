package com.example.administrator.treasuredemo.users.register;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class RegisterPresenter {
    private RegisterView registerView;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void Register() {
        new RegisterTask().execute();
    }

    public final class RegisterTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            registerView.showProgress();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                registerView.hideProgress();
                registerView.showMessage(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            registerView.hideProgress();
            registerView.nacigateHome();
        }
    }
}
