package com.example.administrator.treasuredemo.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.treasuredemo.R;
import com.example.administrator.treasuredemo.commons.ActivityUtils;
import com.example.administrator.treasuredemo.users.account.AccountActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar        toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout   drawerLayout;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    private ImageView ivUserIcon;

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        navigationView.setNavigationItemSelectedListener(this);//对navigationView的menu进行监听
        //--------------------------------------------------------------------------------
/**
 * Activity activity,
 * DrawerLayout drawerLayout,
 Toolbar toolbar,
 @StringRes int openDrawerContentDescRes,
 @StringRes int closeDrawerContentDescRes
 *
 */
        //      为toolbar左上角添加动画
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,           //Drawer  抽屉
                toolbar,                //ActionBar
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //---------------------------------------------------------------------------------
        ivUserIcon = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_userIcon);
        ivUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityUtils.startActivity(AccountActivity.class);
            }
        });
    }

    //    创建OptionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_hide:
                activityUtils.showToast(R.string.hide_treasure);
                break;
        }
//        返回true,当前选项变为checked状态
        return false;
    }

    @Override
    public void onBackPressed() {
//        是不是打开的(左)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }
}
