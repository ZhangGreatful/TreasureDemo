package com.example.administrator.treasuredemo.users.account;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.administrator.treasuredemo.R;
import com.example.administrator.treasuredemo.commons.ActivityUtils;
import com.example.administrator.treasuredemo.components.IconSelectWindow;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class AccountActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar   toolbar;
    @Bind(R.id.iv_userIcon)
    ImageView userIcon;

    private ActivityUtils    activityUtils;
    private IconSelectWindow iconSelectWindow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_account);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getTitle());
    }

    /**
     * 当在当前个人用户中心页面,按下icon,弹出PopupWindow
     */
    @OnClick(R.id.iv_userIcon)
    public void onClick() {
        if (iconSelectWindow == null) iconSelectWindow = new IconSelectWindow(this, listener);
        if (iconSelectWindow.isShowing()) {
            iconSelectWindow.dismiss();
            return;
        }
        iconSelectWindow.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(cropHandler, requestCode, resultCode, data);

    }

    private       CropHandler               cropHandler = new CropHandler() {
        //        剪切完成
        @Override
        public void onPhotoCropped(Uri uri) {
            File file = new File(uri.getPath());
            activityUtils.showToast(file.getPath());
        }

        @Override
        public void onCropCancel() {

        }

        @Override
        public void onCropFailed(String message) {

        }

        @Override
        public CropParams getCropParams() {
            CropParams cropParams = new CropParams();
            cropParams.aspectX = 300;
            cropParams.aspectY = 300;
            return cropParams;
        }

        @Override
        public Activity getContext() {
            return AccountActivity.this;
        }
    };
    private final IconSelectWindow.Listener listener    = new IconSelectWindow.Listener() {
        //        到相册进行图像选择
        @Override
        public void toGallery() {
//            首先,清理缓存
            CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
//
            Intent intent = CropHelper.buildCropFromGalleryIntent(cropHandler.getCropParams());
            startActivityForResult(intent, CropHelper.REQUEST_CROP);
        }

        //          到相机进行图像选择
        @Override
        public void toCamera() {
//            首先,清理缓存
            CropHelper.clearCachedCropFile(cropHandler.getCropParams().uri);
//
            Intent intent = CropHelper.buildCaptureIntent(cropHandler.getCropParams().uri);
            startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
        }
    };
}
