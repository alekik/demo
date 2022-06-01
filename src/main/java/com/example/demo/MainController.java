package com.example.demo;

import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.UserServices;
import com.example.demo.EnemyServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    private EnemyRepository enemyRepository;
    @GetMapping(value = "/index")
    public String IndexController() {

        return "index";
    }
    @RequestMapping(value = "/login")
    public String loginController() {
        return "login";
    }
    @GetMapping(value = "/register")
    public String registerController() {

        return "register";
    }
    @PostMapping(value = "/register")
    public String PostRegisterController(@RequestParam String email, @RequestParam String password) {
//        System.out.println(email);
//        System.out.println(password);
//        UserServices.adduser(email, password);
//        EnemyServices.addEnemy();
        Enemy enemy = new Enemy();
        enemy.setId(2L);
        enemy.setHp(1);
        enemy.setIsBoss(1);
        enemy.setPathToImage("12");
        enemy.setName("Hor");
        enemyRepository.save(enemy);
//        logger.info("Saving new enemy...");
//        enemy.printEnemy();
//        logger.info("Saving new enemy...");
        return "redirect:/index";

    }
    @RequestMapping(value = "/rating")
    public String ratingController() {
        return "rating";
    }
//    @RequestMapping(value = "/error")
//    public String errorController() {
//        return "error";
//    }

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
