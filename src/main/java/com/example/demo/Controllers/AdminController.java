package com.example.demo.Controllers;

import com.example.demo.Services.EnemyServices;
import com.example.demo.Services.ItemServices;
import com.example.demo.Services.UserServices;
import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserServices userService;
    @Autowired
    private EnemyServices enemyService;
    @Autowired
    private ItemServices itemService;

    @GetMapping(value="/admin/{imgenemy}/{imgitem}/{id}/{pass}")
    public String AdminController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, @PathVariable(value="imgenemy") String imgenemy, @PathVariable(value="imgitem") String imgitem,Model model){
        model.addAttribute("id",id);
        model.addAttribute("pass",pass);
        model.addAttribute("imgenemy",imgenemy);
        model.addAttribute("imgitem",imgitem);
        return "admin";
    }

    @PostMapping(value="/admin/{imgenemy}/{imgitem}/{id}/{pass}")
    public String PostAdminController(@PathVariable(value="id") long id, @PathVariable(value="pass") String pass, @PathVariable(value="imgenemy") String imgenemy, @PathVariable(value="imgitem") String imgitem, Model model){
        model.addAttribute("id",id);
        model.addAttribute("pass",pass);
        model.addAttribute("imgenemy",imgenemy);
        model.addAttribute("imgitem",imgitem);
        return "admin";
    }

    @GetMapping(value="/admin/enemy/{imgenemy}/{imgitem}/{id}/{pass}")
    public String AdminEnemyController(@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, @PathVariable(value="imgenemy") String imgenemy, @PathVariable(value="imgitem") String imgitem,Model model){
        if (imgenemy.equals("1.jpg") )imgenemy="2.jpg";
        else if (imgenemy.equals("2.jpg"))imgenemy="3.jpg";
        else if (imgenemy.equals("3.jpg"))imgenemy="4.jpg";
        else if (imgenemy.equals("4.jpg"))imgenemy="5.jpg";
        else if (imgenemy.equals("5.jpg"))imgenemy="1.jpg";
        return "redirect:/admin/" + imgenemy + "/" + imgitem + "/" + id + "/" + pass;
    }

    @GetMapping(value="/admin/item/{imgenemy}/{imgitem}/{id}/{pass}")
    public String AdminItemController(@PathVariable(value="id") long id, @PathVariable(value="pass") String pass, @PathVariable(value="imgenemy") String imgenemy, @PathVariable(value="imgitem") String imgitem, Model model){
        if (imgitem.equals("1.jpg") )imgitem="2.jpg";
        else if (imgitem.equals("2.jpg"))imgitem="3.jpg";
        else if (imgitem.equals("3.jpg"))imgitem="4.jpg";
        else if (imgitem.equals("4.jpg"))imgitem="5.jpg";
        else if (imgitem.equals("5.jpg"))imgitem="1.jpg";
        return "redirect:/admin/" + imgenemy + "/" + imgitem + "/" + id + "/" + pass;
    }

    @PostMapping(value = "/addenemy/{imgenemy}/{id}/{pass}")
    public String addenemyController(@PathVariable(value="id") long id,@PathVariable(value="imgenemy") String imgenemy,@PathVariable(value="pass") String pass, @RequestParam String name, @RequestParam int hp,Model model){
        enemyService.addEnemy(name,imgenemy,hp);
        return "redirect:/admin/1.jpg/1.jpg/" + id + "/" + pass;
//        return "redirect:/rating";
    }

    @PostMapping(value = "/additem/{imgitem}/{id}/{pass}")
    public String additemController(@PathVariable(value="id") long id,@PathVariable(value="imgitem") String imgitem,@PathVariable(value="pass") String pass, @RequestParam String name, @RequestParam int damage,@RequestParam int cost,Model model) {
        itemService.addItem(name,imgitem, damage, cost);
        System.out.println(name);
        return "redirect:/admin/1.jpg/1.jpg/" + id + "/" + pass;
//        return "redirect:/rating";
    }
    @PostMapping(value="/admin_red/enemy/{id}/{pass}")
    public String AdminRedController(@PathVariable(value="id") long id, @PathVariable(value="pass") String pass, Model model){
        List<Enemy> enemylist = enemyService.findall();
        model.addAttribute("items",enemylist);
        model.addAttribute("id",id);
        model.addAttribute("pass",pass);

        return "admin_red";
    }
    @PostMapping(value="/updateenemy/{enemyid}/{id}/{pass}")
    public String AdminRedEnemyController(@PathVariable(value="enemyid") long enemyid, @PathVariable(value="id") long id, @PathVariable(value="pass") String pass, @RequestParam String name, @RequestParam int hp, Model model){
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
