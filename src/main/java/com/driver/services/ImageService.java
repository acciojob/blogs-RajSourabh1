package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);



        imageRepository2.save(image);

        return image;
    }

    public void deleteImage(Image image){
       imageRepository2.delete(image);
    }

    public Image findById(int id) {
      return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        int count = 0;
        String str = image.getDimensions();
            int lenScreen = 0;
            int breScreen = 0;
            for(int i = 0;i<screenDimensions.length();i++){
                if(screenDimensions.charAt(i)=='X'){
                    lenScreen = breScreen;
                    breScreen = 0;
                    continue;
                }
                breScreen *=10;
                breScreen +=(screenDimensions.charAt(i)-'0');
            }
            int lenImage = 0;
            int breImage = 0;
        for(int i = 0;i<screenDimensions.length();i++){
            if(screenDimensions.charAt(i)=='X'){
                lenImage = breImage;
                breImage = 0;
                continue;
            }
            breImage *=10;
            breImage +=(screenDimensions.charAt(i)-'0');
        }
         count = (lenScreen/lenImage) * (breScreen/breImage);
            return count;
    }
}
