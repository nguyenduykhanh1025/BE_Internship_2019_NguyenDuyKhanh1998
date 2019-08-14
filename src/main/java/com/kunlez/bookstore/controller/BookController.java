package com.kunlez.bookstore.controller;

import com.kunlez.bookstore.DTO.BookDTO;
import com.kunlez.bookstore.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookServices bookServices;

    @GetMapping("/{indexPage}/{numberItem}")
    public List<BookDTO> getBookPag(@PathVariable int indexPage, @PathVariable int numberItem){
        return bookServices.getBookPagination(indexPage, numberItem);
    }
    @GetMapping("/{id}")
    public BookDTO getBookFollowId(@PathVariable int id){
        return bookServices.getBookFollowId(id);
    }

    @GetMapping("/categories/{idCategories}")
    public List<BookDTO> getAllBookFollowCategories(@PathVariable int idCategories){
        return bookServices.getAllBookFollowCategories(idCategories);
    }



    @GetMapping("/user")
    public List<BookDTO> getAllBookFollowUser(@RequestHeader("Authorization") String token){
        return bookServices.getAllBookFollowUser(token);
    }

    @GetMapping("/search")
    public List<BookDTO> getAllBookFollowCategories(@RequestParam(name = "valueSearch") String valueSearch){
        return bookServices.getAllBookSearchByKeyword(valueSearch);
    }

    @Secured("ROLE_MEMBER")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id, @RequestHeader("Authorization") String token){
        System.out.println(token);
       return bookServices.deleteBook(id, token);
    }

    @Secured(("ROLE_MEMBER"))
    @PostMapping
    public BookDTO post(@RequestBody @Validated BookDTO bookDTO){
        bookServices.post(bookDTO);
        return bookDTO;

    }
    @Secured({"ROLE_MEMBER"})
    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Validated BookDTO bookDTO, @RequestHeader("Authorization") String token){
        // client send title & description & nameCategories & linkImage & nameAuthor
        // set categories
        return bookServices.update(bookDTO, token);
    }
    @GetMapping("/enable/{id}")
    public void enableID(@PathVariable int id){
        bookServices.enableID(id);
    }

    @GetMapping("/sort/{valueSort}")
    public List<BookDTO> getSortListBook(@PathVariable int valueSort){
        return bookServices.getSortListBook(valueSort);
    }

    @GetMapping("/author/{nameAuthor}")
    public List<BookDTO> getBookFollowNameAuthor(@PathVariable String nameAuthor){
        return bookServices.getBookFollowNameAuthor(nameAuthor);
    }

    @GetMapping("/poster/{namePoster}")
    public List<BookDTO> getListBookFollowNamePoster(@PathVariable String namePoster){
        return bookServices.getListBookFollowNamePoster(namePoster);
    }

    @GetMapping
    public List<BookDTO> get(@RequestParam(name = "numberItem") int numberItem,
                             @RequestParam(name="indexPage") int indexPage,
                             @RequestParam(name="idCategories") int idCategories,
                             @RequestParam(name="valueSort") int valueSort
                            ){

        return bookServices.get(numberItem, indexPage, idCategories, valueSort);
    }
}
