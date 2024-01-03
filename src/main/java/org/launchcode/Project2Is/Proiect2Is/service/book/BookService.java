package org.launchcode.Project2Is.Proiect2Is.service.book;

import org.launchcode.Project2Is.Proiect2Is.model.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    List<Book> findAll();
    void insert(Book book);
    LocalDate parseLocalDate(String dateString);
    void deleteById(Long id);
    Book findBookById(Long id);
    void updateBook(Long id, String title, String author, String publishedDate, Long stock, Float price);

    void updateStock(Long id, long l);
}
