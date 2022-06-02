package com.example.demo.Controllers;

import com.example.demo.DemoApplication;
import com.example.demo.Services.EnemyServices;
import com.example.demo.Services.UserServices;
import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class MainController {

    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/index")
    public String IndexController() {

        return "index";
    }
    @GetMapping(value="/login")
    public String LoginController(){
        return "login";
    }
    @PostMapping(value = "/login")
    public String PostloginController(@RequestParam String email, @RequestParam String password) {
        int check = UserServices.checkuser(email,password,userRepository);
        if (check==2)return "redirect:/rating";
        else if (check==0)return "redirect:/login";
        return "redirect:/index";
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
//        EnemyServices.addEnemy(enemyRepository);
        UserServices.adduser(email,password,userRepository);
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
