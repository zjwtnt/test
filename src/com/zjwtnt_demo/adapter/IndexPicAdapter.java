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
    private String [] titles={"����1","����2","����3","����4"};  
	private String [] texts={"�ı�����A","�ı�����B","�ı�����C","�ı�����D"};  
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
        // ʹ��View�Ķ���itemView��R.layout.item����  
        //View itemView = inflater.inflate(R.layout., null);  
          
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
