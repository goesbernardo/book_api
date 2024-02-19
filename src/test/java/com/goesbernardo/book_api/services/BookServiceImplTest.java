package com.goesbernardo.book_api.services;

import com.goesbernardo.book_api.dto.BookRecord;
import com.goesbernardo.book_api.domain.Book;
import com.goesbernardo.book_api.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository mockBookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImplUnderTest;

    @Test
    void testGetAll() {
        final List<Book> books = List.of(new Book(0L, "title", "name", "isbn", "author"));
        when(mockBookRepository.findAll()).thenReturn(books);

        final List<Book> result = bookServiceImplUnderTest.getAll();

        Assertions.assertNotNull(result);
    }

    @Test
    void testGetAll_BookRepositoryReturnsNoItems() {
        when(mockBookRepository.findAll()).thenReturn(Collections.emptyList());

        final List<Book> result = bookServiceImplUnderTest.getAll();

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetBookById() {
        final Optional<Book> book = Optional.of(new Book(0L, "title", "name", "isbn", "author"));
        when(mockBookRepository.findById(0L)).thenReturn(book);

        final BookRecord result = bookServiceImplUnderTest.getBookById(0L);

        Assertions.assertNotNull(result);
    }

    @Test
    void testGetBookById_BookRepositoryReturnsAbsent() {
        when(mockBookRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookServiceImplUnderTest.getBookById(0L)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testGetHireBookUnavailable_BookRepositoryReturnsAbsent() {
        final BookRecord bookRecord = new BookRecord(0L, "title", "name", "isbn", "author");
        when(mockBookRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookServiceImplUnderTest.getHireBookUnavailable(bookRecord))
                .isInstanceOf(RuntimeException.class);
    }



    @Test
    void testUpdateBook_BookRepositoryFindByIdReturnsAbsent() {
        final BookRecord bookRecord = new BookRecord(0L, "title", "name", "isbn", "author");
        when(mockBookRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookServiceImplUnderTest.updateBook(0L, bookRecord))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testDeleteBook() {
        bookServiceImplUnderTest.deleteBook(0L);
        verify(mockBookRepository).deleteById(0L);
    }
}
