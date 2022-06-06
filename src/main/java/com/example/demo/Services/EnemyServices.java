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

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EnemyServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private EnemyRepository enemyRepository;

    public void addEnemy(){
        Enemy enemy = new Enemy();
        enemy.setHp(10);
        enemy.setIsBoss(0);
        enemy.setName("123");
        enemy.setPathToImage("123");
        enemyRepository.save(enemy);
    }

    public int damageEnemy(){
        return 0;
    }

}
