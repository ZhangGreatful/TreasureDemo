package com.example.administrator.treasuredemo.users.login;

import android.os.AsyncTask;
import android.util.Log;

import com.example.administrator.treasuredemo.commons.LogUtils;
import com.example.administrator.treasuredemo.net.NetClient;
import com.example.administrator.treasuredemo.users.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class LoginPresenter extends MvpNullObjectBasePresenter<LoginView> {
    private static final String TAG = "LoginPresenter";
    private final String    URL       = "http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=login";
    private final MediaType mediaType = MediaType.parse("text/*");
    private Gson gson;

    public LoginPresenter() {
        gson = new GsonBuilder().setLenient().create();//非严格模式
    }

    public void login(Users users) {
        new LoginTask().execute(users);
    }

    public final class LoginTask extends AsyncTask<Users, String, LoginResult> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getView().showProgress();//显示进度条
        }

        @Override
        protected LoginResult doInBackground(Users... params) {
            Users users = params[0];
//            构建请求体
            RequestBody requestBody = RequestBody.create(mediaType, gson.toJson(users));
//            构建请求
            Request request = new Request.Builder().url(URL)
                    .post(requestBody)
                    .build();
            //     构建一次呼叫请求,得到当前这个呼叫(请求,响应)对象
            Call call = NetClient.getInstance().getClient().newCall(request);
            try {
//                执行这次操作,得到响应
                Response response = call.execute();
//                对响应进行判断
                if (response.isSuccessful()) {
//                    从响应中取出响应体
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    LogUtils.d("-------------body :" + body);
//                    将字符串body-->LoginResult实体对象
                    LoginResult loginResult = gson.fromJson(body, LoginResult.class);
                    return loginResult;
                }

            } catch (IOException e) {
                e.printStackTrace();
                publishProgress(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "onProgressUpdate: --------");
            getView().hideProgress();
            getView().showMessage(values[0]);
        }

        @Override
        protected void onPostExecute(LoginResult result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute: -----------");
            getView().hideProgress();
            if (result != null) {
                if (result.getCode() == 1) {
                    getView().navigateHome();
                } else if (result.getCode() == 2) {
                    getView().showMessage("此用户已被锁住!无法正常登录");
                } else if (result.getCode() == 3) {
                    getView().showMessage("用户名不存在!请先注册成会员再登录");
                } else if (result.getCode() == 4) {
                    getView().showMessage("密码错误！");
                } else if (result.getCode() == 5) {
                    getView().showMessage("此用户已登录");
                }
            }

        }
    }
}
