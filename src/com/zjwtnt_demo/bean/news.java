package com.zjwtnt_demo.bean;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;


@Table(name = "zjw_user") //@Table 表示orm(对象关系映射)的表名  
public class news {
	@Id(column="autoid")
	private int autoid;
	public int getAutoid() {
		return autoid;
	}
	public void setAutoid(int autoid) {
		this.autoid = autoid;
	}
	private String id;
	private String title;
	private String thumb;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private String content;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the thumb
	 */
	public String getThumb() {
		return thumb;
	}
	/**
	 * @param thumb the thumb to set
	 */
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
}
