package com.kunlez.bookstore.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name="author")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;

    @Column
    private String name;

    @ManyToMany(mappedBy = "authorEntities")
    private Set<BookEntity> bookEntities;

    public AuthorEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
