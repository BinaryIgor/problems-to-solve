package com.example.some_api;

import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    List<Book> saveAll(List<Book> books);

    Optional<Book> findById(long id);

    List<Book> search(@Nullable String author, @Nullable String title, @Nullable Collection<String> tags, int limit);
}
