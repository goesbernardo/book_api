package com.goesbernardo.book_api.service;

import com.goesbernardo.book_api.dto.BookRecord;
import com.goesbernardo.book_api.model.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {

    List<BookRecord> getAll();

    BookRecord getBookById(Long id);

    BookRecord createHireBook(BookRecord bookRecord);

    BookRecord getHireBookUnavailable(BookRecord bookRecord);

    BookRecord updateBook(Long bookId,BookRecord bookRecord);

    void deleteBook(Long id);


}
