package com.zihler.library.domain.values;

import com.zihler.library.domain.entities.Book;

public abstract class Rental {
    private final Book book;
    final int daysRented;

    protected Rental(Book book, int daysRented) {
        this.book = book;
        this.daysRented = daysRented;
    }

    int getDaysRented() {
        return daysRented;
    }

    String getBookAuthors() {
        return book.getAuthors();
    }

    String getBookTitle() {
        return book.getTitle();
    }

    int getFrequentRenterPoints() {
        // add bonus for a reading mode "both"
        if (book.getReadingMode().equals("BOTH") && daysRented > 1) {
            return 2;
        }
        return 1;
    }

    abstract double getAmount();

    RentalDocument asDocument() {
        return RentalDocument.from(this);
    }
}
