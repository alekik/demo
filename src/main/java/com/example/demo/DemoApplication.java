package com.example.demo;

import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.ItemRepository;
import com.example.demo.persist.repos.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
//@Controller
public class DemoApplication {

//	private final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
//
//	private EnemyRepository enemyRepository;
//
//	private UserRepository userRepository;
//
//	private ItemRepository itemRepository;
//
//	private HeroRepository heroRepository;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
//		queryAllEnemies();
//		createEnemy();
//		queryAllEnemies();
//		deleteEnemy();
//		queryAllEnemies();
//		updateEnemy();
//		queryAllEnemies();
//
//		createUser();
	}

//	private void createEnemy() {
//		Enemy enemy = new Enemy();
//		enemy.setId(2L);
//		enemy.setHp(1);
//		enemy.setIsBoss(1);
//		enemy.setPathToImage("12");
//		enemy.setName("Hor");
//		logger.info("Saving new enemy...");
//		enemyRepository.save(enemy);
//	}
//
//	private void createUser() {
//		User user = new User();
//		user.setId(2L);
//		user.setUsername("Alex");
//		user.setRole("admin");
//		user.setPassword("JOHN");
//		user.setUserLevel(12);
//		user.setGametime(0);
//		user.setScore(0);
//		user.setCurrentEnemy(enemyRepository.getById(1L));
//		user.setItem(new HashSet<>(itemRepository.findAll()));
//		user.setHero(new HashSet<>(heroRepository.findAll()));
//		user.setRating(1);
//		userRepository.save(user);
//	}
//
//	private void deleteEnemy() {
//		Enemy enemy = enemyRepository.getById(2L);
//		enemyRepository.delete(enemy);
//	}
//
//	private void updateEnemy() {
//		Enemy enemy = new Enemy();
//		enemy.setId(1L);
//		enemy.setHp(1);
//		enemy.setIsBoss(1);
//		enemy.setPathToImage("12");
//		enemy.setName("Hor");
//		enemyRepository.saveAndFlush(enemy);
//	}
//
//	private void queryAllEnemies() {
//		List<Enemy> allEnemies = enemyRepository.findAll();
//		for (Enemy enemy: allEnemies) {
//			enemy.printEnemy();
//		}
//		logger.info("Number of enemies: " + allEnemies.size());
//	}
}
