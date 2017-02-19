package com.itheima.readsms;

import com.stericson.RootTools.RootTools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ScreenOffReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String path = "data/data/com.android.providers.telephony/databases/mmssms.db";
    	//1.修改mmssms.db的文件访问权限
    	try {
			RootTools.sendShell("chmod 777 " + path, 30000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
