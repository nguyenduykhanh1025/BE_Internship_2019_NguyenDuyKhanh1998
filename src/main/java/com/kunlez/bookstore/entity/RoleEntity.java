package com.kunlez.bookstore.entity;

import javax.persistence.*;

@Entity(name="role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id = 0;

    @Column
    private String name;

    public RoleEntity(String name) {
        this.name = name;
    }

    public RoleEntity() {
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
