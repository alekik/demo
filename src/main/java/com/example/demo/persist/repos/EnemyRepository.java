package com.example.demo.persist.repos;

import com.example.demo.persist.models.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Arkadiy Sotnikov (04.04.2022 2:34)
 */
public interface EnemyRepository extends JpaRepository<Enemy, Long> {

}
