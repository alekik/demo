package com.example.demo.persist.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Arkadiy Sotnikov (04.04.2022 2:13)
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Enemy")
public class Enemy {
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
    @Column(name = "path_to_image", nullable = false)
    private String pathToImage;

    @Getter
    @Setter
    @Column(name = "Hp", nullable = false)
    private Integer Hp;

    @Getter
    @Setter
    @Column(name = "is_boss", nullable = false)
    private Integer isBoss;

    public void printEnemy() {
        System.out.println(id);
        System.out.println(name);
        System.out.println(pathToImage);
        System.out.println(Hp);
        System.out.println(isBoss);
    }
}
