package com.crio.xmeme.dto;

public class UploadImageResponseDTO {
	private Long id;
	
    public UploadImageResponseDTO(Long id) {
        this.id = id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
