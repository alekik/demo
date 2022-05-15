package com.example.demo.persist.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Arkadiy Sotnikov (04.04.2022 3:04)
 */
@Entity
@Table(name = "Hero")
public class Hero {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "damage", nullable = false)
    private Integer damage;

    @Getter
    @Setter
    @Column(name = "hero_level", nullable = false)
    private Integer heroLevel;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Getter
    @Setter
    @Column(name = "hero_order", nullable = false)
    private Integer heroOrder;
}
