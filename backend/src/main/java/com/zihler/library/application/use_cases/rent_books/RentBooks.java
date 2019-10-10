package com.zihler.library.application.use_cases.rent_books;

import com.zihler.library.domain.entities.Customer;
import com.zihler.library.application.outbound_ports.persistence.CustomerRepository;
import com.zihler.library.application.outbound_ports.presentation.RentalRecordPresenter;
import com.zihler.library.application.use_cases.rent_books.ports.IRentBooks;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;
import com.zihler.library.domain.values.RentalRecordDocument;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksInput;

import java.util.List;

public class RentBooks implements IRentBooks {
    private final CustomerRepository customerRepository;

    public RentBooks(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void executeWith(RentBooksInput input, RentalRecordPresenter presenter) {
        Customer customer = this.customerRepository.findByUsername(input.getCustomerName());
        List<Rental> rentals = input.createRentals();
        RentalRecord rentalRecord = RentalRecord.from(customer, rentals);
        RentalRecordDocument rentalRecordDocument = rentalRecord.asDocument();
        presenter.present(rentalRecordDocument);
    }

}
