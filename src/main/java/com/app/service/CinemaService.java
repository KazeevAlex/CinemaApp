package com.app.service;

import com.app.domain.cinema.CinemaDomain;
import com.app.repos.CinemaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CinemaService {
    @Value("${upload.path}")
    private String uploadPath;

    private CinemaRepo cinemaRepo;
    @Autowired
    public CinemaService(CinemaRepo cinemaRepo) { this.cinemaRepo = cinemaRepo; }

    public void saveCinema(CinemaDomain cinemaDomain){ cinemaRepo.save(cinemaDomain); }

    public Set<String> saveImageArray(MultipartFile[] imageArray) {
        Set<String> imageSet = new HashSet<>();
        for (MultipartFile image : imageArray)
            imageSet.add(saveImage(image));
        return imageSet;
    }

    public String saveImage(MultipartFile image) {
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + image.getOriginalFilename();

        try {
            image.transferTo(new File(uploadPath + "/" + resultFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultFilename;
    }

}
