package com.app.service;

import com.app.domain.film.FilmDomain;
import com.app.domain.film.Type;
import com.app.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    public void deleteFilmById(Long filmId) {
        FilmDomain filmDomain = filmRepo.findById(filmId).get();

        deleteImages(filmDomain.getMainImage());
        for (String imageString : filmDomain.getGalleryImages())
            deleteImages(imageString);

        filmRepo.deleteById(filmId);
    }

    public FilmDomain getFilmById(Long filmId) {
        return filmRepo.findById(filmId).get();
    }

    public Page<FilmDomain> findAll(Pageable pageable) {
        return filmRepo.findAll(pageable);
    }


    public Set<Type> convertStringArrayToTypeSet(String[] typesInStrings) {
        Set<Type> typeSet = new HashSet<>();

        for (String type : typesInStrings)
            typeSet.add(Type.valueOf(type));

        return typeSet;
    }

    public void changeValuesOfTypeSet(FilmDomain filmDomain, String[] typesInString) {
        Set<Type> typeSet = filmDomain.getTypes();
        typeSet.clear();
        for (String type : typesInString)
            typeSet.add(Type.valueOf(type));
    }

    public void saveFilmImages(FilmDomain filmDomain, MultipartFile mainImage, MultipartFile[] galleryImages) {
        if (mainImage != null && !mainImage.getOriginalFilename().isEmpty() && galleryImages != null && galleryImages.length != 0) {

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();

            String resultFilename = saveImage(mainImage);
            filmDomain.setMainImage(resultFilename);

            Set<String> galleryImageSet = new HashSet<>();
            for (MultipartFile galleryImage : galleryImages) {
                resultFilename = saveImage(galleryImage);
                galleryImageSet.add(resultFilename);
            }

            filmDomain.setGalleryImages(galleryImageSet);
        }
    }

    public void changeMainImage(FilmDomain filmDomain, MultipartFile newImage) {
        File oldImage = new File(uploadPath + "/" + filmDomain.getMainImage());
        if (oldImage.delete())
            filmDomain.setMainImage(saveImage(newImage));
    }

    public void changeGalleryImages(FilmDomain filmDomain, MultipartFile[] newGalleryImages) {
        Set<String> galleryImages = filmDomain.getGalleryImages();
        for (String imageName : galleryImages)
            deleteImages(imageName);
        galleryImages.clear();
        for (MultipartFile galleryImage : newGalleryImages)
            galleryImages.add(saveImage(galleryImage));
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

    private void deleteImages(String imageName) {
        File imageFile = new File(uploadPath + "/" + imageName);
        imageFile.delete();
    }
}
