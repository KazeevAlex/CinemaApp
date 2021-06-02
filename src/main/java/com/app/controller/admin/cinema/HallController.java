package com.app.controller.admin.cinema;

import com.app.domain.Domain;
import com.app.domain.SeoBlock;
import com.app.domain.cinema.CinemaDomain;
import com.app.domain.cinema.hall.HallDomain;
import com.app.domain.cinema.hall.HallType;
import com.app.service.cinema.CinemaService;
import com.app.service.cinema.HallService;
import com.app.service.cinema.SeatService;
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
@RequestMapping("/admin/hall")
public class HallController {

    HallService hallService;
    CinemaService cinemaService;
    SeatService seatService;

    @Autowired
    public HallController(HallService hallService, CinemaService cinemaService, SeatService seatService) {
        this.hallService = hallService;
        this.cinemaService = cinemaService;
        this.seatService = seatService;
    }

    @GetMapping("/add")
    public String addHall(
            Model model,
            @RequestParam String cinemaId
    ){
        model.addAttribute("cinemaId", Long.valueOf(cinemaId.replaceAll(",", "")));
        model.addAttribute("hallType", HallType.values());
        return "admin/cinema/hall/hall_add";
    }

    @PostMapping("/add")
    public String saveHall(
        @RequestParam String cinemaId,
        @RequestParam String name,
        @RequestParam String hallType,
        @RequestParam MultipartFile schemaImage,
        @RequestParam MultipartFile mainImage,
        @RequestParam MultipartFile[] galleryImages,
        @RequestParam String description,

        @RequestParam String seoUrl,
        @RequestParam String seoTitle,
        @RequestParam String seoKeywords,
        @RequestParam String seoDescription
    ) {
        String schema = hallService.saveImage(schemaImage);
        String mainImageString = hallService.saveImage(mainImage);
        Set<String> gallery = hallService.saveImageArray(galleryImages);

        SeoBlock seoBlock = new SeoBlock(seoUrl, seoTitle, seoKeywords, seoDescription);
        CinemaDomain cinemaDomain = (CinemaDomain) cinemaService.getById(Long.valueOf(cinemaId.replaceAll(",", "")));

        HallDomain hallDomain = new HallDomain(name, description, mainImageString, gallery, seoBlock, schema, cinemaDomain);

        hallService.save(hallDomain);

        seatService.createAndSaveHallSeats(hallDomain, hallType);

        hallService.save(hallDomain);

        return "redirect:/admin/cinema/list?size=8";
    }

    @GetMapping("/list")
    public String getHallList(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Domain> page = hallService.getAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/admin/cinema/list");
        return "admin/cinema/cinema_list";
    }

    @GetMapping("/edit/{hallId}")
    public String getHallEditPage(
            Model model,
            @PathVariable String hallId
    ) {
        model.addAttribute("hall", hallService.getById(Long.valueOf(hallId.replaceAll(",", ""))));
        return "admin/cinema/hall/hall_edit";
    }

    @PostMapping("/edit/{hallId}")
    public String saveHallEditPage(
            @PathVariable String hallId,
            @RequestParam String name,
            @RequestParam MultipartFile schemaImage,
            @RequestParam MultipartFile mainImage,
            @RequestParam MultipartFile[] galleryImages,
            @RequestParam String description,

            @RequestParam String seoUrl,
            @RequestParam String seoTitle,
            @RequestParam String seoKeywords,
            @RequestParam String seoDescription
    ) {
        SeoBlock seoBlock = new SeoBlock(seoUrl, seoTitle, seoKeywords, seoDescription);

        hallService.editAndSave(hallId, name, description, mainImage, schemaImage, galleryImages, seoBlock);


        return "redirect:/admin/cinema/list?size=8";
    }

    @GetMapping("/delete/{hallId}")
    public String delete(@PathVariable String hallId) {
        hallService.deleteById(hallId);
        return "redirect:/admin/cinema/list?size=8";
    }
}
