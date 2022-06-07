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
        return "index";
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
    @GetMapping(value = "/game/{id}/{pass}")
    public String IndexUserController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model) {
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
        int check = userService.checkuser(id,pass);
        if (check==1)return "redirect:/game/" + id + "/" + pass;
        else if (check==0)return "redirect:/login";
        userService.attack(id);
        User user = userService.getuserbyid(id);
        model.addAttribute("score", user.getScore());
        model.addAttribute("level", user.getUserLevel());
        model.addAttribute("enemyname", user.getCurrentEnemy().getName());
        model.addAttribute("enemyhp", user.getCurrentEnemyHp());
        model.addAttribute("name", user.getUsername());
        model.addAttribute("id", id);
        return "game_admin";
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
//    @GetMapping(value="/admin")
//    public String AdminController(){
//        return "admin";
//    }

    @GetMapping(value="/admin/{id}/{pass}")
    public String AdminController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass,Model model){
        model.addAttribute("id",id);
        model.addAttribute("pass",pass);
        return "admin";
    }

    @PostMapping(value="/admin/{id}/{pass}")
    public String PostAdminController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass,Model model){
        model.addAttribute("id",id);
        model.addAttribute("pass",pass);
        return "admin";
    }
    @PostMapping(value = "/addenemy/{id}/{pass}")
    public String addenemyController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, @RequestParam String name, @RequestParam int hp,Model model){
        enemyService.addEnemy(name,hp);
        System.out.println(name);
        return "redirect:/admin/" + id + "/" + pass;
//        return "redirect:/rating";
    }

    @PostMapping(value = "/additem/{id}/{pass}")
    public String additemController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, @RequestParam String name, @RequestParam int damage,@RequestParam int cost,Model model){
        itemService.addItem(name,damage,cost);
        System.out.println(name);
        return "redirect:/admin/" + id + "/" + pass;
//        return "redirect:/rating";
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
//        System.out.println(email);
//        System.out.println(password);
//        UserServices.adduser(email, password);
//        EnemyServices.addEnemy(enemyRepository);
        long res = userService.adduser(email,password);
//        logger.info("Saving new enemy...");
//        enemy.printEnemy();
//        logger.info("Saving new enemy...");
//        model.addAttribute("name",email);
        return "redirect:/game/" + res + "/" + password;

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
    @PostMapping(value="/buyitem/{itemid}/{id}/{pass}")
    public String itembuyController(@PathVariable(value="itemid") long itemid,@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model){
        userService.additem(itemid,id);
        return "redirect:/store/" + id + "/" + pass;
    }

    @PostMapping(value="/admin_red/enemy/{id}/{pass}")
    public String AdminRedController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model){
        List<Enemy> enemylist = enemyService.findall();
        model.addAttribute("items",enemylist);
        model.addAttribute("id",id);
        model.addAttribute("pass",pass);

        return "admin_red";
    }
    @PostMapping(value="/updateenemy/{enemyid}/{id}/{pass}")
    public String AdminRedEnemyController(@PathVariable(value="enemyid") long enemyid,@PathVariable(value="id") long id,@PathVariable(value="pass") String pass,@RequestParam String name, @RequestParam int hp, Model model){
        enemyService.updateEnemy(enemyid,name,hp);
        userService.updatecurrenthp();
        return "redirect:/admin/" + id + "/" + pass;
    }
    @PostMapping(value="/deleteenemy/{enemyid}/{id}/{pass}")
    public String AdminRedDeleteEnemyController(@PathVariable(value="enemyid") long enemyid,@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model){
        if (enemyService.candelete(enemyid)) {
            userService.nextEnemy(enemyid);
            enemyService.deleteEnemy(enemyid);
        }
        return "redirect:/admin/" + id + "/" + pass;
    }

    @PostMapping(value="/deleteitem/{itemid}/{id}/{pass}")
    public String AdminRedDeleteItemController(@PathVariable(value="itemid") long itemid,@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model){
        userService.delteitem(itemid);
        itemService.deleteItem(itemid);
        return "redirect:/admin/" + id + "/" + pass;
    }
    @PostMapping(value="/admin_red/item/{id}/{pass}")
    public String AdminReditemController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model){
        List<Item> itemlist = itemService.findall();
        model.addAttribute("items",itemlist);
        model.addAttribute("id",id);
        model.addAttribute("pass",pass);

        return "admin_red_item";
    }
    @PostMapping(value="/updateitem/{itemid}/{id}/{pass}")
    public String AdminReditemupdateController(@PathVariable(value="itemid") long itemid,@PathVariable(value="id") long id,@PathVariable(value="pass") String pass,@RequestParam String name, @RequestParam int damage, @RequestParam int cost, Model model){
//        enemyService.updateEnemy(itemid,name,hp);
//        userService.updatecurrenthp();
        itemService.updateItem(itemid,name,damage,cost);
        userService.updateItem();
        return "redirect:/admin/" + id + "/" + pass;
    }
}
