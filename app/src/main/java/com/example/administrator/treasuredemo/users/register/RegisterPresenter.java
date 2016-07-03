package com.example.administrator.treasuredemo.users.register;

import android.os.AsyncTask;

import com.example.administrator.treasuredemo.mvpbase.MvpBasePresenter;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class RegisterPresenter extends MvpBasePresenter<RegisterView> {

    //    private RegisterView registerView;
//
//    public RegisterPresenter(RegisterView registerView) {
//        this.registerView = registerView;
//    }
    public RegisterPresenter(RegisterView mvpBaseView) {
        super(mvpBaseView);
    }

    public void Register() {
        new RegisterTask().execute();
    }

    public final class RegisterTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getMvpBaseView().showProgress();
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
            getMvpBaseView().hideProgress();
            getMvpBaseView().nacigateHome();
        }
    }
}
