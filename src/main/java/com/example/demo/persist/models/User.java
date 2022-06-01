package com.example.demo.persist.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Arkadiy Sotnikov (03.04.2022 22:55)
 */

@Entity
@Table(name = "usr")
public class User {

    @Getter
    @Setter
    @Id
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
    private Set<Hero> hero;

    @Getter
    @Setter
    @ManyToMany
    private Set<Item> item;

//    @Getter
//    @Setter
//    @ManyToMany
//    private Set<Enemy> enemy;

}