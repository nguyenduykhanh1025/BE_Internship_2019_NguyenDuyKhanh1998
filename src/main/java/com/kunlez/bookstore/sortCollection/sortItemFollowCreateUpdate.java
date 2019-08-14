package com.kunlez.bookstore.sortCollection;

import com.kunlez.bookstore.DTO.BookDTO;

import java.util.Comparator;

public class sortItemFollowCreateUpdate implements Comparator<BookDTO> {
    @Override
    public int compare(BookDTO o1, BookDTO o2) {
        return o2.getCreatedAt().compareTo(o1.getCreatedAt());
    }
}
