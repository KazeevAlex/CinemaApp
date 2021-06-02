package com.app.service.cinema;

import com.app.domain.SeoBlock;
import com.app.domain.cinema.hall.HallDomain;
import com.app.domain.cinema.hall.HallType;
import com.app.domain.cinema.hall.seat.HallSeatsBuilder;
import com.app.repos.cinema.HallRepo;
import com.app.repos.Repo;
import com.app.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HallService extends AbstractService {

    @Autowired
    public HallService(HallRepo repo) {
        super((Repo) repo);
    }


    public void editAndSave(String hallId,
                            String name, String description,
                            MultipartFile mainImage, MultipartFile schemaImage, MultipartFile[] gallery,
                            SeoBlock seoBlock
    ) {
        Long id = Long.valueOf(hallId.replaceAll(",", ""));
        HallDomain hallDomain = (HallDomain) getById(id);

        hallDomain.setName(name);
        hallDomain.setDescription(description);
        hallDomain.setSeoBlock(seoBlock);

        if (!(mainImage == null) && !mainImage.isEmpty()) {
            deleteImage(hallDomain.getMainImage());
            hallDomain.setMainImage(saveImage(mainImage));
        }

        if (!(schemaImage == null) && !schemaImage.isEmpty()) {
            deleteImage(hallDomain.getSchemaImage());
            hallDomain.setSchemaImage(saveImage(schemaImage));
        }

        if (!(gallery == null) && !(gallery.length == 0)) {
            if (!gallery[0].isEmpty() && !(gallery[0] == null)) {
                deleteImageSet(hallDomain.getGalleryImages());
                hallDomain.setGalleryImages(saveImageArray(gallery));
            }
        }

        save(hallDomain);
    }

    @Override
    public void deleteById(String hallId) {
        Long id = Long.valueOf(hallId.replaceAll(",", ""));
        HallDomain hallDomain = (HallDomain) repo.findById(id).get();

        deleteImage(hallDomain.getMainImage());
        deleteImage(hallDomain.getSchemaImage());
        deleteImageSet(hallDomain.getGalleryImages());

        repo.deleteById(id);
    }
}
