package com.example.LIbarayManagementSystem.Controller;

import com.example.LIbarayManagementSystem.Repository.BookRepository;
import com.example.LIbarayManagementSystem.Service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.TransformService;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController
{
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issue-book-on-library-card")
    public ResponseEntity<String>issueBookFromLibrary(@RequestParam("cid") Integer cardId,@RequestParam("bid") Integer bookId)
    {
        try
        {
            String result=transactionService.issueBookFromLibrary(cardId,bookId);
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }
        catch (Exception e)
        {
            log.error("Transaction Could Not Complete!!!"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
