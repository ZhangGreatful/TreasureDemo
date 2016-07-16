package com.example.administrator.treasuredemo.users.register;

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
public class RegisterPresenter extends MvpNullObjectBasePresenter<RegisterView> {

    private Call<RegisterResult> registerCall;

    public void register(Users user) {
        UserApi userApi = NetClient.getInstance().getUserApi();
        if (registerCall != null) registerCall.cancel();
        registerCall = userApi.register(user);
        registerCall.enqueue(callback);
    }

    private Callback<RegisterResult> callback = new Callback<RegisterResult>() {
        @Override
        public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
            if (response.isSuccessful()) {
                RegisterResult result = response.body();
                if (result == null) {
                    getView().showMessage("unknow error");
                    return;
                }
                getView().showMessage(result.getMsg());
                if (result.getCode() == 1) {
                    getView().navigateToHome();
                }
                return;
            }
            getView().showMessage("网络连接异常");
        }

        @Override
        public void onFailure(Call<RegisterResult> call, Throwable t) {
            getView().hideProgress();
            getView().showMessage(t.getMessage());
        }
    };

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (registerCall != null) registerCall.cancel();
    }
}
