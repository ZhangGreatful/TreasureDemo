package com.example.administrator.treasuredemo.net;

import com.example.administrator.treasuredemo.users.UserApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class NetClient {

    public static final String BASE_URL = "http://admin.syfeicuiedu.com";
    private static NetClient sClient;

    private final OkHttpClient client;
    private final Retrofit     retrofit;
    private final Gson         gson;
    private       UserApi      userApi;

    private NetClient() {
//        将gson设置为非严格模式
        gson = new GsonBuilder().setLenient().create();

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                添加gson转换器(注意添加依赖)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public UserApi getUserApi() {
        if (userApi == null) {
            userApi = retrofit.create(UserApi.class);
        }
        return userApi;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public static NetClient getInstance() {
        if (sClient == null) {
            sClient = new NetClient();
        }
        return sClient;
    }


}
