package com.example.some_api;

import java.util.List;

public record TagBooksRequest(List<Long> bookIds, List<String> tags) {
}
