package com.example.demo.persist.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Arkadiy Sotnikov (04.04.2022 3:06)
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Item")
public class Item {

    @Id
    @Getter
    @Setter
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "damage", nullable = false)
    private Integer damage;

    @Getter
    @Setter
    @Column(name = "rarity_level", nullable = false)
    private Integer rarityLevel;

    @Getter
    @Setter
    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Getter
    @Setter
    @Column(name = "path_to_image", nullable = false)
    private String path_to_image;

    @Getter
    @Setter
    @Column(name = "is_bought_by_donat", nullable = false)
    private Integer isBoughtByDonat;


}
