package com.zihler.library.use_cases.rent_books;

import com.zihler.library.adapters.file_persistance.FileBasedBookRepository;
import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.use_cases.rent_books.ports.RentBookRequest;
import com.zihler.library.use_cases.rent_books.ports.RentBooksRequest;

import java.util.ArrayList;
import java.util.List;

public class RentBooksInput {
    private final RentBooksRequest rentBooksRequest;
    private FileBasedBookRepository bookRepository;

    public RentBooksInput(FileBasedBookRepository bookRepository, RentBooksRequest rentBooksRequest) {
        this.rentBooksRequest = rentBooksRequest;
        this.bookRepository = bookRepository;
    }

    String getCustomerName() {
        return rentBooksRequest.getCustomerName();
    }

    List<Rental> getRentals() {
        List<Rental> rentals = new ArrayList<>();
        for (RentBookRequest rentBookRequest : rentBooksRequest.getRentBookRequests()) {
            Book book = this.bookRepository.findById(rentBookRequest.getBookId());
            Rental rental = new Rental(book, rentBookRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }
}
