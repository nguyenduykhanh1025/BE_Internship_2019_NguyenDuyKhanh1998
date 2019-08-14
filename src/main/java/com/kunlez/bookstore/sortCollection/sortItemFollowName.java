package com.kunlez.bookstore.sortCollection;

import com.kunlez.bookstore.DTO.BookDTO;

import java.util.Comparator;

public class sortItemFollowName implements Comparator<BookDTO> {
    @Override
    public int compare(BookDTO o1, BookDTO o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
