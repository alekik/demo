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
import java.util.List;
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

    public void addEnemy(String name, int hp){
        Enemy enemy = new Enemy();
        enemy.setHp(hp);
        enemy.setIsBoss(0);
        enemy.setName(name);
        enemy.setPathToImage("123");
        enemyRepository.save(enemy);
    }

    public int damageEnemy(){
        return 0;
    }

    public List<Enemy> findall() {
        List<Enemy> lst=enemyRepository.findAll();
        return lst;
    }

    public void updateEnemy(long enemyid, String name, int hp) {
        Optional<Enemy> enemylist = enemyRepository.findById(enemyid);
        ArrayList<Enemy> res = new ArrayList<>();
        enemylist.ifPresent(res::add);
        res.get(0).setName(name);
        res.get(0).setHp(hp);
        enemyRepository.saveAndFlush(res.get(0));
    }

    public void deleteEnemy(long enemyid) {
        enemyRepository.deleteById(enemyid);
    }

    public boolean candelete(long enemyid) {
        if (enemyRepository.count()>1 && enemyid<=enemyRepository.count())return true;
        return false;
    }
}
