package com.example.some_api;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceTest {

    private final TestBookRepository testBookRepository = new TestBookRepository();
    private final BookService service = new BookService(testBookRepository);

    @Test
    void createsNewBook() {
        var createRequest = new CreateBookRequest("some title", "some author");
        var expectedBook = new Book(1, createRequest.title(), createRequest.author(), List.of());

        testBookRepository.nextSavedBookToReturn = expectedBook;

        var createdBook = service.create(createRequest);

        assertThat(createdBook)
                .isEqualTo(expectedBook);
    }


    static class TestBookRepository implements BookRepository {

        Book nextSavedBookToReturn;

        @Override
        public Book save(Book book) {
            return nextSavedBookToReturn;
        }

        @Override
        public List<Book> saveAll(List<Book> books) {
            return List.of();
        }

        @Override
        public Optional<Book> findById(long id) {
            return Optional.empty();
        }

        @Override
        public List<Book> search(@Nullable String author, @Nullable String title, @Nullable Collection<String> tags, int limit) {
            return List.of();
        }
    }
}
