package com.example.demo.Controllers;

import com.example.demo.Services.ItemServices;
import com.example.demo.Services.UserServices;
import com.example.demo.persist.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StoreController {
    @Autowired
    private UserServices userService;
    @Autowired
    private ItemServices itemServices;
    @GetMapping(value = "/store/{id}/{pass}")
    public String storeController(@PathVariable(value="id") long id, @PathVariable(value="pass") String pass, Model model){
        List<Item> items = itemServices.finditemsbyid(id);
        model.addAttribute("items",items);
        model.addAttribute("id",id);
        model.addAttribute("pass",pass);

        return "store";
    }
    @PostMapping(value="/buyitem/{itemid}/{id}/{pass}")
    public String itembuyController(@PathVariable(value="itemid") long itemid,@PathVariable(value="id") long id,@PathVariable(value="pass") String pass, Model model){
        userService.additem(itemid,id);
        return "redirect:/store/" + id + "/" + pass;
    }
}
