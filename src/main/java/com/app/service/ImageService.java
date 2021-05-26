package com.app.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface ImageService {
    String saveImage(MultipartFile image);

    Set<String> saveImageArray(MultipartFile[] imageArray);

    void deleteImage(String imageName);

    void deleteImageSet(Set<String> gallery);
}
