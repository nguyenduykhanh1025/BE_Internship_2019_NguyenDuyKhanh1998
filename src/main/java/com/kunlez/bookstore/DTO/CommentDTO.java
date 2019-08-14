package com.kunlez.bookstore.DTO;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class CommentDTO {

    private int id;

    @NotEmpty
    private String message;

    private Date updateAt;
    private Date createAt;
    private RegisterDTO registerDTO;

    public CommentDTO() {
    }

    public RegisterDTO getRegisterDTO() {
        return registerDTO;
    }

    public void setRegisterDTO(RegisterDTO registerDTO) {
        this.registerDTO = registerDTO;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
