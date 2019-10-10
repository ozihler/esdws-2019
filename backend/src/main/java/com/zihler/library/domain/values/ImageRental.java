package com.zihler.library.domain.values;

import com.zihler.library.domain.entities.Book;

public class ImageRental extends Rental {
    public ImageRental(Book book, int daysRented) {
        super(book, daysRented);
    }

    @Override
    double getAmount() {
        if (daysRented <= 2) {
            return 2;
        }
        return daysRented * 1.5 - 1.0;

    }
}
