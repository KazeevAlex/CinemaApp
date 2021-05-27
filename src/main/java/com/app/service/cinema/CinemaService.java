package com.app.service.cinema;

import com.app.domain.SeoBlock;
import com.app.domain.cinema.Address;
import com.app.domain.cinema.CinemaDomain;
import com.app.repos.CinemaRepo;
import com.app.repos.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CinemaService extends com.app.service.Service {
    @Autowired
    public CinemaService(CinemaRepo cinemaRepo) {
        super((Repo) cinemaRepo);
    }

    public void editAndSaveCinema(Long cinemaId,
                                  String name, String description, String conditions,
                                  MultipartFile logo, MultipartFile topBanner, MultipartFile[] gallery,
                                  Address address, SeoBlock seoBlock
    ) {
        CinemaDomain cinema = (CinemaDomain) getById(cinemaId);

        cinema.setName(name);
        cinema.setDescription(description);
        cinema.setConditions(conditions);
        cinema.setAddress(address);
        cinema.setSeoBlock(seoBlock);

        if (!(logo == null) && !logo.isEmpty()) {
            deleteImage(cinema.getLogo());
            cinema.setLogo(saveImage(logo));
        }

        if (!(topBanner == null) && !topBanner.isEmpty()) {
            deleteImage(cinema.getMainImage());
            cinema.setMainImage(saveImage(topBanner));
        }

        if (!(gallery == null) && !(gallery.length == 0)) {
            if (!gallery[0].isEmpty() && !(gallery[0] == null)) {
                deleteImageSet(cinema.getGalleryImages());
                cinema.setGalleryImages(saveImageArray(gallery));
            }
        }

        save(cinema);
    }

    @Override
    public void deleteById(Long cinemaId) {
        CinemaDomain cinemaDomain = (CinemaDomain) repo.findById(cinemaId).get();

        deleteImage(cinemaDomain.getLogo());
        deleteImage(cinemaDomain.getMainImage());
        deleteImageSet(cinemaDomain.getGalleryImages());

        repo.deleteById(cinemaId);
    }
}