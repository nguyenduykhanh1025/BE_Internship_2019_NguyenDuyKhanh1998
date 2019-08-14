package com.kunlez.bookstore.DTO;

import javax.validation.constraints.NotEmpty;

public class AuthorDTO {
    @NotEmpty
    private int id;

    @NotEmpty
    private String name;

    public AuthorDTO(@NotEmpty int id, @NotEmpty String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorDTO() {
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
