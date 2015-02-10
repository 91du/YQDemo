package com.yq.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.yq.demo.fragmentviewpage.FragmentMainActivity;
import com.yq.demo.getAppInfo.AllAppActivity;
import com.yq.demo.utils.LogTool;

public class MainActivity extends Activity implements OnClickListener {
    Button getAppInfo;
    Button launcherApp;
    Button fragmentDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
        setButtonOnclickListenner();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent;
        switch (v.getId()) {
        case R.id.getAppInfo: // 获取应用信息
            intent = new Intent(this, AllAppActivity.class);
            LogTool.e("获取应用信息");
            startActivity(intent);
            break;
        case R.id.LauncherApp: // 启动第三方app
            LogTool.e("启动第三方app");
            intent = new Intent(this, com.yq.demo.launcherApp.LauncherAppActivity.class);
            startActivity(intent);
            break;
        case R.id.sharedpreferences: // 设置sharedpreferences
            LogTool.e("设置sharedpreferences");
            intent = new Intent(this, SetSharedPreferences.class);
            startActivity(intent);
            break;
        case R.id.fragment_demo:
            intent = new Intent(this, FragmentMainActivity.class);
            startActivity(intent);
            break;
        default:

            break;
        }
    }

    /**
     * 初始化界面
     */
    public void init() {
        getAppInfo = (Button) findViewById(R.id.getAppInfo);
        launcherApp = (Button) findViewById(R.id.LauncherApp);
        fragmentDemo = (Button) findViewById(R.id.fragment_demo);
    }

    /**
     * 注册按钮监听
     */
    public void setButtonOnclickListenner() {
        getAppInfo.setOnClickListener(this);
        launcherApp.setOnClickListener(this);
        fragmentDemo.setOnClickListener(this);
    }

}
