package com.zjwtnt_demo.database;

import com.zjwtnt_demo.bean.news;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.db.*;

public class SQLHelper extends SQLiteOpenHelper {
	private Context mContext;
	private static final int VERSION = 1; 
	private static final String dbname = "zjwtnt_demo";
	
	public SQLHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
		// TODO Auto-generated constructor stub
	}

	public SQLHelper(Context context, String name, CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		mContext = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		FinalDb conn = FinalDb.create(mContext, "zjwtnt_demo.db", true);  
		news model = new news();
		model.setContent("ø’");
		model.setThumb("");
		model.setTitle("»± °±ÍÃ‚");
		model.setId("1");
		conn.save(model);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
