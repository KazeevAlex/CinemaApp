package com.app.controller.admin.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/hall")
public class HallController {
    @GetMapping("/add")
    public String addHall(
            Model model,
            @RequestParam String cinemaId
    ){
        model.addAttribute("cinemaId", cinemaId);
        return "admin/cinema/hall/hall_add";
    }

    @PostMapping("/add")
    public String saveHall(
        @RequestParam String cinemaId,
        @RequestParam String name,
        @RequestParam String schemaImage,
        @RequestParam String mainImage,
        @RequestParam String galleryImages,
        @RequestParam String description,

        @RequestParam String seoUrl,
        @RequestParam String seoTitle,
        @RequestParam String seoKeywords,
        @RequestParam String seoDescription
    ) {

        return null;
    }
}
