package org.launchcode.Project2Is.Proiect2Is.model.builder;

import org.launchcode.Project2Is.Proiect2Is.model.Book;

import java.time.LocalDate;

public class BookBuilder {
    private Book book;

    public BookBuilder(){
        book = new Book();
    }

    public BookBuilder setAuthor(String author){
        book.setAuthor(author);
        return this;
    }

    public BookBuilder setTitle(String title){
        book.setTitle(title);
        return this;
    }

    public BookBuilder setPublishedDate(LocalDate publishedDate){
        book.setPublishedDate(publishedDate);
        return this;
    }

    public BookBuilder setStock(Long stock){
        book.setStock(stock);
        return this;
    }

    public BookBuilder setPrice(Float price){
        book.setPrice(price);
        return this;
    }


    public Book build(){
        return book;
    }


}
