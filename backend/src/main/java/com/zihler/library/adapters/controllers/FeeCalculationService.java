package com.zihler.library.adapters.controllers;

import com.zihler.library.adapters.rest.LibraryResource;
import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.application.outbound_ports.persistence.BookRepository;
import com.zihler.library.application.outbound_ports.persistence.CustomerRepository;
import com.zihler.library.application.use_cases.rent_books.RentBooks;
import com.zihler.library.application.use_cases.rent_books.ports.IRentBooks;
import com.zihler.library.application.use_cases.rent_books.ports.RentBookRequest;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksInput;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FeeCalculationService {
    public final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public FeeCalculationService(BookRepository bookRepository, CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public List<RentBookRequest> getRentBookRequests(List<String> rentBooksRequests) {
        List<RentBookRequest> rentBookRequests = new ArrayList<>();
        for (int i = 0; i < rentBooksRequests.size(); i++) {
            final String[] rentalData = rentBooksRequests.get(i).split(" ");
            RentBookRequest rentBookRequest = new RentBookRequest(Integer.parseInt(rentalData[0]), Integer.parseInt(rentalData[1]));
            rentBookRequests.add(rentBookRequest);
        }
        return rentBookRequests;
    }

    public List<String> calculateFee(List<String> rentBooksRequests) {
        if (rentBooksRequests == null || rentBooksRequests.size() == 0) {
            throw new IllegalArgumentException("rent books requests cannot be null!");
        }
        String customerName = rentBooksRequests.remove(0);
        List<RentBookRequest> rentBookRequests = getRentBookRequests(rentBooksRequests);
        RentBooksRequest rentBooksRequest = RentBooksRequest.from(customerName, rentBookRequests);
        RentBooksInput rentBooksInput = new RentBooksInput(bookRepository, rentBooksRequest);

        RestRentalRecordPresenter restRentalRecordPresenter = new RestRentalRecordPresenter();
        IRentBooks rentBooks = new RentBooks(customerRepository);
        rentBooks.executeWith(rentBooksInput, restRentalRecordPresenter);

        return restRentalRecordPresenter.presentation();
    }
}
