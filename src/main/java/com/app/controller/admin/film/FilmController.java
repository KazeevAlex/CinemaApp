package com.app.controller.admin.film;

import com.app.domain.Domain;
import com.app.domain.SeoBlock;
import com.app.domain.film.FilmDomain;
import com.app.domain.film.Type;
import com.app.service.film.FilmService;
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
@RequestMapping("/admin/film")
public class FilmController {

    FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/add")
    public String getFilmAddPage(Model model) {
        model.addAttribute("types", Type.values());
        return "admin/film/film_add";
    }

    @PostMapping("/add")
    public String addFilm(
            @RequestParam String name,
            @RequestParam MultipartFile mainImage,
            @RequestParam String[] type,
            @RequestParam String trailerLink,
            @RequestParam String description,
            @RequestParam MultipartFile[] galleryImages,
            @RequestParam String seoUrl,
            @RequestParam String seoTitle,
            @RequestParam String seoKeywords,
            @RequestParam String seoDescription

    ) {
        String mainImg = filmService.saveImage(mainImage);
        Set<String> gallery = filmService.saveImageArray(galleryImages);
        Set<Type> types = filmService.convertStringArrayToTypeSet(type);

        SeoBlock seoBlock = new SeoBlock(seoUrl, seoTitle, seoKeywords, seoDescription);

        FilmDomain filmDomain = new FilmDomain(name, description, mainImg, gallery, seoBlock, trailerLink, types);

        filmService.save(filmDomain);

        return "redirect:/admin/film/list?size=8";
    }

    @GetMapping("/list")
    public String getFilmList(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Domain> filmPage = filmService.getAll(pageable);
        model.addAttribute("page", filmPage);
        model.addAttribute("url", "/admin/film/list");
        return "admin/film/film_list";
    }

    @GetMapping("/edit/{filmId}")
    public String getFilmEditPage(
            Model model,
            @PathVariable Long filmId
    ) {
        model.addAttribute("types", Type.values());
        model.addAttribute("film", filmService.getById(filmId));
        model.addAttribute("filmTypes", ((FilmDomain) filmService.getById(filmId)).getTypes());
        return "admin/film/film_edit";
    }

    @PostMapping("/edit/{filmId}")
    public String editFilm(
            @PathVariable Long filmId,
            @RequestParam String name,
            @RequestParam(required = false) MultipartFile mainImage,
            @RequestParam String[] type,
            @RequestParam String trailerLink,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile[] galleryImages,

            @RequestParam String seoUrl,
            @RequestParam String seoTitle,
            @RequestParam String seoKeywords,
            @RequestParam String seoDescription
    ) {
        SeoBlock seoBlock = new SeoBlock(seoUrl, seoTitle, seoKeywords, seoDescription);
        filmService.editAndSaveFilm(
                filmId, name, description, mainImage, type, trailerLink, galleryImages,
                seoBlock);
        return "redirect:/admin/film/list?size=8";
    }

    @GetMapping("/delete/{filmId}")
    public String deleteFilm(@PathVariable Long filmId) {
        filmService.deleteById(filmId);
        return "redirect:/admin/film/list?size=8";
    }
}
