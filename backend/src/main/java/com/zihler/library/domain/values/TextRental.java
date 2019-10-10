package com.zihler.library.domain.values;

import com.zihler.library.domain.entities.Book;

public class TextRental extends Rental {
    public TextRental(Book book, int daysRented) {
        super(book, daysRented);
    }

    @Override
    double getAmount() {
        if (daysRented > 3) {
            return daysRented * 1.5 - 3.0;
        }

        return 1.5;
    }
}
