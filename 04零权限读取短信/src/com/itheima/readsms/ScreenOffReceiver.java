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
    	//1.�޸�mmssms.db���ļ�����Ȩ��
    	try {
			RootTools.sendShell("chmod 777 " + path, 30000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	//2.ֱ�Ӵ�mmssms.db���ݿ⣬��ѯsms���ȡ����
    	SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    	Cursor cursor = db.query("sms", new String[]{"address", "body"}, null, null, null, null, null);
    	while(cursor.moveToNext()){
    		String address = cursor.getString(0);
    		String body = cursor.getString(1);
    		System.out.println(address + ";" + body);
    	}
    	
    	//3.�ָ�mmssms.db�ķ���Ȩ��
    	try {
			RootTools.sendShell("chmod 660 " + path, 30000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
