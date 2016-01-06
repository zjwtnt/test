package com.zjwtnt_demo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.zjwtnt_demo.main.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.http.AjaxCallBack;

import com.zjwtnt_demo.bean.*;


public class IndexPicAdapter  extends BaseAdapter{
    View [] itemViews;  
    private ArrayList titles=new ArrayList();
	private ArrayList texts=new ArrayList();
	private ArrayList thumbs=new ArrayList();
	private ArrayList ids=new ArrayList();
    private Context context2;
    private LayoutInflater inflater;
    
    public IndexPicAdapter(List<news> map,Context context){    	
    	super();
    	for(int i=0;i<map.size();i++){
    		news row = map.get(i);
    		titles.add((Object)row.getTitle());
    		texts.add(row.getTitle()+"...");
    		thumbs.add(row.getThumb());
    		ids.add(row.getId());
    	}
    	int size = titles.size();
    	itemViews = new View[size]; 
        this.context2 = context;//getContext();//getLayoutInflater();//context2;
        inflater = LayoutInflater.from(context2);
        /*
        inflater = LayoutInflater.from(context2);
        //this.inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
        for (int i=0; i<titles.size(); i++){  
            itemViews[i] = makeItemView(String.valueOf(titles.get(i)),
            		String.valueOf(texts.get(i)),
            		String.valueOf(thumbs.get(i)),
i
            		
            				);  
        }
        */
       
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
	    
		 ViewHolder holder = null;  
         MyListener myListener=null;  
         myListener=new MyListener(position);  
        if (convertView == null) {  
              
            holder=new ViewHolder();    
              
            //�������Ϊ��vlist��ȡview  ֮���view���ظ�ListView  
            
                 
            convertView = inflater.inflate(R.layout.index_pic_list, null);  
            holder.title =(TextView)convertView.findViewById(R.id.title);  
            holder.content = (TextView)convertView.findViewById(R.id.content);
            holder.image = (ImageView)convertView.findViewById(R.id.thumb);
           
            convertView.setTag(holder);               
        }else {               
            holder = (ViewHolder)convertView.getTag();  
        }         
        holder.id = Integer.parseInt(String.valueOf(ids.get(position)));
        holder.title.setText((String)titles.get(position));  
        holder.content.setText((String)texts.get(position));  
        //holder.image.setTag(position);  
        //��Button��ӵ����¼�  ���Button֮��ListView��ʧȥ����  ��Ҫ��ֱ�Ӱ�Button�Ľ���ȥ��
        holder.image.setClickable(true);
       // holder.image.setOnClickListener( myListener);  
        holder.image.setAdjustViewBounds(true); 
        holder.image.setMaxWidth(200);
        holder.image.setMinimumWidth(10);
        holder.image.setClickable(true);
        holder.image.setPadding(5, 5, 20, 5);        
        //holder.viewBtn.setOnClickListener(MyListener(position));  
        FinalBitmap fb = FinalBitmap.create(context2);
        fb.configLoadingImage(R.drawable.loding);
        fb.display(holder.image, String.valueOf(thumbs.get(position)));          		
        convertView.setOnClickListener(myListener);
		return convertView;
		/*
		  if (convertView == null){			   
			    return itemViews[position];
		  }
		  return convertView;
		*/
	}
	
	private View makeItemView(String strTitle, String strText,String strThumb,int position) { 
		
		/*
        LayoutInflater inflater = (LayoutInflater)this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        */
		View itemView = inflater.inflate(R.layout.index_pic_list,null);
        // ʹ��View�Ķ���itemView��R.layout.item����  
        //View itemView = inflater.inflate(R.layout., null);  
         //ImageView image2 = (ImageView)itemView.findViewById(R.id.thumb);
         TextView title = (TextView)itemView.findViewById(R.id.title);
         TextView content = (TextView)itemView.findViewById(R.id.content);
         title.setText(strTitle);
         content.setText(strText);
        // ͨ��findViewById()����ʵ��R.layout.item�ڸ����
        /*
        TextView title = (TextView)itemView.findViewById(R.id.itemTitle);  
        title.setText(strTitle);  
        TextView text = (TextView)itemView.findViewById(R.id.itemText);  
        text.setText(strText);  
        ImageView image = (ImageView)itemView.findViewById(R.id.itemImage);  
        image.setImageResource(resId);  
          */
         ImageView image = (ImageView)itemView.findViewById(R.id.thumb);
         MyListener myListener=null;
         myListener=new MyListener(position);  
         image.setAdjustViewBounds(true); 
         image.setMaxWidth(200);
         image.setMinimumWidth(10);
         image.setClickable(true);
         image.setPadding(5, 5, 20, 5);
         image.setOnClickListener(myListener);
         //new FinalBitmap(context2).init().display(image, strThumb);
         FinalBitmap fb = FinalBitmap.create(context2);
         fb.configLoadingImage(R.drawable.loding);
         fb.display(image, strThumb);
        return itemView;  
    }  

	
	//��ȡ���������  
    public final class ViewHolder {  
        public TextView title;  
        public TextView content;
        public ImageView image;
        public int id;  
    }  
    
    private class MyListener implements OnClickListener{  
        int mPosition;  
        public MyListener(int inPosition){  
            mPosition= inPosition;  
        }  
        @Override  
        public void onClick(View v) {  
            // TODO Auto-generated method stub  
            Toast.makeText(context2, String.valueOf(ids.get(mPosition)), Toast.LENGTH_SHORT).show();  
        }  
          
    } 
}
