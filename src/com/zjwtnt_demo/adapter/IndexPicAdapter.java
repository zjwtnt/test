package com.zjwtnt_demo.adapter;



import java.util.ArrayList;

import com.zjwtnt_demo.main.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IndexPicAdapter  extends BaseAdapter{
    View [] itemViews;  
    private ArrayList titles=new ArrayList();
	private ArrayList texts=new ArrayList();
    private Context context;
    private LayoutInflater inflater;
    
    public IndexPicAdapter(Context context2){    	
    	
    	for(int i=0;i<100;i++){
    		titles.add((Object)(String.valueOf(i)+"����"));
    		texts.add("aaa�ʹ��� ȫ"+String.valueOf(i));
    	}
    	itemViews = new View[titles.size()]; 
        this.context = context2;
        inflater = LayoutInflater.from(context);
        //this.inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
        for (int i=0; i<titles.size(); i++){  
            itemViews[i] = makeItemView(String.valueOf(titles.get(i)),
            		String.valueOf(texts.get(i)));  
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
	
	private View makeItemView(String strTitle, String strText) { 
		
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
        return itemView;  
    }  

}
