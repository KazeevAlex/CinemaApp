package com.app.controller.admin.cinema;

import com.app.domain.Domain;
import com.app.domain.SeoBlock;
import com.app.domain.cinema.Address;
import com.app.domain.cinema.CinemaDomain;
import com.app.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

        CinemaDomain cinemaDomain = new CinemaDomain(name, description, topBanner, gallery, seoBlock, conditions, logo,address);

        cinemaService.save(cinemaDomain);

        return "redirect:/admin/cinema/list?size=8";
    }

    @GetMapping("/list")
    public String getCinemaList(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Domain> page = cinemaService.getAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/admin/cinema/list");
        return "admin/cinema/cinema_list";
    }

    @GetMapping("/edit/{cinemaId}")
    public String getCinemaEditPage(
            Model model,
            @PathVariable Long cinemaId
    ) {
        model.addAttribute("cinema", cinemaService.getById(cinemaId));
        return "admin/cinema/cinema_edit";
    }

    @PostMapping("/edit/{cinemaId}")
    public String editCinema(
            @PathVariable Long cinemaId,
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
        Address address = new Address(city, street, build, email, mainPhone, additionalPhone, mapCoordinate);
        SeoBlock seoBlock = new SeoBlock(seoUrl, seoTitle, seoKeywords, seoDescription);

        cinemaService.editAndSaveCinema(
                cinemaId, name, description, conditions, logoImage, topBannerImage, galleryImages,
                address, seoBlock);
        return "redirect:/admin/cinema/list?size=8";
    }


    @GetMapping("/delete/{cinemaId}")
    public String deleteCinema(@PathVariable Long cinemaId) {
        cinemaService.deleteById(cinemaId);
        return "redirect:/admin/cinema/list?size=8";
    }
}
