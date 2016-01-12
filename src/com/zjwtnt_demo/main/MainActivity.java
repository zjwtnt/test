package com.zjwtnt_demo.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.alibaba.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class MainActivity extends Activity {
	private static final String ACTIVITY_TAG="LogDemo";
	public static MainActivity mactivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TitleView com_header = (TitleView)findViewById(R.id.header);
		com_header.setTitleText(this.getResources().getString(R.string.title_activity_main));
		com_header.setLeftButtonShow(false);
		mactivity=this;
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
		
		//httpget ht = new httpget();				
		//String json = ht.Get("http://www.china-way.com/tmp.php");
		
		
		FinalHttp fh = new FinalHttp();
        fh.get("http://www.china-way.com/tmp.php", new AjaxCallBack(){

            @Override
            public void onSuccess(Object t) {
            	String json = t.toString();
            	if(json!=""){
            		//Map<String, news> map = JSON.parseObject(json, new TypeReference<Map<String,news>>(){});
            		List<news> map = JSON.parseArray(json, news.class);
            		ListView list = (ListView)findViewById(R.id.MyListView);
            		list.setAdapter(new IndexPicAdapter(map,getBaseContext()));
            	}
            }
   
            
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
    
            }

        });
        
    	//TextView tv = (TextView)findViewById(R.id.textview1);
    	//tv.setText("aaa="+json);
		//Log.v(ACTIVITY_TAG,json);
		
		
		
	}
}
