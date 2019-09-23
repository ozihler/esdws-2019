package com.zihler.library;

import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class FileBasedInMemoryBookRepository {
    private final List<Book> books;

    FileBasedInMemoryBookRepository(ResourceLoader resourceLoader) throws IOException {
        this.books = loadBooks(resourceLoader);
    }

    List<Book> getAll() {
        return books;
    }

    private List<Book> loadBooks(ResourceLoader resourceLoader) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:books.csv").getInputStream(),
                        StandardCharsets.UTF_8
                )
        );
        final List<Book> books = new ArrayList<>();
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            Book book = Book.from(line);
            books.add(book);
        }
        return books;
    }

    Book getByKey(int bookKey) {
        return this.books.get(bookKey);
    }
}
