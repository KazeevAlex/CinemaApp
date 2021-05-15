package com.app.controller;

import com.app.domain.film.FilmDomain;
import com.app.domain.user.User;
import com.app.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private FilmRepo filmRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<FilmDomain> films = filmRepo.findAll();

        if(filter != null && !filter.isEmpty())
            films = filmRepo.findByName(filter);
        else
            films = filmRepo.findAll();

        model.addAttribute("films", films);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String description, Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        FilmDomain filmDomain = new FilmDomain(name, description, user);

        if(file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            filmDomain.setMainImage(resultFilename);
        }

        filmRepo.save(filmDomain);

        Iterable<FilmDomain> films = filmRepo.findAll();
        model.put("films", films);

        return "main";
    }
}
