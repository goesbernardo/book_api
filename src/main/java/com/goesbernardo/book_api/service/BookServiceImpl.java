package com.goesbernardo.book_api.service;

import com.goesbernardo.book_api.dto.BookRecord;
import com.goesbernardo.book_api.mapper.BookMapper;
import com.goesbernardo.book_api.model.Book;
import com.goesbernardo.book_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    private final static BookMapper bookMapper = BookMapper.INSTANCE;

    @Override
    public List<BookRecord> getAll() {

        try {
            return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
        }catch (Exception e) {
            throw new RuntimeException();
        }

    }
    @Override
    public BookRecord getBookById(Long id) {

        return bookRepository.findById(id).stream().map(bookMapper::toDto).findFirst().orElseThrow(()-> new RuntimeException("book not found"));
   }

    @Override
    public BookRecord createHireBook(BookRecord record) {

        Book book = bookMapper.toModel(record);
        Book bookCreated = bookRepository.save(book);
        return bookMapper.toDto(bookCreated);
    }

    @Override
    public BookRecord getHireBookUnavailable(BookRecord bookRecord) {

        BookRecord bookById = this.getBookById(bookRecord.getId());
        if (!bookById.getName().isEmpty()){
            throw new RuntimeException("informação já existente no banco de dados , não é possível alugar o livro");
        }
        else {
            return bookById;
        }
    }

    @Override
    public BookRecord updateBook(Long bookId, BookRecord bookRecord) {

        Book book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("livro não encontrado"));
        book.setTitle(bookRecord.getTitle());
        book.setAuthor(bookRecord.getAuthor());
        book.setIsbn(bookRecord.getIsbn());

        Book bookUpdate = bookRepository.save(book);

        return bookMapper.toDto(bookUpdate);


    }

    @Override
    public void deleteBook(Long id) {

         bookRepository.deleteById(id);
    }
}
