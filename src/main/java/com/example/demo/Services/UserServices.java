package com.example.demo.Services;

import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.Item;
import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.ItemRepository;
import com.example.demo.persist.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServices {
    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public long adduser(String login, String password){
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setId(userRepository.count()+1L);
        user.setUserLevel(1);
        Optional<Enemy> enemylist = enemyRepository.findById(1L);
        ArrayList<Enemy> res = new ArrayList<>();
        enemylist.ifPresent(res::add);
        user.setCurrentEnemy(res.get(0));
        user.setCurrentEnemyHp(res.get(0).getHp());
        user.setGametime(0);
        user.setRating(0);
        user.setRole("admin");
        user.setScore(120);
        user.setDamage(1);
        userRepository.save(user);
        return user.getId();
    }

    public long findbyemail(String email){
        long res=1L;
        boolean t = false;
        List<User> lst = userRepository.findAll();
        for (User user:lst){
            if (user.getUsername().equals(email)) {
                t = true;
                break;
            }
            res+=1L;
        }
        if (t) return res;
        return 0L;
    }

    public int checkuser(long id, String password){
        int res=0;
        if (id==0L) return 0;
        Optional<User> userlist = userRepository.findById(id);
        ArrayList<User> reslst = new ArrayList<>();
        userlist.ifPresent(reslst::add);
        if (reslst.get(0).getPassword().equals(password)){
            if (reslst.get(0).getRole().equals("admin"))res=2;
            else res=1;
        }
        return res;
    }

    public User getuserbyid(Long id) {
        Optional<User> userlist = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        userlist.ifPresent(res::add);
        return res.get(0);
    }

    public void attack(long id) {
        Optional<User> userlist = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        userlist.ifPresent(res::add);
        res.get(0).setCurrentEnemyHp(res.get(0).getCurrentEnemyHp()-res.get(0).getDamage());
        if (res.get(0).getCurrentEnemyHp()<=0){
            if (res.get(0).getCurrentEnemy().getId()>=enemyRepository.count()){
                Optional<Enemy> enemylist = enemyRepository.findById(1L);
                ArrayList<Enemy> resenemy = new ArrayList<>();
                enemylist.ifPresent(resenemy::add);
                res.get(0).setCurrentEnemy(resenemy.get(0));
                res.get(0).setCurrentEnemyHp(resenemy.get(0).getHp()*(res.get(0).getUserLevel()/10+1));
            }
            else{
                Optional<Enemy> enemylist = enemyRepository.findById(res.get(0).getCurrentEnemy().getId()+1L);
                ArrayList<Enemy> resenemy = new ArrayList<>();
                enemylist.ifPresent(resenemy::add);
                res.get(0).setCurrentEnemy(resenemy.get(0));
                res.get(0).setCurrentEnemyHp(resenemy.get(0).getHp()*(res.get(0).getUserLevel()/(int)enemyRepository.count()+1));
            }
            res.get(0).setUserLevel(res.get(0).getUserLevel()+1);
            res.get(0).setScore(res.get(0).getScore()+10*(res.get(0).getUserLevel()/(int)enemyRepository.count()));
        }
        userRepository.saveAndFlush(res.get(0));
    }

    public void additem(long itemid,long id){
        Optional<User> userlist = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        userlist.ifPresent(res::add);
        Optional<Item> itemlist = itemRepository.findById(itemid);
        ArrayList<Item> itemres = new ArrayList<>();
        itemlist.ifPresent(itemres::add);
        if (res.get(0).getScore()>=itemres.get(0).getCost()){
            res.get(0).setScore(res.get(0).getScore()-itemres.get(0).getCost());
            res.get(0).getItem().add(itemres.get(0));
            res.get(0).setDamage(res.get(0).getDamage()+itemres.get(0).getDamage());
            userRepository.saveAndFlush(res.get(0));
        }
    }

    public void updatecurrenthp() {
        List<User> lst = userRepository.findAll();
        for (User user:lst){
            user.setCurrentEnemyHp(user.getCurrentEnemy().getHp());
            userRepository.saveAndFlush(user);
        }
    }

    public void updateItem() {
        List<User> lst = userRepository.findAll();
        for (User user:lst){
            user.setDamage(1);
            Set<Item> lstitem = user.getItem();
            for (Item item:lstitem){
                user.setDamage(user.getDamage()+item.getDamage());
            }
            userRepository.saveAndFlush(user);
        }
    }

    public void nextEnemy(long enemyid) {
        List<User> lst = userRepository.findAll();
        for (User user:lst){
            if (user.getCurrentEnemy().getId()==enemyid){
                if (user.getCurrentEnemy().getId()>=enemyRepository.count()){
                    Optional<Enemy> enemylist = enemyRepository.findById(1L);
                    ArrayList<Enemy> resenemy = new ArrayList<>();
                    enemylist.ifPresent(resenemy::add);
                    user.setCurrentEnemy(resenemy.get(0));
                    user.setCurrentEnemyHp(resenemy.get(0).getHp());
                }
                else{
                    Optional<Enemy> enemylist = enemyRepository.findById(user.getCurrentEnemy().getId()+1L);
                    ArrayList<Enemy> resenemy = new ArrayList<>();
                    enemylist.ifPresent(resenemy::add);
                    user.setCurrentEnemy(resenemy.get(0));
                    user.setCurrentEnemyHp(resenemy.get(0).getHp());
                }
                userRepository.saveAndFlush(user);
            }
        }
    }

    public void delteitem(long itemid) {
        List<User> lst = userRepository.findAll();
        for (User user:lst){
            Optional<Item> itemlist = itemRepository.findById(itemid);
            ArrayList<Item> resitem = new ArrayList<>();
            itemlist.ifPresent(resitem::add);
            user.getItem().remove(resitem.get(0));
            userRepository.saveAndFlush(user);
            updateItem();
        }

    }

    public List<User> getrating() {
        List<User> lst = userRepository.findAll(Sort.by("userLevel"));
        return lst;
    }

    public String getcount() {
        return String.valueOf(userRepository.count()+1);
    }
}
