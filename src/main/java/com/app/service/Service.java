package com.app.service;

import com.app.domain.Domain;
import com.app.domain.cinema.CinemaDomain;
import com.app.repos.Repo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class Service implements CrudService, ImageService{
    @Value("${upload.path}")
    private String uploadPath;

    public final Repo<Domain, Long> repo;
    public Service(Repo<Domain, Long> repo) { this.repo = repo; }

    @Override
    public void save(Domain domain) {
        repo.save(domain);
    }

    @Override
    public Domain getById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Page<Domain> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }


    @Override
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

    @Override
    public Set<String> saveImageArray(MultipartFile[] imageArray) {
        Set<String> imageSet = new HashSet<>();
        for (MultipartFile image : imageArray)
            imageSet.add(saveImage(image));
        return imageSet;
    }

    @Override
    public void deleteImage(String imageName) {
        File imageFile = new File(uploadPath + "/" + imageName);
        imageFile.delete();
    }

    @Override
    public void deleteImageSet(Set<String> gallery){
        for(String image : gallery)
            deleteImage(image);
    }
}
