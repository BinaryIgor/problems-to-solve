package com.example.some_api;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final Map<Long, Book> books = new HashMap<>();
    private long nextId = 1;

    @Override
    public Book save(Book book) {
        Book saved;
        if (book.id() <= 0) {
            saved = new Book(nextId++, book.title(), book.author(), book.tags());
        } else {
            saved = new Book(book.id(), book.title(), book.author(), book.tags());
        }
        books.put(saved.id(), saved);
        return saved;
    }

    @Override
    public List<Book> saveAll(List<Book> books) {
        var saved = new ArrayList<Book>(books.size());
        for (Book book : books) {
            saved.add(save(book));
        }
        return saved;
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public List<Book> search(@Nullable String author, @Nullable String title, @Nullable Collection<String> tags, int limit) {
        if (limit <= 0) {
            return List.of();
        }

        var matches = new ArrayList<Book>();
        for (Book book : books.values()) {
            if (author != null && !book.author().equals(author)) {
                continue;
            }
            if (title != null && !book.title().contains(title)) {
                continue;
            }
            if (tags != null && !tags.isEmpty() && !book.tags().containsAll(tags)) {
                continue;
            }

            matches.add(book);
            if (matches.size() == limit) {
                break;
            }
        }
        return matches;
    }
}
