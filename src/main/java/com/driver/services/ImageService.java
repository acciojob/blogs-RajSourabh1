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
        String str = image.getDimensions();

        int imageLength = 0;
        int imageBreadth = 0;

        for(int i = 0; i<str.length(); i++){
            if(str.charAt(i) == 'X'){
                imageLength = imageBreadth;
                imageBreadth = 0;
                continue;
            }
            imageBreadth *= 10;
            imageBreadth += (str.charAt(i) - '0');
        }


        int screenLength = 0;
        int screenBreadth = 0;
        for(int i = 0; i<screenDimensions.length(); i++){
            if(screenDimensions.charAt(i) == 'X'){
                screenLength = screenBreadth;
                screenBreadth = 0;
                continue;
            }
            screenBreadth *= 10;
            screenBreadth += (screenDimensions.charAt(i) - '0');
        }

        int ans = (int)(Math.floor((new Double(screenLength))/(new Double(imageLength)))*Math.floor((new Double(screenBreadth))/(new Double(imageBreadth))));
        return ans;
    }
}
