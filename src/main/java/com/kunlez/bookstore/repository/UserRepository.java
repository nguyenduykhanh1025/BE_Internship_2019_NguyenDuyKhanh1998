package com.kunlez.bookstore.repository;

import com.kunlez.bookstore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

    UserEntity findByEmail(String email);
}