package com.example.administrator.treasuredemo.users.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.treasuredemo.components.AlertDialogFragment;
import com.example.administrator.treasuredemo.home.HomeActivity;
import com.example.administrator.treasuredemo.R;
import com.example.administrator.treasuredemo.commons.ActivityUtils;
import com.example.administrator.treasuredemo.commons.RegexUtils;
import com.example.administrator.treasuredemo.users.Users;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册界面
 */
public class RegisterActivity extends MvpActivity<RegisterView, RegisterPresenter> implements RegisterView {
    @Bind(R.id.toolbar)
    Toolbar  toolBar;
    @Bind(R.id.et_Password)
    EditText etPassword;
    @Bind(R.id.et_Username)
    EditText etUsername;
    @Bind(R.id.et_Confirm)
    EditText etConfirm;
    @Bind(R.id.btn_Register)
    Button   btn_Register;

    private String        password;
    private String        username;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(RegisterActivity.this);
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getTitle());
        }

        etConfirm.addTextChangedListener(mTextWatcher);//EditText设置监听
        etUsername.addTextChangedListener(mTextWatcher);//EditText设置监听
        etPassword.addTextChangedListener(mTextWatcher);//EditText设置监听
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @OnClick(R.id.btn_Register)
    public void register() {
//    正则进行判断用户名是否正确
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
            showUsernameError();
            return;
        }
        if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
            showPasswordError();
            return;
        }
        getPresenter().register(new Users(username, password));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

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
            boolean canRegister = TextUtils.isEmpty(username) || TextUtils.isEmpty(password);
            if (!canRegister) {
                btn_Register.setEnabled(true);
            }
        }
    };
    private ProgressDialog progressDialog;

    @Override
    public void showProgress() {
        //            隐藏软键盘
        activityUtils.hideSoftKeyboard();
//            显示进度条
        progressDialog = ProgressDialog.show(RegisterActivity.this, "", "正在注册");
    }

    @Override
    public void hideProgress() {
//            执行完加载任务,进度条消失,
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void navigateToHome() {
        activityUtils.startActivity(HomeActivity.class);
        finish();
    }

    @Override
    public void clearEditView() {
        etPassword.setText("");
    }


}
