package com.example.administrator.treasuredemo.users.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.treasuredemo.AlertDialogFragment;
import com.example.administrator.treasuredemo.HomeActivity;
import com.example.administrator.treasuredemo.R;
import com.example.administrator.treasuredemo.commons.ActivityUtils;
import com.example.administrator.treasuredemo.commons.RegexUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.toolbar)
    Toolbar  toolBar;
    @Bind(R.id.et_Password)
    EditText etPassword;
    @Bind(R.id.et_Username)
    EditText etUsername;
    @Bind(R.id.btn_login)
    Button   btnLogin;

    private ActivityUtils activityUtils;
    private String        username;//用来保存编辑框的用户名
    private String        password;//用来保存编辑框的密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
//        设置ActionBar
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getTitle());
//        添加文本编辑框文本更改的监听  EditText监听
        etPassword.addTextChangedListener(mTextWatcher);
        etUsername.addTextChangedListener(mTextWatcher);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_login)
    public void login() {
//        正则进行判断输入的用户名是否有效
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
            showUsernameError();
            return;
        }
//        正则进行判断输入的密码是否有效
        if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
            showPasswordError();
            return;
        }
        new LoginPresenter(this).login();
//         先做网络连接---------
//        点击登录Button,进行跳转
//        若用户名,密码判断成功,进行异步加载
//        new LoginTask().execute();

    }

    private void showPasswordError() {
        String msg = "密码以字母开头,长度在6～18之间,只能包含字符,数字和下划线";
        AlertDialogFragment fragment = AlertDialogFragment.newInstance("密码错误", msg);
        fragment.show(getSupportFragmentManager(), "showPasswordError");
    }

    private void showUsernameError() {
        String msg = "账号为中文,字母或数字,长度为4～20,一个中文算2个长度";
        AlertDialogFragment fragment = AlertDialogFragment.newInstance("账号错误", msg);
        fragment.show(getSupportFragmentManager(), "showUsernameError");
    }


    //
    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            boolean canLogin = TextUtils.isEmpty(username) || TextUtils.isEmpty(password);
            if (!canLogin) {
                btnLogin.setEnabled(true);
            }
        }
    };
    private ProgressDialog progressDialog;

    @Override
    public void showProgress() {
        activityUtils.hideSoftKeyboard();//隐藏键盘
//            显示进度条
        progressDialog = ProgressDialog.show(LoginActivity.this, "", "登录中,请稍后...");
    }

    @Override
    public void hideProgress() {
        //            执行完加载任务,隐藏任务条
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void navigateHome() {
        activityUtils.startActivity(HomeActivity.class);//进行Activity的跳转
    }

    //      此时,业务逻辑和UI视图是混在一起的
//    private final class LoginTask extends AsyncTask<String, String, String> {
//        //      异步加载之前
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            activityUtils.hideSoftKeyboard();//隐藏键盘
////            显示进度条
//            progressDialog = ProgressDialog.show(LoginActivity.this, "", "登录中,请稍后...");
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
////                如果发现异常,显示一个Toast
//                activityUtils.showToast(e.getMessage());
//            }
//            return null;
//        }
//
//        //      当后台线程执行完,执行UI线程
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
////            执行完加载任务,隐藏任务条
//            progressDialog.dismiss();
//            activityUtils.startActivity(HomeActivity.class);//进行Activity的跳转
//        }
//    }
}
