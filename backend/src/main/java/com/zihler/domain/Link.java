package com.zihler.domain;

public class Link {
    private String link;

    private Link(String link) {
        this.link = link;
    }

    public static Link from(String link) {
        return new Link(link);
    }


    @Override
    public String toString() {
        return link;
    }

}
