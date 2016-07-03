package com.example.administrator.treasuredemo.users.register;

import android.os.AsyncTask;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class RegisterPresenter extends MvpNullObjectBasePresenter<RegisterView> {

    //    private RegisterView registerView;
//
//    public RegisterPresenter(RegisterView registerView) {
//        this.registerView = registerView;
//    }
//    public RegisterPresenter(RegisterView mvpBaseView) {
//        super(mvpBaseView);
//    }

    public void Register() {
        new RegisterTask().execute();
    }

    public final class RegisterTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getView().showProgress();
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
            getView().hideProgress();
            getView().nacigateHome();
        }
    }
}
