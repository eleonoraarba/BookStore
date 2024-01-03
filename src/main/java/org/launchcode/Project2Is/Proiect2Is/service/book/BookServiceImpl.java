package org.launchcode.Project2Is.Proiect2Is.service.book;

import org.launchcode.Project2Is.Proiect2Is.model.Book;
import org.launchcode.Project2Is.Proiect2Is.repository.book.BookRepository;
import org.launchcode.Project2Is.Proiect2Is.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void insert(Book book) {
        bookRepository.save(book);
    }

     public LocalDate parseLocalDate(String dateString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy M d");
        return LocalDate.parse(dateString, formatter);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public void updateBook(Long id, String title, String author, String publishedDate, Long stock, Float price) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }

        Book book = bookRepository.findBookById(id);

        if (book != null) {
            if (title != null && !title.isEmpty()) {
                book.setTitle(title);
            }

            if (author != null && !author.isEmpty()) {
                book.setAuthor(author);
            }

            if (publishedDate != null && !publishedDate.isEmpty()) {
                book.setPublishedDate(parseLocalDate(publishedDate));
            }

            if (stock != null) {
                book.setStock(stock);
            }

            if (price != null) {
                book.setPrice(price);
            }

            bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    @Override
    public void updateStock(Long id, long newStock) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }

        Book book = bookRepository.findBookById(id);

        if (book != null) {
            book.setStock(newStock);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }


}
