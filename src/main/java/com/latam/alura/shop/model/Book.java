package com.latam.alura.shop.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity

public class Book extends Product{

    private String author;
    private int pages;

    public Book() {}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Book(String author, int pages) {
         this.author = author;
         this.pages = pages;
    }


}
