package com.example.demo.persist.repos;

import com.example.demo.persist.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {
}
