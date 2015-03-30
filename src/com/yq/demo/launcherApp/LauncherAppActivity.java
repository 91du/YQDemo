package com.yq.demo.launcherApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.yq.demo.R;

public class LauncherAppActivity extends Activity implements OnClickListener {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_app);
        button = (Button) findViewById(R.id.Button1);
        button.setOnClickListener(this);
    }

    // 启动指定App
    private int launchApp(String packageName) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
        startActivity(intent);
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.Button1:
            launchApp("com.yq.popwindow");
            break;

        default:
            break;
        }
    }

}
