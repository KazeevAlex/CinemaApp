package com.app.controller.admin.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/hall")
public class HallController {
    @GetMapping("/add")
    public String addHall(){
        return "admin/cinema/hall/hall_add";
    }
}
