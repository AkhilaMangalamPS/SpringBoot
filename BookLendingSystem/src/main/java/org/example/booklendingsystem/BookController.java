package org.example.booklendingsystem;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public String addBook(@RequestBody Book book){
        service.addBook(book);
        return "Book added successfully";
    }

    @GetMapping("/available")
    public List<Book> availableBooks(){
        return service.getAll();
    }
}
