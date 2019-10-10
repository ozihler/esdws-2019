package com.zihler.library.adapters.file_persistance;

import com.zihler.library.application.outbound_ports.persistence.BookRepository;
import com.zihler.library.domain.entities.Book;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileBasedBookRepository implements BookRepository {
    private final List<Book> books;

    public FileBasedBookRepository(ResourceLoader resourceLoader) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:books.csv").getInputStream(),
                        StandardCharsets.UTF_8
                )
        );
        this.books = new ArrayList<>();
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] bookData = line.split(";");
            Book book = new Book(Integer.parseInt(bookData[0]), bookData[1], bookData[2], bookData[3], bookData[4]);
            books.add(book);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book findById(int bookId) {
        return getAllBooks().get(bookId);
    }
}
