package com.goesbernardo.book_api.controller;

import com.goesbernardo.book_api.dto.BookRecord;
import com.goesbernardo.book_api.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    private Logger logger = LoggerFactory.getLogger(BookController.class);


    @GetMapping
    public ResponseEntity<List<BookRecord>> findAll() {

        logger.info("start process");
        List<BookRecord> records = bookService.getAll();
        return ResponseEntity.ok().body(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRecord> findBookById(@PathVariable("id") Long id) {

        logger.info("start process");
        BookRecord bookById = bookService.getBookById(id);
        return ResponseEntity.ok(bookById);
    }

    @PostMapping("/hireBook")
    public ResponseEntity<BookRecord> createByHireBook(@RequestBody @Valid BookRecord bookRecord) {

        logger.info("start process");
        BookRecord hireBook = bookService.createHireBook(bookRecord);
        return ResponseEntity.accepted().body(hireBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookRecord> updateBook(@PathVariable(value = "id") Long bookId,
                                                 @RequestBody BookRecord bookRecord) {
        logger.info("start process");
        BookRecord updateBook = bookService.updateBook(bookId, bookRecord);
        return ResponseEntity.ok().body(updateBook);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") Long id) {

        bookService.deleteBook(id);

    }
}