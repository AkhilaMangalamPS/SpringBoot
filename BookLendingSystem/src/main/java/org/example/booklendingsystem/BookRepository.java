package org.example.booklendingsystem;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> bookList = new ArrayList<>();
    public void save(Book book){
        bookList.add(book);
    }

    public List<Book> getAll(){
        return bookList;
    }

}
