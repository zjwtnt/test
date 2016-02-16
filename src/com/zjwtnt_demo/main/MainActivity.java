package com.zjwtnt_demo.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import net.tsz.afinal.*;
import net.tsz.afinal.http.AjaxCallBack;

import com.zjwtnt_demo.adapter.*;
import com.zjwtnt_demo.bean.*;
import com.zjwtnt_demo.views.*;
import com.zjwtnt_demo.net.*;
import com.alibaba.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MainActivity extends Activity implements OnPageChangeListener {
	private static final String ACTIVITY_TAG="LogDemo";
	public static MainActivity mactivity;
	private FocusAdapter adapter;
	private Gallery gallery;
	
	private ViewPager viewPager;
	private ArrayList<View> list;
	private ImageView imageView;
	private ImageView[] imageViews;
	private ViewGroup  group;
	
	private List<news> data;
	private View footer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_main);
		TitleView com_header = (TitleView)findViewById(R.id.header);
		com_header.setTitleText(this.getResources().getString(R.string.title_activity_main));
		com_header.setLeftButtonShow(false);
		mactivity=this;
		
       
        
		//focusimg();
		getfocusimg();
		showlist();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void getfocusimg(){
		list = new ArrayList<View>();
		FinalHttp fh = new FinalHttp();	
        fh.get(
        		"http://www.china-way.com/tmp.php", 
        		new AjaxCallBack(){

            @Override
            public void onSuccess(Object t) {
            	String json = t.toString();
            	if(json!=""){
            		//Map<String, news> map = JSON.parseObject(json, new TypeReference<Map<String,news>>(){});
            		List<news> map = JSON.parseArray(json, news.class);            		
            		for(int i=0;i<map.size();i++){
            			if(i<5){
	            			news tmp = (news)map.get(i);
	            			//ImageView iv = new ImageView(mactivity); 
	            			
	            			
	            	       
	            	        LayoutInflater inflater = mactivity.getLayoutInflater();
	            	        View vi = inflater.inflate(R.layout.item01, null);
	            	        ImageView iv = (ImageView)vi.findViewById(R.id.imageView1);
	            	        iv.setScaleType(ScaleType.FIT_XY);
	            	        FinalBitmap fb = FinalBitmap.create(mactivity);
	            	        iv.setOnClickListener(	new OnClickListener(){
		    			public void onClick(View v){
		    				//创嬄广告点击位置
		    				int i = viewPager.getCurrentItem();
		    				Log.i("TAG", "This page was clicked: "+viewPager.getCurrentItem());
		    			}
		    		}	);
	            	        fb.configLoadingImage(R.drawable.loding);
	            	        fb.display(iv, String.valueOf(map.get(i).getThumb()));
	            	        mactivity.list.add(vi);
	            	        //mactivity.list.add(iv,null);
            			}
            		}
            		focusimg2();
            	}
            }
   
            
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
    
            }

        });
	}
	
	private void focusimg2(){		
		LayoutInflater inflater = getLayoutInflater();
		
		/*
		list.add(inflater.inflate(R.layout.item01, null));
		list.add(inflater.inflate(R.layout.item02, null));
		list.add(inflater.inflate(R.layout.item03, null));
		list.add(inflater.inflate(R.layout.item04, null));
		list.add(inflater.inflate(R.layout.item05, null));
		*/
		
		
        		
		
		
		imageViews = new ImageView[list.size()];
		
	    ViewGroup group = (ViewGroup)findViewById(R.id.viewGroup);
	    viewPager = (ViewPager)findViewById(R.id.viewPager);
	    
	    for(int i=0;i<list.size();i++){
	    	imageView = new ImageView(this);
	    	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20,20);
	    	lp.setMargins(10,0, 10, 0);
	    	imageView.setLayoutParams(lp);//new LayoutParams(12,12)	    	
	    	imageViews[i] = imageView;
	    	if(i == 0){
	    		imageView.setBackgroundResource(R.drawable.page_indicator_focused);
	    	}else{
	    		imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
	    	}
	
	    	group.addView(imageView);
	    }
	    

	    viewPager.setAdapter(new MyAdapter());
	    viewPager.setOnPageChangeListener(this);
	    viewPager.setCurrentItem(0);
	}
	
	private void focusimg(){
		/*
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
        */
    
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
		ListView list = (ListView)findViewById(R.id.MyListView);
		View footer = getLayoutInflater().inflate(R.layout.loader_footer, null);
		list.addFooterView(footer);
		footer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(mactivity,"oh year", Toast.LENGTH_SHORT).show();
				//弹出层，分页显示信息
	            Intent intent = new Intent(mactivity,NewslistActivity.class);
	            mactivity.startActivity(intent);
			}
			
		});
		//list.setOnClickListener((OnClickListener) this);
		list.setOnItemClickListener(
				new OnItemClickListener() {

				    @Override
				    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				    	//parent.getAdapter().getItem(position);
				    	//Toast.makeText(mactivity,String.valueOf(position), Toast.LENGTH_SHORT).show();
				    }
				}
				);
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
	

	
	public class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		@Override  
        public int getItemPosition(Object object) {  
            return super.getItemPosition(object);  
        }

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			 ((ViewPager) container).addView(list.get(position%list.size()),0);  
			return list.get(position%list.size());
		}  
		
		
	     @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
  
        }


		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager)container).removeView(list.get(position%list.size()));
		}
		
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setImageBackground(arg0%list.size());
		
	}	
	private void setImageBackground( int selectItems){
		for(int i=0; i<imageViews.length; i++){
			if(i == selectItems){
				imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);
			}else{
				imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
}
