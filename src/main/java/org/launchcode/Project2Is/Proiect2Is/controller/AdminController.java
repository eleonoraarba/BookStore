package org.launchcode.Project2Is.Proiect2Is.controller;

import jakarta.transaction.Transactional;
import org.launchcode.Project2Is.Proiect2Is.model.Book;
import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.launchcode.Project2Is.Proiect2Is.model.builder.BookBuilder;
import org.launchcode.Project2Is.Proiect2Is.service.book.BookServiceImpl;
import org.launchcode.Project2Is.Proiect2Is.service.security.RoleUserServiceImpl;
import org.launchcode.Project2Is.Proiect2Is.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("adminPage")
public class AdminController {
    @Autowired
    private UserServiceImpl userServiceImplementation;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    private RoleUserServiceImpl roleUserService;

    @GetMapping("/showUsers")
    public ResponseEntity<List<User>> displayUsers(){
       List<User> users = userServiceImplementation.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/insertUser")
    public ResponseEntity<String> insertUser(@RequestBody User newUser, @RequestParam boolean admin){

        userServiceImplementation.insert(newUser);
        if(admin){
            roleUserService.insertUserRole(newUser, "admin");
        }
        else{
            roleUserService.insertUserRole(newUser, "user");
        }
        return ResponseEntity.ok().body("Insert successfull");
    }

    @PostMapping("/deleteUser")
    @Transactional
    public ResponseEntity<String> deleteUser(@RequestParam(required = true) Long id){
        userServiceImplementation.deleteById(id);
        roleUserService.deleteById(id);
        return ResponseEntity.ok().body("Delete successfull");
    }

    @PostMapping("/updateUser")
    @Transactional
    public ResponseEntity<String> updateUser(@RequestParam(required = true) Long id,@RequestBody User user, @RequestParam boolean admin){
        String username= user.getUsername();;
        String password= user.getPassword();

        if(admin){
            roleUserService.updateUserRole(id, "admin");
        }
        else{
            roleUserService.updateUserRole(id, "user");
        }
        userServiceImplementation.updateUser(id,username,password);

        return ResponseEntity.ok().body("Update successfull ");
    }

    @GetMapping("/showBooks")
    public ResponseEntity<List<Book>> displayBooks(){
        List<Book> books= bookServiceImpl.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PostMapping("/insertBook")
    public ResponseEntity<String> insertBook(@RequestBody Book newbook, @RequestParam(required = true)String publishedDate){
        Book book = new BookBuilder()
                .setAuthor(newbook.getAuthor())
                .setTitle(newbook.getTitle())
                .setPublishedDate(bookServiceImpl.parseLocalDate(publishedDate))
                .setStock(newbook.getStock())
                .setPrice(newbook.getPrice())
                .build();

        bookServiceImpl.insert(book);
        return ResponseEntity.ok().body("Insert successfull");
    }

    @PostMapping("/deleteBook")
    @Transactional
    public ResponseEntity<String> deleteBook(@RequestParam(required = true) Long id){
        bookServiceImpl.deleteById(id);
        return ResponseEntity.ok().body("Delete successfull");
    }

    @PostMapping("/updateBook")
    @Transactional
    public ResponseEntity<String> updateBook(@RequestParam(required = true) Long id,@RequestParam(required = true)String publishedDate,@RequestBody Book book){

        String title = book.getTitle();
        String author = book.getAuthor();
        Long stock= book.getStock();
        Float price= book.getPrice();


        bookServiceImpl.updateBook(id,title,author,publishedDate,stock,price);

        return ResponseEntity.ok().body("Update successfull ");
    }
}
