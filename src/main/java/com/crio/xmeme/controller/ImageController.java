package com.crio.xmeme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crio.xmeme.dto.ImageDetailsDTO;
import com.crio.xmeme.dto.ImageUpdateDTO;
import com.crio.xmeme.dto.UploadImageResponseDTO;
import com.crio.xmeme.entity.Image;
import com.crio.xmeme.exception.ResourceNotFoundException;
import com.crio.xmeme.repository.ImageRepository;
import com.crio.xmeme.service.ImageStorageService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

@RestController
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private ImageRepository imageRepository;
    
    @PostMapping("/memes")
    public UploadImageResponseDTO uploadFile(@ModelAttribute ImageDetailsDTO image) throws IOException {
    	
    	String name = image.getName();
    	String caption = image.getCaption();
    	String url = image.getUrl();
    	
    	//Saving the passed data into our database
    	Image img = imageStorageService.storeImage(name, caption, url);

    	//Returning the image id back
        return new UploadImageResponseDTO(img.getImageId());
    }
    
    @PatchMapping("/memes/{id}")
    public ResponseEntity<Void> updateImage(@PathVariable("id") long id, @RequestBody ImageUpdateDTO imageUpdateDto) {
    	try {
    		//Fetching the image object from the database using its 'id'
    		Image image = imageStorageService.getImageById(id);
        	
    		//If caption has been updated, update its value in the object as well
        	if (imageUpdateDto.getCaption() != null) {
        		image.setCaption(imageUpdateDto.getCaption());
        	}
        	
        	//If image url has been updated, update its value in the object as well
        	if (imageUpdateDto.getUrl() != null) {
        		image.setUrl(imageUpdateDto.getUrl());
        	}
        	
        	//Save our updated Object to the database
        	imageRepository.save(image);
        	
        	return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            // log exception first, then return Not Found (404)
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/memes")
    public ArrayList<Image> getAllMemes() {
    	//Fetch List of Memes from the database
    	ArrayList<Image> allMemes = imageStorageService.getAllMemes();
    	
    	/*
    	 * To check the data fetched from the database
    	 * 
    	for (Image i : allMemes) {
    		System.out.println("File name: " + i.getName() + " with caption: " + i.getCaption());
    	}*/
    	
    	return allMemes;
    }

    @GetMapping("/memes/{id}")
    public Image getMemeById(@PathVariable Long id) {
        // Load file from database
        Image image = imageStorageService.getImageById(id);

        return image;
    }

}