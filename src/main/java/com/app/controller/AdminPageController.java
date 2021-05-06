package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @RequestMapping({"/dashboard", "/"})
    public String dashboardPage() {
        return "admin/dashboard";
    }

    @RequestMapping("/user/add")
    public String userAdd(){
        return "admin/user_add";
    }

    @RequestMapping({"/user/list", "/user"})
    public String userList(){
        return "admin/user_list";
    }
}
