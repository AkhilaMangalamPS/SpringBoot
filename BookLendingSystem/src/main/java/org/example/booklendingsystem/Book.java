package org.example.booklendingsystem;

public class Book {
    private int bookId;
    private String bookName;
    private boolean available = true;

    public Book(int bookId,String bookName){
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }
    public int getBookId(){
        return bookId;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public String getBookName(){
        return bookName;
    }

    public boolean isAvailable(){
        return available;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }
}
