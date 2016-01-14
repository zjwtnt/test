package com.zjwtnt_demo.main;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import com.alibaba.fastjson.JSON;
import com.zjwtnt_demo.adapter.IndexPicAdapter;
import com.zjwtnt_demo.bean.news;
import com.zjwtnt_demo.views.TitleView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewNewsActivity extends Activity {
	private int id2;
	public static ViewNewsActivity mactivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_news);
		TitleView com_header = (TitleView)findViewById(R.id.header);
		com_header.setTitleText(this.getResources().getString(R.string.title_activity_main_details));
		//com_header.setLeftButtonShow(false);
		Bundle bundle = this.getIntent().getExtras();
		id2 =  Integer.parseInt(bundle.getString("id"));
		mactivity = this;
		//Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
		this.getDetails();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_news, menu);
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
	
	@SuppressWarnings("unchecked")
	private void getDetails(){
		ArrayList<news> news_list;
		Bundle bundle = mactivity.getIntent().getExtras();
		id2 =  Integer.parseInt(bundle.getString("id"));
	
		
		FinalHttp fh = new FinalHttp();
	
        fh.get("http://www.china-way.com/tmp.php", new AjaxCallBack(){

            @Override
            public void onSuccess(Object t) {
            	int id3 = id2;
            	String json = t.toString();
            	if(json!=""){
            		//Map<String, news> map = JSON.parseObject(json, new TypeReference<Map<String,news>>(){});
            		List<news> map = JSON.parseArray(json, news.class);
            		
            		for(int i=0;i<map.size();i++){
            			String tmp_id = map.get(i).getId();
            			if(tmp_id.equals(String.valueOf(id3))){
            				TextView tv_title = (TextView)findViewById(R.id.title);
            				WebView wv_content = (WebView)findViewById(R.id.wv_content);
            				tv_title.setText(map.get(i).getTitle());
            				//支持javascript
            				wv_content.getSettings().setJavaScriptEnabled(true); 
            				// 设置可以支持缩放 
            				wv_content.getSettings().setSupportZoom(true); 
            				// 设置出现缩放工具 
            				wv_content.getSettings().setBuiltInZoomControls(true);
            				//扩大比例的缩放
            				wv_content.getSettings().setUseWideViewPort(true);
            				//自适应屏幕
            				wv_content.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
            				wv_content.getSettings().setLoadWithOverviewMode(true);
            				wv_content.loadData(map.get(i).getContent(), "text/html", "GBK");
            				break;
            			}
            		}
            	}
            }
   
            
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
    
            }

        });
	}
}
