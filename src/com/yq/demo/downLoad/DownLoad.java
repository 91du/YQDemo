package com.yq.demo.downLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.yq.demo.R;
import com.yq.demo.utils.LogTool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DownLoad extends Activity implements OnClickListener {
	TextView progress_textview;
	ProgressBar download_progress_bar;
	InputStream inStream;
	OutputStream outStream;
	Thread downloadThread;
	int fileLength;
	int DownloadFileLength;
	int WXZ = 1;
	int ZZXZ = 2;
	int XZWC = 3;
	String SDCardPath;
	String filePath;
	String urlPath = "";
	String fileName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		// "http://xiaocong.oss.aliyuncs.com/uploadfile/application/application/2013913142698000.apk";
		urlPath = "http://xiaocong.oss.aliyuncs.com/uploadfile/application/application/20148131131319000.apk";
		SDCardPath = Environment.getExternalStorageDirectory().toString();
		filePath = SDCardPath + "/YqDownload";
		fileName = urlPath.substring(urlPath.lastIndexOf("/") + 1);
		LogTool.e("fileName= " + fileName);
		init();
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (!Thread.currentThread().isInterrupted()) {
				switch (msg.what) {
				case 1:
					download_progress_bar.setMax(fileLength);
					break;
				case 2:
					download_progress_bar.setProgress(DownloadFileLength);
					int x = DownloadFileLength * 100 / fileLength;
					progress_textview.setText(x + "%");
					break;
				case 3:
					Toast.makeText(getApplicationContext(), "下载完成",
							Toast.LENGTH_SHORT).show();
					LogTool.e("下载完成");
					break;

				default:
					LogTool.e("moren");
					break;
				}
			}
		};
	};

	public boolean checkSDCardExist() {
		SDCardPath = Environment.getExternalStorageDirectory().toString();
		if (SDCardPath == null || SDCardPath == "") {
			LogTool.e("未检测到sd卡");
			return false;
		}
		return true;
	}

	public void makeDirs(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
			LogTool.e("建立文件夹" + path);
		}
	}

	public void startDownload(String urlPath, String filePath, String fileName) {
		if (checkSDCardExist()) {
			makeDirs(filePath);
		}

		try {
			URL url = new URL(urlPath);
			URLConnection conn = url.openConnection();
			inStream = conn.getInputStream();
			Message message1 = new Message();
			message1.what = WXZ;
			handler.sendMessage(message1);
			fileLength = conn.getContentLength();
			File file = new File(filePath + "/" + fileName);

			outStream = new FileOutputStream(file);
			byte[] buffer = new byte[1024 * 4];

			int len;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
				DownloadFileLength += len;
				Log.i("-------->", DownloadFileLength + "");
				Message message2 = new Message();
				message2.what = ZZXZ;
				handler.sendMessage(message2);
			}
			inStream.close();
			outStream.flush();
			outStream.close();
			Message message3 = new Message();
			message3.what = XZWC;
			handler.sendMessage(message3);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void init() {
		progress_textview = (TextView) findViewById(R.id.download_progress_bar_text);
		download_progress_bar = (ProgressBar) findViewById(R.id.download_progress_bar);

		findViewById(R.id.downLoad_continue).setOnClickListener(this);
		findViewById(R.id.download_start).setOnClickListener(this);
		findViewById(R.id.downLoad_stop).setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.download_start: // 开始
			LogTool.e("开始");
			downloadThread = new Thread() {
				@Override
				public void run() {
					startDownload(urlPath, filePath, fileName);
				}
			};
			downloadThread.start();
			break;
		case R.id.downLoad_stop: // 暂停
			LogTool.e("暂停");
			downloadThread.stop();
			break;
		case R.id.downLoad_continue: // 继续
			LogTool.e("继续");

			break;

		default:
			break;
		}

	}

}
