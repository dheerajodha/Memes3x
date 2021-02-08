package com.crio.xmeme;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "memes")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    private String name;
    
	private String caption;

    @Lob
    private String url;

    public Image() {

    }

    public Image(String name, String caption, String url) {
        this.name = name;
        this.caption = caption;
        this.url = url;
    }

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
