package com.example.some_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookControllerTest extends BaseIntegrationTest {

    @Autowired
    private BookService bookService;

    @Test
    void tagsBooks() {
        var book1 = bookService.create(new CreateBookRequest("title-1", "author-1"));
        var book2 = bookService.create(new CreateBookRequest("title-2", "author-2"));
        var book3 = bookService.create(new CreateBookRequest("title-3", "author-3"));

        var tag1 = "tag1";
        var tag2 = "tag2";
        var tag3 = "tag3";

        assertThat(searchBooks(tag1, tag2)).isEmpty();

        tagBooks(new TagBooksRequest(List.of(book1.id(), book2.id()), List.of(tag1, tag2)));
        tagBooks(new TagBooksRequest(List.of(book3.id()), List.of(tag3)));

        var taggedBook1 = bookWithTags(book1, tag1, tag2);
        var taggedBook2 = bookWithTags(book2, tag1, tag2);
        var taggedBook3 = bookWithTags(book3, tag3);

        assertThat(searchBooks(tag1, tag2))
                .containsExactlyInAnyOrder(taggedBook1, taggedBook2);
        assertThat(searchBooks(tag3))
                .containsExactlyInAnyOrder(taggedBook3);
    }

    private List<Book> searchBooks(String... tags) {
        return restTestClient.get()
                .uri(b -> {
                    var builder = b.path("/books");
                    for (var t : tags) {
                        builder = b.queryParam("tag", t);
                    }
                    return builder.build();
                })
                .exchangeSuccessfully()
                .returnResult(new ParameterizedTypeReference<List<Book>>() {
                })
                .getResponseBody();
    }

    private void tagBooks(TagBooksRequest request) {
        restTestClient.post()
                .uri("/books-tag")
                .body(request)
                .exchangeSuccessfully();
    }

    private Book bookWithTags(Book book, String... tags) {
        return new Book(book.id(), book.title(), book.author(), List.of(tags));
    }
}
