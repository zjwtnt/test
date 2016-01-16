package com.zjwtnt_demo.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Gallery;
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
	private FocusAdapter adapter;
	private Gallery gallery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_main);
		TitleView com_header = (TitleView)findViewById(R.id.header);
		com_header.setTitleText(this.getResources().getString(R.string.title_activity_main));
		com_header.setLeftButtonShow(false);
		mactivity=this;
		focusimg();
		showlist();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void focusimg(){
		gallery=(Gallery)findViewById(R.id.gallery);
        adapter=new FocusAdapter(this);
        
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("focusImage",R.drawable.focus_1);
        map1.put("intro", "这是内容简介1这是内容简介1这是内容简介1这是内容简介1这是内容简介1这是内容简介1");
        
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("focusImage",R.drawable.focus_2);
        map2.put("intro", "这是内容简介2这是内容简介2这是内容简介2这是内容简介2这是内容简介2这是内容简介2");
         
        Map<String,Object> map3=new HashMap<String,Object>();
        map3.put("focusImage",R.drawable.focus_3);
        map3.put("intro", "这是内容简介3这是内容简介3这是内容简介3这是内容简介3这是内容简介3这是内容简介3");
        
        Map<String,Object> map4=new HashMap<String,Object>();
        map4.put("focusImage",R.drawable.focus_4);
        map4.put("intro", "这是内容简介4这是内容简介4这是内容简介4这是内容简介4这是内容简介4这是内容简介4");
        
        Map<String,Object> map5=new HashMap<String,Object>();
        map5.put("focusImage",R.drawable.focus_5);
        map5.put("intro", "这是内容简介5这是内容简介5这是内容简介5这是内容简介5这是内容简介5这是内容简介5");
        
        adapter.addFocus(map1);
        adapter.addFocus(map2);
        adapter.addFocus(map3);
        adapter.addFocus(map4);
        adapter.addFocus(map5);
        gallery.setAdapter(adapter);
    
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
