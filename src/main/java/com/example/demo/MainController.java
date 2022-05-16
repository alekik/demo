package com.example.demo;

import com.example.demo.persist.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = "/login")
    public String loginController() {
        return "login";
    }

//    @GetMapping(value="/user")
//    public String getloginvalues(Model model) {
////        model.addAttribute("user",new User());
//        return "user";
//    }
//    @PostMapping(value = "/user")
//    public String check(@ModelAttribute User user) {
//        System.out.println("${user.username}");
//
////        System.out.println(action);
//
////        if (action.equals("login")) {
////            return "/hi";
////        }
//        return "log1";
//    }
}
