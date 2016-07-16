package com.example.administrator.treasuredemo.users.login;

import com.example.administrator.treasuredemo.net.NetClient;
import com.example.administrator.treasuredemo.users.UserApi;
import com.example.administrator.treasuredemo.users.Users;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class LoginPresenter extends MvpNullObjectBasePresenter<LoginView> {

    private Call<LoginResult> loginCall;


    public void login(Users user) {
        UserApi userApi = NetClient.getInstance().getUserApi();
        if (loginCall != null) loginCall.cancel();
        loginCall = userApi.login(user);
        loginCall.enqueue(callback);
    }

    private Callback<LoginResult> callback = new Callback<LoginResult>() {
        @Override
        public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
            getView().hideProgress();
//            是否成功
            if (response.isSuccessful()) {
//                取出响应体(retrofit已加gson转化器,注意接口的定义)
                LoginResult result = response.body();
                if (result == null) {
                    getView().showMessage("unknow error");
                    return;
                }
//                显示返回信息
                getView().showMessage(result.getMsg());
//                登录成功
                if (result.getCode() == 1) {
                    getView().navigateToHome();

                }
                return;
            }
            getView().showMessage("网络连接异常");
        }

        @Override
        public void onFailure(Call<LoginResult> call, Throwable t) {
            getView().hideProgress();
            getView().showMessage(t.getMessage());
        }
    };

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance && loginCall != null) loginCall.cancel();
    }
}
