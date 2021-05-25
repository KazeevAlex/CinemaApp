package com.app.service;

import com.app.domain.SeoBlock;
import com.app.domain.cinema.Address;
import com.app.domain.cinema.CinemaDomain;
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

        deleteImage(filmDomain.getMainImage());
        deleteImageSet(filmDomain.getGalleryImages());

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

    public void editAndSaveFilm(Long filmId,
                                String name, String description, MultipartFile mainImage, String[] type,  //main information
                                String trailerLink, MultipartFile[] gallery,                              //main information
                                String seoUrl, String seoTitle, String seoKeywords, String seoDescription //seo block
    ) {
        FilmDomain film = getFilmById(filmId);

        film.setName(name);
        film.setDescription(description);
        film.setTrailerLink(trailerLink);

        film.setTypes(convertStringArrayToTypeSet(type));

        if (!(mainImage == null) && !mainImage.isEmpty()) {
            deleteImage(film.getMainImage());
            film.setMainImage(saveImage(mainImage));
        }

        if (!(gallery == null) && !(gallery.length == 0)) {
            if (!gallery[0].isEmpty() && !(gallery[0] == null)) {
                deleteImageSet(film.getGalleryImages());
                film.setGalleryImages(saveImageArray(gallery));
            }
        }

        film.setSeoBlock(new SeoBlock(seoUrl, seoTitle, seoKeywords, seoDescription));

        saveFilm(film);
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

    public Set<String> saveImageArray(MultipartFile[] imageArray) {
        Set<String> imageSet = new HashSet<>();
        for (MultipartFile image : imageArray)
            imageSet.add(saveImage(image));
        return imageSet;
    }

    private void deleteImage(String imageName) {
        File imageFile = new File(uploadPath + "/" + imageName);
        imageFile.delete();
    }

    private void deleteImageSet(Set<String> gallery) {
        for (String image : gallery)
            deleteImage(image);
    }
}
