package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    @RequestMapping(value = "/hi")
    public String helloWorldController() {
        return "hi";
    }

    @RequestMapping(value = "/login")
    public String loginController() {
        return "login";
    }
    @RequestMapping(value = "/log1")
    public String logController() {
        return "log1";
    }

//    @PostMapping("/login")
//    public String check(@RequestParam String action) {
//
//        System.out.println(action);
//
//        if (action.equals("login")) {
//            return "hi";
//        }
//        return "login";
//    }
}
