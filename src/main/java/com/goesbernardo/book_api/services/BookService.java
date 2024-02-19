package com.goesbernardo.book_api.services;

import com.goesbernardo.book_api.domain.Book;
import com.goesbernardo.book_api.dto.BookRecord;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    BookRecord getBookById(Long id);

    BookRecord createHireBook(BookRecord bookRecord);

    BookRecord getHireBookUnavailable(BookRecord bookRecord);

    BookRecord updateBook(Long bookId,BookRecord bookRecord);

    void deleteBook(Long id);


}
