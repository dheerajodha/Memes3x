package com.crio.xmeme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ImageRepository extends JpaRepository<Image, Long>{
	
}
