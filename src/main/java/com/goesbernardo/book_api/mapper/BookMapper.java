package com.goesbernardo.book_api.mapper;

import com.goesbernardo.book_api.dto.BookRecord;
import com.goesbernardo.book_api.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toModel(BookRecord bookRecord);

    BookRecord toDto(Book book);
}
