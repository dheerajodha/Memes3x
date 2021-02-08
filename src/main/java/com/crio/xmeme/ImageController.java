package com.crio.xmeme;

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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

@RestController
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageStorageService imageStorageService;

    @PostMapping("/memes")
    public UploadImageResponse uploadFile(@ModelAttribute ImageDetails image) throws IOException {
    	
    	String name = image.getName();
    	String caption = image.getCaption();
    	String url = image.getUrl();
    	
    	Image img = imageStorageService.storeImage(name, caption, url);

        return new UploadImageResponse(img.getImageId());
    }

    @GetMapping("/memes")
    public ArrayList<Image> getAllMemes() {
    	ArrayList<Image> allMemes = imageStorageService.getAllMemes();
    	
    	for (Image i : allMemes) {
    		System.out.println("File name: " + i.getName() + " with caption: " + i.getCaption());
    	}
    	
    	return allMemes;
    }

    @GetMapping("/memes/{id}")
    public Image getMemeById(@PathVariable Long id) {
        // Load file from database
        Image image = imageStorageService.getImageById(id);

        /* return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(image.getCaption());
                */
        return image;
    }

}