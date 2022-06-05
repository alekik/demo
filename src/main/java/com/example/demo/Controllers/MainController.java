package com.example.demo.Controllers;

import com.example.demo.DemoApplication;
import com.example.demo.Services.EnemyServices;
import com.example.demo.Services.UserServices;
import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServices userService;
    @GetMapping(value = "/")
    public String StartController() {
        return "index";
    }
    @GetMapping(value = "/game")
    public String GameController(Model model) {
        return "game";
    }
    @GetMapping(value = "/index")
    public String IndexController(@ModelAttribute String name,Model model) {
//        Object name = model.getAttribute("name");
        if (name!=null){
            model.addAttribute("name",name);
        }
        else {
            model.addAttribute("name", "Guest");
        }
//        model.addAttribute("name", name);
        return "index";
    }
    @GetMapping(value = "/game/{id}")
    public String IndexUserController(@PathVariable(value="name") Long id, Model model) {
//        Object name = model.getAttribute("name");
//        if (name!=null){
//            model.addAttribute("name",name);
//        }
//        else {
//            model.addAttribute("name", "Guest");
//        }
        User user = userService.getuserbyid(id);
        model.addAttribute("score", user.getScore());
        model.addAttribute("level", user.getUserLevel());
//        model.addAttribute("enemyname", name);
//        model.addAttribute("enemyhp", name);
//        model.addAttribute("name", name);
        return "game";
    }
    @PostMapping(value = "/game/{name}")
    public String PostIndexUserController(@PathVariable(value="name") String name, Model model) {
//        Object name = model.getAttribute("name");
//        if (name!=null){
//            model.addAttribute("name",name);
//        }
//        else {
//            model.addAttribute("name", "Guest");
//        }
        model.addAttribute("name", name);
        return "game";
    }
    @PostMapping(value = "/index")
    public String IndexGameController(@ModelAttribute String name,Model model){
//        Object name = model.getAttribute("name");
        if (name!=null){
            model.addAttribute("name",name);
        }
        else {
            model.addAttribute("name", "Guest");
        }
//        model.addAttribute("name", name);
        model.addAttribute("name", "Guest");
        return "index";
    }
    @GetMapping(value="/login")
    public String LoginController(){
        return "login";
    }
    @GetMapping(value="/admin")
    public String AdminController(){
        return "admin";
    }
    @PostMapping(value = "/login")
    public String PostloginController(@RequestParam String email, @RequestParam String password, Model model) {
        int check = userService.checkuser(email,password);
        if (check==2)return "rating";
        else if (check==0)return "login";
        model.addAttribute("name", email);
        return "redirect:/index";
    }
    @GetMapping(value = "/register")
    public String registerController() {

        return "register";
    }
    @PostMapping(value = "/register")
    public String PostRegisterController(@RequestParam String email, @RequestParam String password, Model model) {
//        System.out.println(email);
//        System.out.println(password);
//        UserServices.adduser(email, password);
//        EnemyServices.addEnemy(enemyRepository);
        userService.adduser(email,password);
//        logger.info("Saving new enemy...");
//        enemy.printEnemy();
//        logger.info("Saving new enemy...");
        model.addAttribute("name",email);
        return "redirect:/game/" + email;

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
