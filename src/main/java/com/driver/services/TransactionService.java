package com.driver.services;

import com.driver.models.Book;
import com.driver.models.Card;
import com.driver.models.Transaction;
import com.driver.models.TransactionStatus;
import com.driver.repositories.BookRepository;
import com.driver.repositories.CardRepository;
import com.driver.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    BookRepository bookRepository5;

    @Autowired
    CardRepository cardRepository5;

    @Autowired
    TransactionRepository transactionRepository5;

    @Value("${books.max_allowed}")
    public int max_allowed_books;

    @Value("${books.max_allowed_days}")
    public int getMax_allowed_days;

    @Value("${books.fine.per_day}")
    public int fine_per_day;

    public String issueBook(int cardId, int bookId) throws Exception {
        //check whether bookId and cardId already exist
        Card card;
        Book book;
        //2. card is present and activated
        // If it fails: throw new Exception("Card is invalid");
        try{
            card = cardRepository5.findById(cardId).get();
        }catch (NoSuchElementException e){
            throw new Exception("Card is Invalid");
        }
        //1. book is present and available
        // If it fails: throw new Exception("Book is either unavailable or not present");
        try{
            book = bookRepository5.findById(bookId).get();
        }catch (NoSuchElementException e){
            throw new Exception("Book is either unavailable or not present");
        }
        System.out.println("Card Found: " +card);
        System.out.println("book found = " + book);

        //conditions required for successful transaction of issue book:
        //3. number of books issued against the card is strictly less than max_allowed_books
        // If it fails: throw new Exception("Book limit has reached for this card");
        if (card.getBooks().size() >= 3){
            throw new Exception("Book limit has reached for this card");
        }

        book.setAvailable(false);
        book.setCard(card);
        Transaction transaction;
        if(!card.getCardStatus().toString().equalsIgnoreCase("DEACTIVATED")){
            transaction = Transaction.builder()
                    .book(book)
                    .card(card)
                    .fineAmount(0)
                    .transactionStatus(TransactionStatus.SUCCESSFUL)
                    .isIssueOperation(true)
                    .transactionId(UUID.randomUUID().toString())
                    .build();
        }
        //If the transaction is successful, save the transaction to the list of transactions and return the id
        else {

            transaction = Transaction.builder()
                    .book(book)
                    .card(card)
                    .fineAmount(0)
                    .transactionStatus(TransactionStatus.FAILED)
                    .isIssueOperation(true)
                    .transactionId(UUID.randomUUID().toString())
                    .build();
        }

        transactionRepository5.save(transaction);
        //Note that the error message should match exactly in all cases

       return transaction.getTransactionId(); //return transactionId instead
    }

    public Transaction returnBook(int cardId, int bookId) throws Exception{

//        List<Transaction> transactions = transactionRepository5.findAll();
//        Transaction transaction = transactions.get(transactions.size() - 1);
//        System.out.println("All transactions = " + transactions);
//        System.out.println("One transaction = " + transaction);
        Card card;
        Book book;
        //2. card is present and activated
        // If it fails: throw new Exception("Card is invalid");
        try{
            card = cardRepository5.findById(cardId).get();
        }catch (NoSuchElementException e){
            throw new Exception("Card is Invalid");
        }
        //1. book is present and available
        // If it fails: throw new Exception("Book is either unavailable or not present");
        try{
            book = bookRepository5.findById(bookId).get();
        }catch (NoSuchElementException e){
            throw new Exception("Book is either unavailable or not present");
        }
        System.out.println("Card Found: " +card);
        System.out.println("book found = " + book);
        book.setAvailable(true);
        book.setCard(null);
        Transaction transaction = Transaction.builder()
                .book(book)
                .card(card)
                .fineAmount(0)
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .isIssueOperation(false)
                .transactionId(UUID.randomUUID().toString())
                .build();
        transactionRepository5.save(transaction);

        //for the given transaction calculate the fine amount considering the book has been returned exactly when this function is called
        //make the book available for other users
        //make a new transaction for return book which contains the fine amount as well

        Transaction returnBookTransaction  = transaction;
        return returnBookTransaction; //return the transaction after updating all details
    }
}
