package org.launchcode.Project2Is.Proiect2Is.controller;

import jakarta.transaction.Transactional;
import org.launchcode.Project2Is.Proiect2Is.model.Book;
import org.launchcode.Project2Is.Proiect2Is.model.Role;
import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.launchcode.Project2Is.Proiect2Is.model.builder.BookBuilder;
import org.launchcode.Project2Is.Proiect2Is.service.book.BookService;
import org.launchcode.Project2Is.Proiect2Is.service.security.RoleService;
import org.launchcode.Project2Is.Proiect2Is.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("adminPage")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/showUsers")
    public ResponseEntity<List<User>> displayUsers(){
       List<User> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/insertUser")
    public ResponseEntity<String> insertUser(@RequestBody User newUser, @RequestParam boolean admin) {


        if (admin) {
            User user = User.builder()
                    .username(newUser.getUsername())
                    .password(newUser.getPassword())
                    .role((roleService.findByRole("ADMIN")))
                    .build();
            userService.insert(user);
        } else {
            User user = User.builder()
                    .username(newUser.getUsername())
                    .password(newUser.getPassword())
                    .role((roleService.findByRole("USER")))
                    .build();
            userService.insert(user);
        }

        return ResponseEntity.ok().body("Insert successful");
    }

    @PostMapping("/deleteUser")
    @Transactional
    public ResponseEntity<String> deleteUser(@RequestParam(required = true) Long id){
        userService.deleteById(id);
        return ResponseEntity.ok().body("Delete successfull");
    }

    @PostMapping("/updateUser")
    @Transactional
    public ResponseEntity<String> updateUser(@RequestParam(required = true) Long id, @RequestBody User user, @RequestParam boolean admin) {

        Role userRole = admin ? roleService.findByRole("ADMIN") : roleService.findByRole("USER");
        user.setRoles(Collections.singletonList(userRole));

        userService.updateUser(id, user.getUsername(), user.getPassword());

        return ResponseEntity.ok().body("Update successful");
    }


    @GetMapping("/showBooks")
    public ResponseEntity<List<Book>> displayBooks(){
        List<Book> books= bookService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PostMapping("/insertBook")
    public ResponseEntity<String> insertBook(@RequestBody Book newbook, @RequestParam(required = true)String publishedDate){
        Book book = new BookBuilder()
                .setAuthor(newbook.getAuthor())
                .setTitle(newbook.getTitle())
                .setPublishedDate(bookService.parseLocalDate(publishedDate))
                .setStock(newbook.getStock())
                .setPrice(newbook.getPrice())
                .build();

        bookService.insert(book);
        return ResponseEntity.ok().body("Insert successfull");
    }

    @PostMapping("/deleteBook")
    @Transactional
    public ResponseEntity<String> deleteBook(@RequestParam(required = true) Long id){
        bookService.deleteById(id);
        return ResponseEntity.ok().body("Delete successfull");
    }

    @PostMapping("/updateBook")
    @Transactional
    public ResponseEntity<String> updateBook(@RequestParam(required = true) Long id,@RequestParam(required = true)String publishedDate,@RequestBody Book book){

        String title = book.getTitle();
        String author = book.getAuthor();
        Long stock= book.getStock();
        Float price= book.getPrice();


        bookService.updateBook(id,title,author,publishedDate,stock,price);

        return ResponseEntity.ok().body("Update successfull ");
    }
}
