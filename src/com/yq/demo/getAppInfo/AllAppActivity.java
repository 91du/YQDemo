package com.yq.demo.getAppInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yq.demo.R;
 
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
 
public class AllAppActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_app_info_avtivity);
        Get();
        ArrayList<AllAppInfo> list=Get();
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            AllAppInfo allAppInfo = (AllAppInfo) iterator.next();
            Log.e("qyq", allAppInfo.getAppname()+" |"+allAppInfo.getPackagename()+"| "+allAppInfo.getVersionCode()+"|"+allAppInfo.getLastInstal()+"|"+allAppInfo.getInstalPath());
        }
    }
    
    @SuppressLint("NewApi")
	private ArrayList<AllAppInfo> Get(){
    ArrayList<AllAppInfo> appList = new ArrayList<AllAppInfo>();  
    List<PackageInfo> packageInfos=getPackageManager().getInstalledPackages(1);
    for (int i = 0; i < packageInfos.size(); i++) {
        PackageInfo pInfo=packageInfos.get(i);
        AllAppInfo allAppInfo=new AllAppInfo();
        allAppInfo.setAppname(pInfo.applicationInfo.loadLabel(getPackageManager()).toString());//应用程序的名称
        allAppInfo.setPackagename(pInfo.packageName);//应用程序的包名
        allAppInfo.setVersionCode(pInfo.versionCode);//版本号
        allAppInfo.setLastInstal(pInfo.firstInstallTime);
        //allAppInfo.setProvider(pInfo.providers);
        allAppInfo.setInstalPath(pInfo.applicationInfo.sourceDir);
        allAppInfo.setAppicon(pInfo.applicationInfo.loadIcon(getPackageManager()));
        appList.add(allAppInfo);
    }
    return appList;
    }
}