package com.zihler.library.use_cases.rent_books;

import com.zihler.library.InMemoryCustomerRepository;
import com.zihler.library.adapters.file_persistance.FileBasedBookRepository;
import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.entities.Customer;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;
import com.zihler.library.use_cases.rent_books.ports.RentBookRequest;
import com.zihler.library.use_cases.rent_books.ports.RentBooksRequest;

import java.util.ArrayList;
import java.util.List;

public class RentBooks {
    private final InMemoryCustomerRepository customerRepository;
    private final FileBasedBookRepository bookRepository;

    public RentBooks(InMemoryCustomerRepository customerRepository, FileBasedBookRepository bookRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    public void with(RentBooksRequest rentBooksRequest, RestRentalRecordPresenter restRentalRecordPresenter) {
        Customer customer = this.customerRepository.findByUsername(rentBooksRequest.getCustomerName());
        List<Rental> rentals = rentals(rentBooksRequest.getRentBookRequests());
        RentalRecord rentalRecord = RentalRecord.from(customer, rentals);
        restRentalRecordPresenter.present(rentalRecord);
    }

    private List<Rental> rentals(List<RentBookRequest> rentBookRequests) {
        List<Rental> rentals = new ArrayList<>();
        for (RentBookRequest rentBookRequest : rentBookRequests) {
            Book book = bookRepository.findById(rentBookRequest.getBookId());
            Rental rental = new Rental(book, rentBookRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }
}
