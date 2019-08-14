package com.kunlez.bookstore.repository;

import com.kunlez.bookstore.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository  extends JpaRepository<AuthorEntity, Integer> {
    AuthorEntity findByName(String name);
}
