package com.app.service.film;

import com.app.domain.SeoBlock;
import com.app.domain.film.FilmDomain;
import com.app.domain.film.Type;
import com.app.repos.FilmRepo;
import com.app.repos.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class FilmService extends com.app.service.Service {

    @Autowired
    public FilmService(FilmRepo filmRepo) {
        super((Repo) filmRepo);
    }

    public Set<Type> convertStringArrayToTypeSet(String[] typesInStrings) {
        Set<Type> typeSet = new HashSet<>();

        for (String type : typesInStrings)
            typeSet.add(Type.valueOf(type));

        return typeSet;
    }

    public void editAndSaveFilm(Long filmId,
                                String name, String description, MultipartFile mainImage,
                                String[] type, String trailerLink, MultipartFile[] gallery,
                                SeoBlock seoBlock
    ) {
        FilmDomain film = (FilmDomain) getById(filmId);

        film.setName(name);
        film.setDescription(description);
        film.setTrailerLink(trailerLink);
        film.setTypes(convertStringArrayToTypeSet(type));
        film.setSeoBlock(seoBlock);

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

        save(film);
    }

    public void deleteById(Long filmId) {
        FilmDomain filmDomain = (FilmDomain) repo.findById(filmId).get();

        deleteImage(filmDomain.getMainImage());
        deleteImageSet(filmDomain.getGalleryImages());

        repo.deleteById(filmId);
    }
}