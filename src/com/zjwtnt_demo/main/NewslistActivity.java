package com.zjwtnt_demo.main;

import java.util.List;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import com.alibaba.fastjson.JSON;
import com.zjwtnt_demo.adapter.IndexPicAdapter;
import com.zjwtnt_demo.adapter.IndexPicAdapter.ViewHolder;
import com.zjwtnt_demo.adapter.IndexPicAdapter2;
import com.zjwtnt_demo.bean.news;
import com.zjwtnt_demo.views.TitleView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

public class NewslistActivity extends Activity {

	
    private ListView listview;
    private View footer;
    private List<news> data;
    private List<news> data2;
    private newsAdapter adapter;
    private IndexPicAdapter2 adapter2;
    private int pageCount = 0;
    private int pageSize = 5;
    private int currPage = 1;
    private int totalPage = 3;
    private boolean isfinsh = false;//是否加载完成
    private int ispager = 0;
    private ListView list;
    public static NewslistActivity mactivity;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newslist);
		TitleView com_header = (TitleView)findViewById(R.id.header);
		com_header.setTitleText(this.getResources().getString(R.string.title_activity_newslist));
		com_header.setLeftButtonShow(true);
		
		adapter2 = new IndexPicAdapter2(getBaseContext());
		list = (ListView)findViewById(R.id.MyListView);
        list.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
				
				if(!isfinsh && ispager==1 && scrollState == OnScrollListener.SCROLL_STATE_IDLE && currPage<100 && currPage<=totalPage){
      	
        			
					FinalHttp fh = new FinalHttp();	
					 fh.get(
				        	"http://www.china-way.com/tmp.php?pageindex="+String.valueOf(currPage)+"&pagesize="+String.valueOf(pageSize), 
				        	new AjaxCallBack(){
				            @Override
				            public void onSuccess(Object t) {
				            	String json = t.toString();
				            	if(json!=""){
				            		//data = JSON.parseArray(json, news.class);
				            		List<news> tmp = JSON.parseArray(json, news.class);
				            		int tmp_size = tmp.size();
				            		for(int i=0;i<tmp_size;i++){
				            			data.add(tmp.get(i));
				            		}
				            		adapter2.bindData(data);
				            		showlist();
				            		
				            		Toast.makeText(getBaseContext(), "正在获取更多数据...",500).show();
				            	}
				            	
				            }
				            @Override
				            public void onFailure(Throwable t, int errorNo, String strMsg) {
				            	Log.v("a", strMsg);
				            }

				        });
				}
				else{
					if( currPage>totalPage && !isfinsh){
					Toast.makeText(
							getBaseContext(),
							"已全部加载",
							Toast.LENGTH_SHORT).show();
					 isfinsh = true;
					}
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				 ispager = (firstVisibleItem + visibleItemCount == totalItemCount)?1:0;
			}

            
          });  		
		//读数据
		FinalHttp fh = new FinalHttp();	
		 fh.get(
	        	"http://www.china-way.com/tmp.php?pageindex="+String.valueOf(currPage)+"&pagesize="+String.valueOf(pageSize), 
	        	new AjaxCallBack(){
	            @Override
	            public void onSuccess(Object t) {
	            	String json = t.toString();
	            	if(json!="" && json!=null){

	            		//data.addAll(tmp);
	            		data = JSON.parseArray(json, news.class);
	            		
	            		if(data.size()>0){
	            			adapter2.bindData(data);
	            		}
	            		showlist();
	            		 
	            		
	            	}
	            }
	            @Override
	            public void onFailure(Throwable t, int errorNo, String strMsg) {
	            	Log.v("a", strMsg);
	            }

	        });
	}

	private void showlist(){
		/*
	     adapter = new newsAdapter(this,R.layout.index_pic_list);
	     for(int i=0;i<data.size();i++){
	    	 adapter.add(data.get(i));
	     }
	     */
         // 必须在setAdapter之前把head和Footer设置好
         //footer = getLayoutInflater().inflate(R.layout.loader_footer, null);
         
         //list.setAdapter(adapter);
		//data.remove(null);
		
		if(currPage==1){
			
			list.setAdapter(adapter2);
			
		}
		else{
			//if(currPage>=2){
			//	list.setAdapter(adapter2);
			//}
			//data.add(data2.get(0));
		}
		
		if(totalPage>=currPage){
			adapter2.notifyDataSetChanged();
		}
	
		currPage++;
        
        
         
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.newslist, menu);
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
	
	
	class newsAdapter extends ArrayAdapter<news>{
		private int mResourceId;
		public newsAdapter(Context context, int resource) {
			super(context, resource);
			this.mResourceId = resource;
		}

	    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
	    	news model = getItem(position);
            
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(mResourceId, null);
            ViewHolder holder = new ViewHolder();
            MyListener myListener=null;  
            myListener=new MyListener(position); 
            
            if (convertView == null) {              	
            	//View itemView = inflater.inflate(R.layout.index_pic_list,null);
            	convertView  = view;
                holder.title = (TextView)convertView.findViewById(R.id.title);  
                holder.content = (TextView)convertView.findViewById(R.id.content);
                holder.image = (ImageView)convertView.findViewById(R.id.thumb);               
                convertView.setTag(holder);               
            }else {               
                holder = (ViewHolder)convertView.getTag();  
            }   
            holder.id = Integer.parseInt(String.valueOf(model.getId()));
            holder.title.setText((String)model.getTitle());  
            holder.content.setText((String)model.getContent());  
            convertView.setOnClickListener(myListener);
            return view;
        }
	    
	   

	
		
	}
	
	
	 private class MyListener implements OnClickListener{  
		 
	        int mPosition;  
	        public MyListener(int inPosition){  
	            mPosition= inPosition;  
	        }  
	        @Override  
	        public void onClick(View v) {  
	            // TODO Auto-generated method stub 
	        	news model = data.get(mPosition);
	            Intent intent = new Intent(NewslistActivity.mactivity,ViewNewsActivity.class);
	            Bundle bundle=new Bundle();
	            bundle.putString("id", String.valueOf(model.getId()));
	            //用intent.putExtra(String name, String value);来传递参数。
	            intent.putExtras(bundle);              
	            //intent.setClass(MainActivity.mactivity ViewNewsActivity.class);
	            //context2.startActivity(intent);
	            NewslistActivity.mactivity.startActivity(intent);
	            //Toast.makeText(context2, String.valueOf(ids.get(mPosition)), Toast.LENGTH_SHORT).show();  
	        }  
	          
	    }
    
    
	//提取出来方便点  
    public final class ViewHolder {  
        public TextView title;  
        public TextView content;
        public ImageView image;
        public int id;  
    }

}


