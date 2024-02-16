package com.goesbernardo.book_api.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRecord {

    private Long id;
    private String title;
    @NotNull
    private String name;
    private String isbn;
    private String author;
}
