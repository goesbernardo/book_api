package com.goesbernardo.book_api.services;

import com.goesbernardo.book_api.dto.BookRecord;
import com.goesbernardo.book_api.domain.Book;
import com.goesbernardo.book_api.repository.BookRepository;
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
        // Setup
        final List<BookRecord> expectedResult = List.of(new BookRecord(0L, "title", "name", "isbn", "author"));

        // Configure BookRepository.findAll(...).
        final List<Book> books = List.of(new Book(0L, "title", "name", "isbn", "author"));
        when(mockBookRepository.findAll()).thenReturn(books);

        // Run the test
        final List<BookRecord> result = bookServiceImplUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAll_BookRepositoryReturnsNoItems() {
        // Setup
        when(mockBookRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<BookRecord> result = bookServiceImplUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetBookById() {
        // Setup
        final BookRecord expectedResult = new BookRecord(0L, "title", "name", "isbn", "author");

        // Configure BookRepository.findById(...).
        final Optional<Book> book = Optional.of(new Book(0L, "title", "name", "isbn", "author"));
        when(mockBookRepository.findById(0L)).thenReturn(book);

        // Run the test
        final BookRecord result = bookServiceImplUnderTest.getBookById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetBookById_BookRepositoryReturnsAbsent() {
        // Setup
        when(mockBookRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> bookServiceImplUnderTest.getBookById(0L)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testCreateHireBook() {
        // Setup
        final BookRecord record = new BookRecord(0L, "title", "name", "isbn", "author");
        final BookRecord expectedResult = new BookRecord(0L, "title", "name", "isbn", "author");

        // Configure BookRepository.save(...).
        final Book book = new Book(0L, "title", "name", "isbn", "author");
        when(mockBookRepository.save(new Book(0L, "title", "name", "isbn", "author"))).thenReturn(book);

        // Run the test
        final BookRecord result = bookServiceImplUnderTest.createHireBook(record);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetHireBookUnavailable() {
        // Setup
        final BookRecord bookRecord = new BookRecord(0L, "title", "name", "isbn", "author");
        final BookRecord expectedResult = new BookRecord(0L, "title", "name", "isbn", "author");

        // Configure BookRepository.findById(...).
        final Optional<Book> book = Optional.of(new Book(0L, "title", "name", "isbn", "author"));
        when(mockBookRepository.findById(0L)).thenReturn(book);

        // Run the test
        final BookRecord result = bookServiceImplUnderTest.getHireBookUnavailable(bookRecord);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetHireBookUnavailable_BookRepositoryReturnsAbsent() {
        // Setup
        final BookRecord bookRecord = new BookRecord(0L, "title", "name", "isbn", "author");
        when(mockBookRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> bookServiceImplUnderTest.getHireBookUnavailable(bookRecord))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testUpdateBook() {
        // Setup
        final BookRecord bookRecord = new BookRecord(0L, "title", "name", "isbn", "author");
        final BookRecord expectedResult = new BookRecord(0L, "title", "name", "isbn", "author");

        // Configure BookRepository.findById(...).
        final Optional<Book> book = Optional.of(new Book(0L, "title", "name", "isbn", "author"));
        when(mockBookRepository.findById(0L)).thenReturn(book);

        // Configure BookRepository.save(...).
        final Book book1 = new Book(0L, "title", "name", "isbn", "author");
        when(mockBookRepository.save(new Book(0L, "title", "name", "isbn", "author"))).thenReturn(book1);

        // Run the test
        final BookRecord result = bookServiceImplUnderTest.updateBook(0L, bookRecord);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateBook_BookRepositoryFindByIdReturnsAbsent() {
        // Setup
        final BookRecord bookRecord = new BookRecord(0L, "title", "name", "isbn", "author");
        when(mockBookRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> bookServiceImplUnderTest.updateBook(0L, bookRecord))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void testDeleteBook() {
        // Setup
        // Run the test
        bookServiceImplUnderTest.deleteBook(0L);

        // Verify the results
        verify(mockBookRepository).deleteById(0L);
    }
}
