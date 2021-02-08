package com.crio.xmeme;

public class UploadImageResponse {
	private Long id;
	
    public UploadImageResponse(Long id) {
        this.id = id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
