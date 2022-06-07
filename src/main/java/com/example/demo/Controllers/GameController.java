package com.example.demo.Controllers;

import com.example.demo.Services.EnemyServices;
import com.example.demo.Services.ItemServices;
import com.example.demo.Services.UserServices;
import com.example.demo.persist.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {
    @Autowired
    private UserServices userService;
    @Autowired
    private EnemyServices enemyService;
    @Autowired
    private ItemServices itemService;
    @GetMapping(value = "/game/{id}/{pass}")
    public String IndexUserController(@PathVariable(value="id") long id, @PathVariable(value="pass") String pass, Model model) {
//        Object name = model.getAttribute("name");
//        if (name!=null){
//            model.addAttribute("name",name);
//        }
//        else {
//            model.addAttribute("name", "Guest");
//        }
        int check = userService.checkuser(id,pass);
        if (check==2)return "redirect:/game_admin/" + id + "/" + pass;
        else if (check==0)return "redirect:/login";
        User user = userService.getuserbyid(id);
        model.addAttribute("score", user.getScore());
        model.addAttribute("level", user.getUserLevel());
        model.addAttribute("enemyname", user.getCurrentEnemy().getName());
        model.addAttribute("enemyhp", user.getCurrentEnemyHp());
        model.addAttribute("name", user.getUsername());
        model.addAttribute("pathToImage", user.getCurrentEnemy().getPathToImage());
        return "game";
    }
    @PostMapping(value = "/game/{id}/{pass}")
    public String PostIndexUserController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model) {
//        Object name = model.getAttribute("name");
//        if (name!=null){
//            model.addAttribute("name",name);
//        }
//        else {
//            model.addAttribute("name", "Guest");
//        }

        int check = userService.checkuser(id,pass);
        if (check==2)return "redirect:/game_admin/" + id + "/" + pass;
        else if (check==0)return "redirect:/login";
        userService.attack(id);
        User user = userService.getuserbyid(id);
        model.addAttribute("score", user.getScore());
        model.addAttribute("level", user.getUserLevel());
        model.addAttribute("enemyname", user.getCurrentEnemy().getName());
        model.addAttribute("enemyhp", user.getCurrentEnemyHp());
        model.addAttribute("name", user.getUsername());
        model.addAttribute("id", id);
        model.addAttribute("pathToImage", user.getCurrentEnemy().getPathToImage());
        return "game";
    }
    @GetMapping(value = "/game_admin/{id}/{pass}")
    public String IndexAdminController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model) {
//        Object name = model.getAttribute("name");
//        if (name!=null){
//            model.addAttribute("name",name);
//        }
//        else {
//            model.addAttribute("name", "Guest");
//        }
        int check = userService.checkuser(id,pass);
        if (check==1)return "redirect:/game/" + id + "/" + pass;
        else if (check==0)return "redirect:/login";
        User user = userService.getuserbyid(id);
        model.addAttribute("score", user.getScore());
        model.addAttribute("level", user.getUserLevel());
        model.addAttribute("enemyname", user.getCurrentEnemy().getName());
        model.addAttribute("enemyhp", user.getCurrentEnemyHp());
        model.addAttribute("name", user.getUsername());
        model.addAttribute("pathToImage", user.getCurrentEnemy().getPathToImage());
        return "game_admin";
    }
    @PostMapping(value = "/game_admin/{id}/{pass}")
    public String PostIndexAdminController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model) {
//        Object name = model.getAttribute("name");
//        if (name!=null){
//            model.addAttribute("name",name);
//        }
//        else {
//            model.addAttribute("name", "Guest");
//        }
        int check = userService.checkuser(id, pass);
        if (check == 1) return "redirect:/game/" + id + "/" + pass;
        else if (check == 0) return "redirect:/login";
        userService.attack(id);
        User user = userService.getuserbyid(id);
        model.addAttribute("score", user.getScore());
        model.addAttribute("level", user.getUserLevel());
        model.addAttribute("enemyname", user.getCurrentEnemy().getName());
        model.addAttribute("enemyhp", user.getCurrentEnemyHp());
        model.addAttribute("name", user.getUsername());
        model.addAttribute("id", id);
        model.addAttribute("pathToImage", user.getCurrentEnemy().getPathToImage());
        return "game_admin";
    }
}
