package com.crio.xmeme.dto;

public class ImageUpdateDTO {
	String caption;
	String url;
	
	public ImageUpdateDTO() {}
	
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
