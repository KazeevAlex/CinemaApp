package com.app.controller.admin.film;

import com.app.domain.SeoBlock;
import com.app.domain.film.FilmDomain;
import com.app.domain.film.Type;
import com.app.service.FilmService;
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
        Set<Type> types = filmService.convertStringArrayToTypeSet(type);
        SeoBlock seoBlock = new SeoBlock(seoUrl, seoTitle, seoKeywords, seoDescription);

        FilmDomain filmDomain = new FilmDomain(name, types, trailerLink, description, seoBlock);

        filmService.saveFilmImages(filmDomain, mainImage, galleryImages);
        filmService.saveFilm(filmDomain);

        return "redirect:/admin/film/list?size=8";
    }

    @GetMapping("/list")
    public String getFilmList(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<FilmDomain> filmPage = filmService.findAll(pageable);
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
        model.addAttribute("film", filmService.getFilmById(filmId));
        model.addAttribute("filmTypes", filmService.getFilmById(filmId).getTypes());

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
        FilmDomain filmDomain = filmService.getFilmById(filmId);

        filmDomain.setName(name);

        filmService.changeValuesOfTypeSet(filmDomain, type);

        filmDomain.setTrailerLink(trailerLink);
        filmDomain.setDescription(description);
        filmDomain.getSeoBlock().setUrl(seoUrl);
        filmDomain.getSeoBlock().setTitle(seoTitle);
        filmDomain.getSeoBlock().setKeywords(seoKeywords);
        filmDomain.getSeoBlock().setDescription(seoDescription);

        if (!(mainImage == null) && !mainImage.isEmpty())
            filmService.changeMainImage(filmDomain, mainImage);

        if (!(galleryImages == null) && !(galleryImages.length == 0)
                && !galleryImages[0].isEmpty() && !(galleryImages[0] == null))
            filmService.changeGalleryImages(filmDomain, galleryImages);

        filmService.saveFilm(filmDomain);

        return "redirect:/admin/film/list";
    }

    @GetMapping("/delete/{filmId}")
    public String deleteFilm(@PathVariable Long filmId) {
        filmService.deleteFilmById(filmId);
        return "redirect:/admin/film/list?size=8";
    }
}
