package com.zihler.domain;

public class BookKey {
    private int bookKey;

    private BookKey(int bookKey) {
        this.bookKey = bookKey;
    }

    public static BookKey from(int bookKey) {
        return new BookKey(bookKey);
    }

    public int get() {
        return bookKey;
    }

    @Override
    public String toString() {
        return String.valueOf(bookKey);
    }
}
