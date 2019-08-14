package com.kunlez.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonAlias("first_name")
    @Column(nullable = false, name = "first_name")
    @NonNull
    private String firstName;

    @JsonAlias({"last_name"})
    @Column(nullable = false, name = "last_name")
    @NonNull
    private String lastName;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(nullable = false)
    @NonNull
    private boolean enable;

    @Column(name = "link_avatar")
    @JsonAlias("link_avatar")
    @NonNull
    private String linkAvatar;

    @Column
    @NonNull
    private String email;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<BookEntity> bookEntities;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<CommentEntity> commentEntities;

    public UserEntity(@NonNull String email, @NonNull String firstName, @NonNull String lastName, @NonNull String password, @NonNull boolean enable) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.enable = enable;
        this.email = email;
    }

    public UserEntity() {
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public void setBookEntities(Set<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }

    public Set<CommentEntity> getCommentEntities() {
        return commentEntities;
    }

    public void setCommentEntities(Set<CommentEntity> commentEntities) {
        this.commentEntities = commentEntities;
    }
}
