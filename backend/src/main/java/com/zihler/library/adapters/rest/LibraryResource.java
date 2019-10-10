package com.zihler.library.adapters.rest;

import com.zihler.library.adapters.controllers.FeeCalculationService;
import com.zihler.library.domain.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping("api/library")
public class LibraryResource {
    private final FeeCalculationService feeCalculationService;

    @Autowired
    public LibraryResource(FeeCalculationService feeCalculationService) {
        this.feeCalculationService = feeCalculationService;
    }

    @GetMapping(
            value = "/books",
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public List<String[]> getBooks() {

        final List<String[]> books = new ArrayList<>();
        for (Book book : feeCalculationService.bookRepository.getAllBooks()) {
            books.add(new String[]{
                    String.valueOf(book.getId()),
                    book.getTitle(),
                    book.getAuthors(),
                    book.getReadingMode(),
                    book.getThumbnailLink()
            });
        }
        return books;
    }

    @PostMapping(value = "/fee", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> calculateFee(@RequestBody List<String> rentBooksRequests) {
        return feeCalculationService.calculateFee(rentBooksRequests);
    }

}

