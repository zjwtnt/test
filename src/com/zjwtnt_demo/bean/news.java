package com.zjwtnt_demo.bean;

public class news {
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
