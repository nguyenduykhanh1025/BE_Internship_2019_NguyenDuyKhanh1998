package com.kunlez.bookstore.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BookDTO {
    private int id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @JsonAlias("created_at")
    private Date createdAt;

    @JsonAlias("update_at")
    private Date updateAt;

    @NotEmpty
    @JsonAlias("link_image")
    private String linkImage;

    @NotEmpty
    private String[] nameAuthor;

    @NotEmpty
    private String[] nameCategories;

    @JsonAlias("authors")
    private AuthorDTO[] authors;

    @JsonAlias("categories")
    private CategoriesDTO[] categoriesDTOS;

    private boolean enable;

    private List<CommentDTO> commentDTOList;

    private String namePoster;

    public BookDTO(@NotEmpty int id, @NotEmpty String title, @NotEmpty String description, @NotEmpty Date createdAt, @NotEmpty Date updateAt, @NotEmpty String linkImage, boolean enable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.linkImage = linkImage;
    }

    public BookDTO() {
    }

    public List<CommentDTO> getCommentDTOList() {
        return commentDTOList;
    }

    public void setCommentDTOList(List<CommentDTO> commentDTOList) {
        this.commentDTOList = commentDTOList;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String[] getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String[] nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String[] getNameCategories() {
        return nameCategories;
    }

    public void setNameCategories(String[] nameCategories) {
        this.nameCategories = nameCategories;
    }

    public AuthorDTO[] getAuthors() {
        return authors;
    }

    public void setAuthors(AuthorDTO[] authors) {
        this.authors = authors;
    }

    public CategoriesDTO[] getCategoriesDTOS() {
        return categoriesDTOS;
    }

    public void setCategoriesDTOS(CategoriesDTO[] categoriesDTOS) {
        this.categoriesDTOS = categoriesDTOS;
    }


    public AuthorDTO[] getAuthorDTOS() {
        return authors;
    }

    public void setAuthorDTOS(AuthorDTO[] authorDTOS) {
        this.authors = authorDTOS;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }


    public String getNamePoster() {
        return namePoster;
    }

    public void setNamePoster(String namePoster) {
        this.namePoster = namePoster;
    }
}
