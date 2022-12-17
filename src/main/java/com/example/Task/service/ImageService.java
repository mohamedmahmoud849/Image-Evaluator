package com.example.Task.service;

import com.example.Task.model.ImageModel;
import com.example.Task.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ImageService {
    public final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void SaveImgDb(MultipartFile file , String description , String category){

        ImageModel im= new ImageModel();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a prober file name");
        }else{

            try {
                im.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        im.setCategory(category);
        im.setDescription(description);


        imageRepository.save(im);

    }
    public List<ImageModel> GetAllAcceptedImages(){

        return imageRepository.findALlByAcceptedIsTrue();

    }
    public List<ImageModel> GetAllUnprocessedImages(){

        return imageRepository.findAllByAcceptedIsNull();

    }

    public void UpdateImageStatus(Integer id,Boolean status){

        imageRepository.updateImageStatus(id, status);

    }

    public ImageModel FindImage(Integer id){

       return imageRepository.findByIdEquals(id);

    }

}
