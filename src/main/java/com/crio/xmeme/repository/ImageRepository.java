package com.crio.xmeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.xmeme.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
	
}
