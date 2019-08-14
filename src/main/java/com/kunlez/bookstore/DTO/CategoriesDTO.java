package com.kunlez.bookstore.DTO;

import javax.validation.constraints.NotEmpty;

public class CategoriesDTO {
    private int id = 0;

    @NotEmpty
    private String name;

    public CategoriesDTO() {
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
