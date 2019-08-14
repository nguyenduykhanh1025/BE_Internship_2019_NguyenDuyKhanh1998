package com.kunlez.bookstore.repository;

import com.kunlez.bookstore.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {
    CategoriesEntity findByName(String name);
}
