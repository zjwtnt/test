package com.zjwtnt_demo.main;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import net.tsz.afinal.*;
import net.tsz.afinal.http.AjaxCallBack;

import com.zjwtnt_demo.adapter.*;
import com.zjwtnt_demo.bean.*;
import com.zjwtnt_demo.views.*;
import com.zjwtnt_demo.net.*;

public class MainActivity extends Activity {
	private static final String ACTIVITY_TAG="LogDemo";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TitleView com_header = (TitleView)findViewById(R.id.header);
		com_header.setTitleText(this.getResources().getString(R.string.title_activity_main));
		com_header.setLeftButtonShow(false);
		showlist();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void showlist(){
		ArrayList<news> news_list;
		
		httpget ht = new httpget();				
		String json = ht.Get("http://www.china-way.com/tmp.php");
		
		/*
		FinalHttp fh = new FinalHttp();
        fh.get("http://www.china-way.com/tmp.php", new AjaxCallBack(){

            @Override
            public void onSuccess(Object t) {
            	TextView tv = (TextView)findViewById(R.id.textview1);
            	tv.setText(t.toString());
            }
            
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            	//textview1.setText(strMsg);
            	TextView tv = (TextView)findViewById(R.id.textview1);
            	tv.setText(strMsg);
            }

        });
        */
    	TextView tv = (TextView)findViewById(R.id.textview1);
    	tv.setText("aaa="+json);
		Log.v(ACTIVITY_TAG,json);
		ListView list = (ListView)findViewById(R.id.MyListView);
		
		list.setAdapter(new IndexPicAdapter(this));
	}
}
