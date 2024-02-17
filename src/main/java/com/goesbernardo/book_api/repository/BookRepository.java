package com.goesbernardo.book_api.repository;

import com.goesbernardo.book_api.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {




}
