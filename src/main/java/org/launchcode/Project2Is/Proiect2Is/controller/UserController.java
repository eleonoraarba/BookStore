package org.launchcode.Project2Is.Proiect2Is.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.launchcode.Project2Is.Proiect2Is.model.Book;
import org.launchcode.Project2Is.Proiect2Is.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("storePage")
public class UserController {

    @Autowired
    private BookService bookService;

    private List<Map<Book, Integer>> order = new ArrayList<>();

    @GetMapping("/showBooks")
    public ResponseEntity<List<Book>> displayBooks(){
        List<Book> books= bookService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @PostMapping("/addToCart")
    @Transactional
    public ResponseEntity<String> addToCart(@RequestBody Book book, @RequestParam Integer quantity){

        Map<Book, Integer> bookQuantityPair = Map.of(book, quantity);

        order.add(bookQuantityPair);
        return ResponseEntity.ok().body("Book added to your cart!");
    }

    @GetMapping("/Total")
    public ResponseEntity<String> total(){

        Float total = 0.0f;
        if (order.isEmpty()) {
            return ResponseEntity.ok().body(String.valueOf(total));
        }
        else{
            for (Map<Book, Integer> map : order) {
                for (Map.Entry<Book, Integer> entry : map.entrySet()) {
                    Book book = entry.getKey();
                    Long quantity = Long.valueOf(entry.getValue());
                            total += quantity * book.getPrice();

                }

            }
        }
        return ResponseEntity.ok().body(String.valueOf(total));
    }

    @GetMapping("/buyBooks")
    public ResponseEntity<String> buyBooks(){
        if(order.isEmpty()){
            return ResponseEntity.ok().body("Cart is empty");
        }
        else {
            for (Map<Book, Integer> map : order) {
                for (Map.Entry<Book, Integer> entry : map.entrySet()) {
                    Book book = entry.getKey();
                    Long quantity = Long.valueOf(entry.getValue());

                    if (book.getStock() > quantity) {
                        bookService.updateStock(book.getId(), book.getStock() - quantity);
                    } else {
                        bookService.deleteById(book.getId());
                    }

                }
            }
        }
                order.clear();
        return ResponseEntity.ok().body("Enjoy your reading!");
    }
}
