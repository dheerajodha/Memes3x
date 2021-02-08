package com.crio.xmeme;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.crio.xmeme.exception.ImageStorageException;
import com.crio.xmeme.exception.MyFileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageStorageService {

    @Autowired
    private ImageRepository imageRepository;
    
    public Image storeImage(String name, String caption, String url) throws IOException {
        Image image = new Image(name, caption, url);

		return imageRepository.save(image);
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + id));
    }
    
    public ArrayList<Image> getAllMemes() {
    	System.out.println("***************************");
        return (ArrayList<Image>) imageRepository.findAll();      
    }
    
}