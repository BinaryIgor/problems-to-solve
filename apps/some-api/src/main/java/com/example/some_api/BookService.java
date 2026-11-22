package com.example.some_api;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(CreateBookRequest request) {
        return bookRepository.save(Book.newOne(request.title(), request.author()));
    }

    public void tag(TagBooksRequest request) {
        if (request.bookIds().isEmpty()) {
            return;
        }
        var toTag = request.bookIds().stream()
                .flatMap(id -> bookRepository.findById(id).stream())
                .toList();

        toTag.forEach(t -> {
            var tagged = t.tagged(request.tags());
            bookRepository.save(tagged);
        });
    }

    public void untag(TagBooksRequest request) {
        if (request.bookIds() == null || request.bookIds().isEmpty()) {
            throw new BookValidationException("At least one book is required for untagging");
        }

        var toUntag = bookRepository.search(null, null, null, 10).stream()
                .filter(b -> request.bookIds().contains(b.id()))
                .map(b -> b.untagged(request.tags()))
                .toList();

        bookRepository.saveAll(toUntag);
    }
}
