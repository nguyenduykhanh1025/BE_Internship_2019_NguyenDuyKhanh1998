package com.kunlez.bookstore.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name="categories")
public class CategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;

    @Column
    private String name;

    @ManyToMany(mappedBy = "categoriesEntities")
    private Set<BookEntity> bookEntities;

    public CategoriesEntity() {
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

    public Set<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public void setBookEntities(Set<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }
}
