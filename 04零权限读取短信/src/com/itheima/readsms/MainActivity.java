package com.itheima.readsms;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.RootToolsException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }


    public void click(View v){
    	String path = "data/data/com.android.providers.telephony/databases/mmssms.db";
    	//1.修改mmssms.db的文件访问权限
    	try {
			RootTools.sendShell("chmod 777 " + path, 30000);
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	//2.直接打开mmssms.db数据库，查询sms表获取短信
    	SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    	Cursor cursor = db.query("sms", new String[]{"address", "body"}, null, null, null, null, null);
    	while(cursor.moveToNext()){
    		String address = cursor.getString(0);
    		String body = cursor.getString(1);
    		System.out.println(address + ";" + body);
    	}
    	
    	//3.恢复mmssms.db的访问权限
    	try {
			RootTools.sendShell("chmod 660 " + path, 30000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
}
