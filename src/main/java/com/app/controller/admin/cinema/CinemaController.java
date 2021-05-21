package com.app.controller.admin.cinema;

import com.app.domain.SeoBlock;
import com.app.domain.cinema.Address;
import com.app.domain.cinema.CinemaDomain;
import com.app.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Controller
@RequestMapping("/admin/cinema")
public class CinemaController {

    CinemaService cinemaService;
    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/add")
    public String addCinema() {
        return "admin/cinema/cinema_add";
    }

    @PostMapping("/add")
    public String addCinema(
            @RequestParam String name,
            @RequestParam MultipartFile logoImage,
            @RequestParam MultipartFile topBannerImage,
            @RequestParam MultipartFile[] galleryImages,
            @RequestParam String description,
            @RequestParam String conditions,

            @RequestParam String city,
            @RequestParam String street,
            @RequestParam String build,
            @RequestParam String email,
            @RequestParam String mainPhone,
            @RequestParam String additionalPhone,
            @RequestParam String mapCoordinate,

            @RequestParam String seoUrl,
            @RequestParam String seoTitle,
            @RequestParam String seoKeywords,
            @RequestParam String seoDescription

    ) {
        String logo = cinemaService.saveImage(logoImage);
        String topBanner = cinemaService.saveImage(topBannerImage);
        Set<String> gallery = cinemaService.saveImageArray(galleryImages);

        Address address = new Address(city, street, build, email, mainPhone, additionalPhone, mapCoordinate);
        SeoBlock seoBlock = new SeoBlock(seoUrl, seoTitle, seoKeywords, seoDescription);

        CinemaDomain cinemaDomain = new CinemaDomain(name, description, conditions, logo, topBanner, gallery, address, seoBlock);

        cinemaService.saveCinema(cinemaDomain);

        return "redirect:/admin/film/list?size=8";
    }
}
