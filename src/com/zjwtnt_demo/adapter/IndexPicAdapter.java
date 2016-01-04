package com.zjwtnt_demo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.zjwtnt_demo.main.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.http.AjaxCallBack;

import com.zjwtnt_demo.bean.*;


public class IndexPicAdapter  extends BaseAdapter{
    View [] itemViews;  
    private ArrayList titles=new ArrayList();
	private ArrayList texts=new ArrayList();
	private ArrayList thumbs=new ArrayList();
    private Context context2;
    private LayoutInflater inflater;
    
    public IndexPicAdapter(List<news> map,Context context){    	
    	super();
    	for(int i=0;i<map.size();i++){
    		news row = map.get(i);
    		titles.add((Object)row.getTitle());
    		texts.add(row.getTitle()+"...");
    		thumbs.add(row.getThumb());
    	}
    	int size = titles.size();
    	itemViews = new View[size]; 
        this.context2 = context;//getContext();//getLayoutInflater();//context2;
        inflater = LayoutInflater.from(context2);
        //this.inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
        for (int i=0; i<titles.size(); i++){  
            itemViews[i] = makeItemView(String.valueOf(titles.get(i)),
            		String.valueOf(texts.get(i)),
            		String.valueOf(thumbs.get(i))	
            				);  
        }
       
    }
    
 


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemViews.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemViews[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		return itemViews[position];
		/*
		  if (convertView == null){			   
			    return itemViews[position];
		  }
		  return convertView;
		*/
	}
	
	private View makeItemView(String strTitle, String strText,String strThumb) { 
		
		/*
        LayoutInflater inflater = (LayoutInflater)this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        */
		View itemView = inflater.inflate(R.layout.index_pic_list,null);
        // 使用View的对象itemView与R.layout.item关联  
        //View itemView = inflater.inflate(R.layout., null);  
         //ImageView image2 = (ImageView)itemView.findViewById(R.id.thumb);
         TextView title = (TextView)itemView.findViewById(R.id.title);
         TextView content = (TextView)itemView.findViewById(R.id.content);
         title.setText(strTitle);
         content.setText(strText);
        // 通过findViewById()方法实例R.layout.item内各组件
        /*
        TextView title = (TextView)itemView.findViewById(R.id.itemTitle);  
        title.setText(strTitle);  
        TextView text = (TextView)itemView.findViewById(R.id.itemText);  
        text.setText(strText);  
        ImageView image = (ImageView)itemView.findViewById(R.id.itemImage);  
        image.setImageResource(resId);  
          */
         ImageView image = (ImageView)itemView.findViewById(R.id.thumb);
        
         image.setAdjustViewBounds(true); 
         image.setMaxWidth(200);
         image.setMinimumWidth(10);
         image.setPadding(5, 5, 20, 5);
         //new FinalBitmap(context2).init().display(image, strThumb);
         FinalBitmap fb = FinalBitmap.create(context2);
         fb.configLoadingImage(R.drawable.loding);
         fb.display(image, strThumb);
        return itemView;  
    }  

}
