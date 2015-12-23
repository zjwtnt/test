package com.zjwtnt_demo.adapter;



import com.zjwtnt_demo.main.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class IndexPicAdapter  extends BaseAdapter{
    View [] itemViews;  
    private String [] titles={"标题1","标题2","标题3","标题4"};  
	private String [] texts={"文本内容A","文本内容B","文本内容C","文本内容D"};  
    private Context context;
    private LayoutInflater inflater;
    
    public IndexPicAdapter(Context context, AttributeSet attrs){    	
    	itemViews = new View[titles.length];  
        this.context = context;
        for (int i=0; i<itemViews.length; ++i){  
            itemViews[i] = makeItemView(titles[i], texts[i]);  
        }
        inflater = LayoutInflater.from(context);
    }
    
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private View makeItemView(String strTitle, String strText) { 
		
		/*
        LayoutInflater inflater = (LayoutInflater)this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        */
		View itemView = inflater.inflate(R.layout.index_pic_list,null);
        // 使用View的对象itemView与R.layout.item关联  
        //View itemView = inflater.inflate(R.layout., null);  
          
        // 通过findViewById()方法实例R.layout.item内各组件
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
