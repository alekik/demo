package com.example.demo.Controllers;

import com.example.demo.DemoApplication;
import com.example.demo.Services.EnemyServices;
import com.example.demo.Services.ItemServices;
import com.example.demo.Services.UserServices;
import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.Item;
import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServices userService;
    @Autowired
    private EnemyServices enemyService;
    @Autowired
    private ItemServices itemService;
    @GetMapping(value = "/")
    public String StartController() {
        long res = userService.adduser("Guest"+userService.getcount(),"123");
        return "redirect:/game/" + res + "/123";
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

    @PostMapping(value = "/index")
    public String IndexGameController(@ModelAttribute String name,Model model){
//        Object name = model.getAttribute("name");
        if (name!=null){
            model.addAttribute("name",name);
        }
        else {
            model.addAttribute("name", "Guest");
        }
        model.addAttribute("name", "Guest");
        return "index";
    }
    @GetMapping(value="/login")
    public String LoginController(){
        return "login";
    }


    @PostMapping(value = "/login")
    public String PostloginController(@RequestParam String email, @RequestParam String password, Model model) {
        long res = userService.findbyemail(email);
        int check = userService.checkuser(res,password);
        if (check==2)return "redirect:/game_admin/" + res + "/" + password;
        else if (check==0)return "redirect:/login";
        return "redirect:/game/" + res + "/" + password;
    }
    @GetMapping(value = "/register")
    public String registerController() {
        return "register";
    }
    @PostMapping(value = "/register")
    public String PostRegisterController(@RequestParam String email, @RequestParam String password, Model model) {
        long res = userService.adduser(email,password);
        return "redirect:/game/" + res + "/" + password;

    }
    @GetMapping(value = "/rating/{id}/{pass}")
    public String ratingController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass,Model model) {
        List<User> users = userService.getrating();
        model.addAttribute("users",users);
        model.addAttribute("id",id);
        model.addAttribute("pass",pass);
        return "rating";
    }



}
