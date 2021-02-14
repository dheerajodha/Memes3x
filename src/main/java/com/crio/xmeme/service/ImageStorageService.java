package com.crio.xmeme.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.crio.xmeme.entity.Image;
import com.crio.xmeme.exception.ImageStorageException;
import com.crio.xmeme.exception.MyFileNotFoundException;
import com.crio.xmeme.repository.ImageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageStorageService {

    @Autowired
    private ImageRepository imageRepository;
    
    public Image storeImage(String name, String caption, String url) throws IOException {
        //Create new image object with the passed data
    	Image image = new Image(name, caption, url);

    	//And save it to our DB with the help of repository's 'save()' method
		return imageRepository.save(image);
    }

    public Image getImageById(Long id) {
    	//Return the image object from a passed 'id' if found, or else throw exception
        return imageRepository.findById(id)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + id));
    }
    
    public ArrayList<Image> getAllMemes() {
    	//Return list of image objects with the help of repository's "findAll()" method
        return (ArrayList<Image>) imageRepository.findAll();      
    }
    
}