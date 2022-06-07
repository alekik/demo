package com.example.demo.Services;

import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.Item;
import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.ItemRepository;
import com.example.demo.persist.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserServices userServices;
    public List<Item> finditemsbyid(long id){
        List<Item> res = new ArrayList<>();
        List<Item> allitems = itemRepository.findAll();
        User user = userServices.getuserbyid(id);
        boolean t=true;
        for (Item itemall:allitems){
            for (Item item:user.getItem()){
                if (item.getId()==itemall.getId()){
                    t=false;
                    break;
                }
            }
            if (t){
                res.add(itemall);
            }
            else{t=true;}
        }
        return res;
    }

    public void addItem(String name,int damage,int cost){
        Item item = new Item();
        item.setName(name);
        item.setCost(cost);
        item.setDamage(damage);
        item.setRarityLevel(1);
        item.setPath_to_image("---");
        item.setIsBoughtByDonat(0);
        itemRepository.save(item);
    }
    public void updateItem(long itemid, String name, int damage, int cost) {
        Optional<Item> itemlist = itemRepository.findById(itemid);
        ArrayList<Item> res = new ArrayList<>();
        itemlist.ifPresent(res::add);
        res.get(0).setName(name);
        res.get(0).setDamage(damage);
        res.get(0).setCost(cost);
        itemRepository.saveAndFlush(res.get(0));
    }

    public List<Item> findall() {
        return itemRepository.findAll();
    }

    public void deleteItem(long itemid) {
        itemRepository.deleteById(itemid);
    }
}
