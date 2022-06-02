package com.example.demo.Services;

import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.HeroRepository;
import com.example.demo.persist.repos.ItemRepository;
import com.example.demo.persist.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServices {
    private EnemyRepository enemyRepository;

    private ItemRepository itemRepository;

    private HeroRepository heroRepository;

    public static void adduser(String login, String password,UserRepository repos){
        User user = new User();
        List<User> lst = repos.findAll();
        user.setId((long) lst.size());
        user.setUsername(login);
        user.setPassword(password);
        user.setUserLevel(1);
//        user.setCurrentEnemy(enemyRepository.getOne(1L));
        user.setGametime(0);
        user.setRating(0);
        user.setRole("user");
        user.setScore(0);
        repos.save(user);
    }

    public static int checkuser(String email, String password, UserRepository repos){
        List<User> lst = repos.findAll();
        int res=0;
        for (User user:lst){
            if (user.getUsername().equals(email) && user.getPassword().equals(password)) {
                if (user.getRole().equals("admin"))res=2;
                else res=1;
                break;
            }
        }
        return res;
    }
}
