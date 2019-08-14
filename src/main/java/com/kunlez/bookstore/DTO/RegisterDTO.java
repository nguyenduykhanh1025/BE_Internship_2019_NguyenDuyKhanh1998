package com.kunlez.bookstore.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class RegisterDTO {

    private int id;

    @NotEmpty
    @NotBlank
    private String email;


    @NotEmpty
    @NotBlank
    private String password;

    @NotEmpty
    @JsonAlias("first_name")
    private String firstName;

    @NotEmpty
    @JsonAlias("last_name")
    private String lastName;

    @NotEmpty
    @JsonAlias("link_avatar")
    private String linkAvatar;

    private boolean enable;

    public RegisterDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
