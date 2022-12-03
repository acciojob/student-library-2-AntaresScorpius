package com.driver.controller;

import com.driver.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Add required annotations

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    //Add required annotations
    @PostMapping("transaction/issueBook")
    public ResponseEntity<String> issueBook(@RequestParam("cardId") int cardId, @RequestParam("bookId") int bookId) throws Exception{
        System.out.println("cardId = " + cardId);
        System.out.println("bookId = " + bookId);
        String transactionId = transactionService.issueBook(cardId,bookId);
        System.out.println("transactionId = " + transactionId);
        return new ResponseEntity<>("transaction completed", HttpStatus.ACCEPTED);
    }

    //Add required annotations
    @PostMapping("transaction/returnBook")
    public ResponseEntity returnBook(@RequestParam("cardId") int cardId, @RequestParam("bookId") int bookId) throws Exception{

        return new ResponseEntity<>("transaction completed", HttpStatus.ACCEPTED);
    }
}
