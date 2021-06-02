package com.app.service.film;

import com.app.domain.SeoBlock;
import com.app.domain.film.FilmDomain;
import com.app.domain.film.FilmType;
import com.app.repos.FilmRepo;
import com.app.repos.Repo;
import com.app.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class FilmService extends AbstractService {

    @Autowired
    public FilmService(FilmRepo filmRepo) {
        super((Repo) filmRepo);
    }

    public Set<FilmType> convertStringArrayToTypeSet(String[] typesInStrings) {
        Set<FilmType> typeSet = new HashSet<>();

        for (String type : typesInStrings)
            typeSet.add(FilmType.valueOf(type));

        return typeSet;
    }

    public void editAndSaveFilm(String filmId,
                                String name, String description, MultipartFile mainImage,
                                String[] type, String trailerLink, MultipartFile[] gallery,
                                SeoBlock seoBlock
    ) {
        Long id = Long.valueOf(filmId.replaceAll(",", ""));
        FilmDomain film = (FilmDomain) getById(id);

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

    public LocalDate[] convertStringToDate(String date) {
        String[] dateArray = date.split("-");
        LocalDate[] localDates = new LocalDate[2];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        localDates[0] = LocalDate.parse(dateArray[0].trim(), dateTimeFormatter);
        localDates[1] = LocalDate.parse(dateArray[1].trim(), dateTimeFormatter);

        return localDates;
    }


    @Override
    public void deleteById(String filmId) {
        Long id = Long.valueOf(filmId.replaceAll(",", ""));
        FilmDomain filmDomain = (FilmDomain) repo.findById(id).get();

        deleteImage(filmDomain.getMainImage());
        deleteImageSet(filmDomain.getGalleryImages());

        repo.deleteById(id);
    }
}
