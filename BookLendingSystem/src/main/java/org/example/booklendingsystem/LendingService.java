package org.example.booklendingsystem;

import org.springframework.stereotype.Service;

@Service
public class LendingService {
    private final UserService userService;
    private final BookService bookService;
    private final Notificationservice notificationservice;

    public LendingService(UserService userService,BookService bookService,Notificationservice notificationservice){
        this.userService = userService;
        this.bookService = bookService;
        this.notificationservice = notificationservice;
    }

    public String borrowBook(int userId, int bookId){
        User user = userService.getUser(userId);
        Book book = bookService.getAll().stream().filter(b -> b.getBookId() == bookId).findFirst().orElse(null);
        if(user == null){
            return "User not fund";
        }
        if(book == null){
            return "Book not found";
        }
        if(!book.isAvailable()){
            return "Book already borrowed";
        }
        book.setAvailable(false);
        notificationservice.notifyUser(user.getUserName()+ " borrowed "+ book.getBookName());
        return "Book borrowed successfully";
    }

    public String returnBook(int userId,int bookID){
        User user = userService.getUser(userId);
        Book book = bookService.getAll().stream().filter(b -> b.getBookId() == bookID).findFirst().orElse(null);
        if(user == null){
            return "User not found";
        }
        if(book == null){
            return "Book not found";
        }
        if(book.isAvailable()){
            return "Book is already in library";
        }
        book.setAvailable(true);
        notificationservice.notifyUser(user.getUserName()+ " returned "+book.getBookName());
        return "Book returned successfully";

    }
}
