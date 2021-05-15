package com.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @RequestMapping({"/"})
    public String dashboardPage() {
        return "admin/dashboard";
    }

    @RequestMapping("/user/add")
    public String userAdd(){
        return "admin/user/add";
    }

    @RequestMapping({"/user/list", "/user"})
    public String userList(){
        return "admin/user/list";
    }
}
