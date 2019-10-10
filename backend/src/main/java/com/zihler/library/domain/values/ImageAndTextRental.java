package com.zihler.library.domain.values;

import com.zihler.library.domain.entities.Book;

public class ImageAndTextRental extends Rental {
    public ImageAndTextRental(Book book, int daysRented) {
        super(book, daysRented);
    }

    @Override
    double getAmount() {
        return daysRented * 3;
    }
}
