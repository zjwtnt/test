/**
 * 
 */
package com.zjwtnt_demo.net;
import net.tsz.afinal.*;
import net.tsz.afinal.http.AjaxCallBack;
/**
 * @author kawaycheng
 *
 */
public class httpget {

	FinalHttp fh = new FinalHttp();
	String content = "";
	String url = "";
	
	public httpget() {
		// TODO Auto-generated constructor stub
	}


	
	public String Get(String target_url){
		this.url = target_url;
		fh.get(url, new AjaxCallBack(){

		    @Override
            public void onSuccess(Object t) {
		    	content = t.toString();
            }
            
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            	content= strMsg;
            }
		});
		return content;
	}
	
	
	
}
