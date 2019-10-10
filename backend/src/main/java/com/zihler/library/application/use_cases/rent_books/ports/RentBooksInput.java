package com.zihler.library.application.use_cases.rent_books.ports;

import com.zihler.library.application.outbound_ports.persistence.BookRepository;
import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.values.ImageAndTextRental;
import com.zihler.library.domain.values.ImageRental;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.TextRental;

import java.util.ArrayList;
import java.util.List;

public class RentBooksInput {
    private final RentBooksRequest rentBooksRequest;
    private BookRepository bookRepository;

    public RentBooksInput(BookRepository bookRepository, RentBooksRequest rentBooksRequest) {
        this.rentBooksRequest = rentBooksRequest;
        this.bookRepository = bookRepository;
    }

    private static Rental createRental(Book book, int daysRented) {
        switch (book.getReadingMode()) {
            case "IMAGE":
                return new ImageRental(book, daysRented);
            case "TEXT":
                return new TextRental(book, daysRented);
            case "BOTH":
                return new ImageAndTextRental(book, daysRented);
            default:
                throw new RuntimeException("Could not find type " + book.getReadingMode());
        }
    }

    public String getCustomerName() {
        return rentBooksRequest.getCustomerName();
    }

    public List<Rental> createRentals() {
        List<Rental> rentals = new ArrayList<>();
        for (RentBookRequest rentBookRequest : rentBooksRequest.getRentBookRequests()) {
            Book book = this.bookRepository.findById(rentBookRequest.getBookId());
            Rental rental = createRental(book, rentBookRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }
}
