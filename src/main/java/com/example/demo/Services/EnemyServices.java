package com.example.demo.Services;

import com.example.demo.DemoApplication;
import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.HeroRepository;
import com.example.demo.persist.repos.ItemRepository;
import com.example.demo.persist.repos.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class EnemyServices {

    private UserRepository userRepository;

    private ItemRepository itemRepository;

    private HeroRepository heroRepository;

    public static void addEnemy(EnemyRepository repos){
        Enemy enemy = new Enemy();
        enemy.setId(1L);
        enemy.setHp(10);
        enemy.setIsBoss(0);
        enemy.setName("123");
        enemy.setPathToImage("123");
        repos.save(enemy);
    }
}
