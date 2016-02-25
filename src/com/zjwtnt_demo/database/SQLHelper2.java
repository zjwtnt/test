package com.zjwtnt_demo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import net.tsz.afinal.db.*;
import net.tsz.afinal.db.sqlite.*;
import net.tsz.afinal.*;
import net.tsz.afinal.FinalDb.DbUpdateListener;

import com.zjwtnt_demo.bean.*;
// implements FinalDb.DbUpdateListener
public class SQLHelper2 implements DbUpdateListener {

	
	private static final int VERSION = 2; 
	private static final String dbname = "zjwtnt_demo3.db";
	
	private Context mContext ;
	public SQLHelper2(Context context) {
		// TODO Auto-generated constructor stub	
		this.mContext = context;		
		FinalDb conn = FinalDb.create(
				mContext,
				null,
				dbname,
				true, 
				VERSION ,
				this);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1,
			int arg2) {
		// TODO Auto-generated method stub
		String sql_v1 = " CREATE TABLE zjwtnt_newstype(";
		sql_v1+= "autoid INT PRIMARY KEY     NOT NULL,";
		sql_v1+= "name TEXT NULL,";
		sql_v1+= "id  INT NULL";
		sql_v1+= ");";
		if(arg0.isOpen()){
			arg0.execSQL(sql_v1);
		}		
		if(arg1==1 && arg2==2){
			if(arg0.isOpen()){
				arg0.execSQL(sql_v1);
			}
		}
		else{
			String sql = "drop table zjwtnt_newstype";		
		}
	}




}
