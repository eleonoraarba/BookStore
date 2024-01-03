package org.launchcode.Project2Is.Proiect2Is.repository.book;

import org.launchcode.Project2Is.Proiect2Is.model.Book;
import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAll();
    Book findBookById(Long id);
    void deleteById(Long id);

}
