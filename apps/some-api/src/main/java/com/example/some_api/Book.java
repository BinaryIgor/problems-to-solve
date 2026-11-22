package com.example.some_api;

import java.util.List;

public record Book(long id, String title, String author, List<String> tags) {

    public static Book newOne(String title, String author) {
        return new Book(0, title, author, List.of());
    }

    public Book tagged(List<String> newTags) {
        tags.addAll(newTags);
        return new Book(id, title, author, tags);
    }

    public Book untagged(List<String> toRemoveTags) {
        var newTags = tags.stream()
                .filter(t -> !toRemoveTags.contains(t))
                .toList();
        return new Book(id, title, author, newTags);
    }
}
