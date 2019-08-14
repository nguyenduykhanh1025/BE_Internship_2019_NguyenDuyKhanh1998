package com.kunlez.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @NonNull
    private String message;

    @Column(name = "created_at")
    @JsonAlias("created_at")
    private Date createAt;

    @Column(name = "updated_at")
    @JsonAlias("updated_at")
    private Date updateAt;

    @ManyToOne
    @JoinColumn
    private BookEntity bookEntity;

    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    public CommentEntity(int id, @NonNull String message, Date createAt, Date updateAt, BookEntity bookEntity, UserEntity userEntity) {
        this.id = id;
        this.message = message;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.bookEntity = bookEntity;
        this.userEntity = userEntity;
    }

    public CommentEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
