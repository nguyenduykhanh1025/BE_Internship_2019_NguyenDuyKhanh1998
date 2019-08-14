package com.kunlez.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name="book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "create_at")
    @JsonAlias("create_at")
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonAlias("updated_at")
    private Date updatedAt;

    @Column(name = "link_image", columnDefinition = "TEXT")
    @JsonAlias("link_image")
    private String linkImage;

    @Column(columnDefinition = "boolean default false")
    private boolean enable;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorEntity> authorEntities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    private Set<CategoriesEntity> categoriesEntities;

    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    @OneToMany(mappedBy = "bookEntity", cascade = CascadeType.ALL)
    private Set<CommentEntity> commentEntities;

    public BookEntity() {
    }


    public Set<CategoriesEntity> getCategoriesEntities() {
        return categoriesEntities;
    }

    public void setCategoriesEntities(Set<CategoriesEntity> categoriesEntities) {
        this.categoriesEntities = categoriesEntities;
    }

    public Set<AuthorEntity> getAuthorEntities() {
        return authorEntities;
    }

    public void setAuthorEntities(Set<AuthorEntity> authorEntities) {
        this.authorEntities = authorEntities;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Set<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(Set<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
