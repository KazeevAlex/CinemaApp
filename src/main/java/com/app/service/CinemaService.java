package com.app.service;

import com.app.domain.SeoBlock;
import com.app.domain.cinema.Address;
import com.app.domain.cinema.CinemaDomain;
import com.app.domain.film.FilmDomain;
import com.app.repos.CinemaRepo;
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
public class CinemaService {
    @Value("${upload.path}")
    private String uploadPath;

    private CinemaRepo cinemaRepo;

    @Autowired
    public CinemaService(CinemaRepo cinemaRepo) {
        this.cinemaRepo = cinemaRepo;
    }

    public void saveCinema(CinemaDomain cinemaDomain) {
        cinemaRepo.save(cinemaDomain);
    }

    public CinemaDomain getCinemaById(Long cinemaId) {
        return cinemaRepo.findById(cinemaId).get();
    }

    public Page<CinemaDomain> findAll(Pageable pageable) {
        return cinemaRepo.findAll(pageable);
    }

    public void editAndSaveCinema( Long cinemaId,
            String name, String description, String conditions,
            MultipartFile logo, MultipartFile topBanner, MultipartFile[] gallery,
            String city, String street, String build, String email, String mainPhone, //address
            String additionalPhone, String mapCoordinate,                             //address
            String seoUrl, String seoTitle, String seoKeywords, String seoDescription //seo block 
    ) {
        CinemaDomain cinema = getCinemaById(cinemaId);
        Address address = cinema.getAddress();
        SeoBlock seoBlock = cinema.getSeoBlock();

        cinema.setName(name);
        cinema.setDescription(description);
        cinema.setConditions(conditions);

        if (!(logo == null) && !logo.isEmpty()) {
            deleteImage(cinema.getLogo());
            cinema.setLogo(saveImage(logo));
        }

        if (!(topBanner == null) && !topBanner.isEmpty()) {
            deleteImage(cinema.getTopBanner());
            cinema.setTopBanner(saveImage(topBanner));
        }

        if (!(gallery == null) && !(gallery.length == 0)) {
            if (!gallery[0].isEmpty() && !(gallery[0] == null)) {
                deleteImageSet(cinema.getGalleryImages());
                cinema.setGalleryImages(saveImageArray(gallery));
            }
        }

        address.setCity(city);
        address.setStreet(street);
        address.setBuild(build);
        address.setMainPhone(mainPhone);
        address.setAdditionalPhone(additionalPhone);
        address.setMapCoordinate(mapCoordinate);

        seoBlock.setUrl(seoUrl);
        seoBlock.setTitle(seoTitle);
        seoBlock.setKeywords(seoKeywords);
        seoBlock.setDescription(seoDescription);

        saveCinema(cinema);
    }

    public void deleteCinemaById(Long cinemaId) {
        CinemaDomain cinemaDomain = cinemaRepo.findById(cinemaId).get();

        deleteImage(cinemaDomain.getLogo());
        deleteImage(cinemaDomain.getTopBanner());
        deleteImageSet(cinemaDomain.getGalleryImages());

        cinemaRepo.deleteById(cinemaId);
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

    private void deleteImageSet(Set<String> gallery){
        for(String image : gallery)
            deleteImage(image);
    }


}
