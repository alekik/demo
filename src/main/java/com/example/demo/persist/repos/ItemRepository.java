package com.example.demo.persist.repos;

import com.example.demo.persist.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
