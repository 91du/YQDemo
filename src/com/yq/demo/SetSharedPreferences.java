package com.yq.demo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SetSharedPreferences extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick_WriteData(View view) {
        SharedPreferences mySharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("name", "karl");
        editor.putString("habit", "sleep");
        editor.commit();
        Toast.makeText(this, "数据成功写入SharedPreferences！", Toast.LENGTH_LONG).show();

    }

    public void onClick_ReadData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String habit = sharedPreferences.getString("habit", "");

        Toast.makeText(this, "读取数据如下：" + "\n" + "name：" + name + "\n" + "habit：" + habit, Toast.LENGTH_LONG).show();

    }
}