package com.zjwtnt_demo.views;



import com.zjwtnt_demo.main.R;

import android.app.*;
import android.content.*;
import android.opengl.Visibility;
import android.util.*;
import android.view.*;
import android.widget.*;



public class TitleView extends FrameLayout {  
	  
    private Button leftButton;  
  
    private TextView titleText;  
  
    public TitleView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        LayoutInflater.from(context).inflate(R.layout.topbar, this);  
        titleText = (TextView) findViewById(R.id.title_text);  
        leftButton = (Button) findViewById(R.id.button_left);  
        leftButton.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                ((Activity) getContext()).finish();  
            }  
        });  
    }  
  
    public void setTitleText(String text) {  
        titleText.setText(text);  
    }  
  
    public void setLeftButtonText(String text) {  
        leftButton.setText(text);  
    }  
  
    public void setLeftButtonListener(OnClickListener l) {  
        leftButton.setOnClickListener(l);  
    }
    
    public void setLeftButtonShow(boolean isshow){
    	leftButton.setVisibility(isshow?View.VISIBLE:View.INVISIBLE);
    }
  
}  