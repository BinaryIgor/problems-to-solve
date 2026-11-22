package com.example.some_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/book")
    ResponseEntity<Book> createBook(CreateBookRequest request) {
        if (request.author() == null) {
            throw new IllegalArgumentException("author is required");
        }
        if (request.title().isBlank()) {
            throw new IllegalArgumentException("title is required");
        }
        var created = bookService.create(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(created);
    }

    @GetMapping("/books")
    List<Book> search(@RequestParam(required = false) String author,
                      @RequestParam(required = false) String title,
                      @RequestParam(required = false) List<String> tags) {
        return bookRepository.search(author, title, tags, 25);
    }

    @PostMapping("/books-tag")
    void tag(@RequestBody TagBooksRequest request) {
        bookService.tag(request);
    }

    @DeleteMapping("/book-tag")
    void untag(@RequestBody TagBooksRequest request) {
        bookService.untag(request);
    }

    @ExceptionHandler
    ProblemDetail handleBookValidationException(BookValidationException ex) {
        var pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, ex.getMessage());
        pd.setType(URI.create(ex.getClass().getSimpleName()));
        pd.setTitle("Book Validation Failure");
        return pd;
    }
}
