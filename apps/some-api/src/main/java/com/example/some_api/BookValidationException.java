package com.example.some_api;

public class BookValidationException extends RuntimeException {

    public BookValidationException(String message) {
        super(message);
    }
}
