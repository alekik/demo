package com.example.demo;

import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.HeroRepository;
import com.example.demo.persist.repos.ItemRepository;
import com.example.demo.persist.repos.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EnemyServices {
    private final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    private EnemyRepository enemyRepository;

    private UserRepository userRepository;

    private ItemRepository itemRepository;

    private HeroRepository heroRepository;

    public void addEnemy(){
        Enemy enemy = new Enemy();
        enemy.setId(1L);
        enemy.setHp(10);
        enemy.setIsBoss(0);
        enemy.setName("123");
        enemy.setPathToImage("123");
        logger.info("Saving new enemy...");
        enemyRepository.save(enemy);
    }
}
