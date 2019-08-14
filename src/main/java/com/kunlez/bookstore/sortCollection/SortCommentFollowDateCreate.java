package com.kunlez.bookstore.sortCollection;

import com.kunlez.bookstore.DTO.CommentDTO;

import java.util.Comparator;

public class SortCommentFollowDateCreate implements Comparator<CommentDTO> {
    @Override
    public int compare(CommentDTO o1, CommentDTO o2) {
        return o1.getUpdateAt().compareTo(o2.getUpdateAt());
    }
}
