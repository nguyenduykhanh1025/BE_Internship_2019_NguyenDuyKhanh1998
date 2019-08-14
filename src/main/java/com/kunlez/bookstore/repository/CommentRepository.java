package com.kunlez.bookstore.repository;

import com.kunlez.bookstore.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.OrderBy;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByOrderByCreateAtDesc();
}
