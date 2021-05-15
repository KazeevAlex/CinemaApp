package com.app.controller.admin.film;

import com.app.domain.SeoBlock;
import com.app.domain.film.FilmDomain;
import com.app.domain.film.Type;
import com.app.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Controller
@RequestMapping("/admin/film")
public class FilmController {
    @Value("${upload.path}")
    private String uploadPath;

    FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/add")
    public String filmPage(Model model) {
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

        filmService.savingImages(filmDomain, mainImage, galleryImages);
        filmService.saveFilm(filmDomain);

        return "admin/dashboard";
    }
}
