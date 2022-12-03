package com.driver.services;

import com.driver.models.Book;
import com.driver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository2;

    public void createBook(Book book){
        bookRepository2.save(book);
    }

    public List<Book> getBooks(String genre, boolean available, String author){
        List<Book> allBooks = bookRepository2.findAll();
        List<Book> books = new ArrayList<>(); //find the elements of the list by yourself
        if (available){
            if(genre != null && author != null){
                System.out.println("Nothing null and avail = true");
                for (Book b : allBooks){
                    if (b.getGenre().toString().equals(genre) && b.getAuthor().getName().equals(author) && b.isAvailable()){
                        books.add(b);
                    }
                }
            }
            else if(author == null){
                System.out.println("author null and avail = true");
                for (Book b : allBooks){
                    if(b.getGenre().toString().equals(genre) && b.isAvailable()){
                        books.add(b);
                    }
                }
            }
            else if (genre == null){
                System.out.println("genre null and avail = true");
                for (Book b : allBooks){
                    if(b.getAuthor().getName().equals(author) && b.isAvailable()){
                        books.add(b);
                    }
                }
            }else {

            }
        }
        else {
            if(genre != null && author != null){
                System.out.println("Nothing null and avail = false");
                for (Book b : allBooks){
                    if (b.getGenre().toString().equals(genre) && b.getAuthor().getName().equals(author) && !b.isAvailable()){
                        books.add(b);
                    }
                }
            }
            else if(author == null){
                System.out.println("author null and avail = false");
                for (Book b : allBooks){
                    if(b.getGenre().toString().equals(genre) && !b.isAvailable()){
                        books.add(b);
                    }
                }
            }
            else if (genre == null){
                System.out.println("genre null and avail = false");
                for (Book b : allBooks){
                    if(b.getAuthor().getName().equals(author) && !b.isAvailable()){
                        books.add(b);
                    }
                }
            }else {

            }
        }

        return books;
    }
}
