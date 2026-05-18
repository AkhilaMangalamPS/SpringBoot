package org.example.booklendingsystem;

import org.springframework.stereotype.Service;

import java.util.List;


import java.util.List;
@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public void addBook(Book book){
        repo.save(book);
    }

    public List<Book> getAll(){
        return repo.getAll();
    }

    public List<Book> getAvailableBook(){
        return repo.getAll().stream().filter(Book::isAvailable).toList();
    }
}
