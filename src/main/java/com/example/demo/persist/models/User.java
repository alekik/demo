package com.example.demo.persist.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Arkadiy Sotnikov (03.04.2022 22:55)
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usr")
public class User {

    @Getter
    @Setter
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "username", nullable = false)
    private String username;

    @Getter
    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Setter
    @OneToOne
    private Enemy currentEnemy;

    @Getter
    @Setter
    private int currentEnemyHp;

    @Getter
    @Setter
    @Column(name = "role", nullable = false)
    private String role;

    @Getter
    @Setter
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Getter
    @Setter
    @Column(name = "score", nullable = false)
    private Integer score;

    @Getter
    @Setter
    @Column(name = "gametime", nullable = false)
    private Integer gametime;

    @Getter
    @Setter
    @Column(name = "user_level")
    private Integer userLevel;

    @Getter
    @Setter
    @ManyToMany
    private Set<Item> item;

    @Getter
    @Setter
    @Column(name = "damage")
    private Integer damage;

//    @Getter
//    @Setter
//    @ManyToMany
//    private Set<Enemy> enemy;

}