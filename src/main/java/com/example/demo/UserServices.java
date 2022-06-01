package com.example.demo;

import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.HeroRepository;
import com.example.demo.persist.repos.ItemRepository;
import com.example.demo.persist.repos.UserRepository;

public class UserServices {
    private EnemyRepository enemyRepository;

    private static UserRepository userRepository;

    private ItemRepository itemRepository;

    private HeroRepository heroRepository;

    public static void adduser(String login, String password){
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setUserLevel(1);
//        user.setCurrentEnemy(enemyRepository.getOne(1L));
        user.setGametime(0);
        user.setRating(0);
        user.setRole("user");
        user.setScore(0);
        userRepository.save(user);
    }
}
