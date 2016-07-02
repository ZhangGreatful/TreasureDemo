package com.example.administrator.treasuredemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.treasuredemo.commons.ActivityUtils;
import com.example.administrator.treasuredemo.users.login.LoginActivity;
import com.example.administrator.treasuredemo.users.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置ToolBar
 * 使用ButterKnife关联id
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_register)
    Button  btn_Register;
    @BindView(R.id.btn_login)
    Button  btn_Login;
    private ActivityUtils activityUtiles;

//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtiles = new ActivityUtils(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        设置ActionBart为ToolBar
        setSupportActionBar(toolbar);
//        添加左侧返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    //      点击返回按钮退出
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                activityUtiles.startActivity(LoginActivity.class);
                break;
            case R.id.btn_register:
                activityUtiles.startActivity(RegisterActivity.class);
                break;
        }
    }
}
