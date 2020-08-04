package org.daistudy.springmvc.test.controller;

import org.daistudy.springmvc.test.entity.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BookController {

    @RequestMapping("/book")
    public Book getBookById() {
        Book book = new Book();
        book.setId(1);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        book.setDate(new Date(LocalDate.now().toEpochDay()*1000));
        book.setTestId(9223372036854775807L);
        return book;
    }

    @RequestMapping("/books")
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<Book>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setId(i);
            book.setName("三国演义:" + i);
            book.setAuthor("罗贯中:" + i);
            book.setDate(new Date(LocalDate.now().toEpochDay()*1000));
            book.setTestId(9223372036854775000L + i);
            list.add(book);
        }
        return list;
    }
}
