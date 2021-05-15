package com.app.service;

import com.app.domain.film.FilmDomain;
import com.app.domain.film.Type;
import com.app.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class FilmService {
    @Value("${upload.path}")
    private String uploadPath;

    FilmRepo filmRepo;

    @Autowired
    public FilmService(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    public void saveFilm(FilmDomain filmDomain) {
        filmRepo.save(filmDomain);
    }

    public Set<Type> convertStringArrayToTypeSet(String[] typesInStrings) {
        Set<Type> typeSet = new HashSet<>();

        for (Type type : Type.values()) {
            if (Arrays.asList(typesInStrings).contains(type.getAlias()))
                typeSet.add(type);
        }

        return typeSet;
    }

    public void savingImages(FilmDomain filmDomain, MultipartFile mainImage, MultipartFile[] galleryImages) {
        if (mainImage != null && !mainImage.getOriginalFilename().isEmpty() && galleryImages != null && galleryImages.length != 0) {

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();

            String resultFilename = innerProcessOfSavingImages(mainImage);
            filmDomain.setMainImage(resultFilename);

            Set<String> galleryImageSet = new HashSet<>();
            for (MultipartFile galleryImage : galleryImages) {
                resultFilename = innerProcessOfSavingImages(galleryImage);
                galleryImageSet.add(resultFilename);
            }

            filmDomain.setGalleryImages(galleryImageSet);
        }
    }

    private String innerProcessOfSavingImages(MultipartFile image) {
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
