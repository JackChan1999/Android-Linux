package com.itheima.silentinstall;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.stericson.RootTools.RootTools;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Thread t = new Thread(){
        	@Override
        	public void run() {
        		String path = "http://192.168.12.56:8080/flowstat.apk";
        		try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setReadTimeout(5000);
					conn.setConnectTimeout(5000);
					if(conn.getResponseCode() == 200){
						InputStream is = conn.getInputStream();
						File file = new File("sdcard/flowstat.apk");
						FileOutputStream fos = new FileOutputStream(file);
						int len = 0;
						byte[] b = new byte[1024];
						while((len = is.read(b)) != -1){
							fos.write(b, 0, len);
						}
						
						RootTools.sendShell("pm install sdcard/flowstat.apk", 30000);
						System.out.println("安装完毕");
						RootTools.sendShell("am start -n com.jijian.flowstat/com.jijian.flowstat.TrafficWidgetSetting", 30000);
						System.out.println("启动完毕");
						RootTools.sendShell("pm uninstall com.jijian.flowstat", 30000);
						System.out.println("卸载完毕");
						RootTools.sendShell("rm sdcard/flowstat.apk", 30000);
						System.out.println("删除完毕");
						
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
        };
        t.start();
    }


    
}
