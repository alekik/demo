package com.example.demo.persist.repos;

import com.example.demo.persist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Arkadiy Sotnikov (03.04.2022 23:08)
 */
public interface UserRepository extends JpaRepository<User, Long> { }
