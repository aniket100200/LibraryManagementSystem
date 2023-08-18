package com.example.LIbarayManagementSystem.Service;

import com.example.LIbarayManagementSystem.CustomException.BookRelatedException.BookIsNotAvailableException;
import com.example.LIbarayManagementSystem.CustomException.BookRelatedException.BookLimitExceededException;
import com.example.LIbarayManagementSystem.CustomException.BookRelatedException.BookNotFoundException;
import com.example.LIbarayManagementSystem.CustomException.CardRelaedException.CardIsNotInCorrectState;
import com.example.LIbarayManagementSystem.CustomException.CardRelaedException.CardNotFoundException;
import com.example.LIbarayManagementSystem.Enums.CardStatus;
import com.example.LIbarayManagementSystem.Enums.TransactionStatus;
import com.example.LIbarayManagementSystem.Enums.TransactionType;
import com.example.LIbarayManagementSystem.Models.Book;
import com.example.LIbarayManagementSystem.Models.LibraryCard;
import com.example.LIbarayManagementSystem.Models.Transaction;
import com.example.LIbarayManagementSystem.Repository.BookRepository;
import com.example.LIbarayManagementSystem.Repository.LibraryCardRepository;
import com.example.LIbarayManagementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.LimitExceededException;
import java.util.List;

import static com.example.LIbarayManagementSystem.Enums.TransactionStatus.FAILED;
import static com.example.LIbarayManagementSystem.Enums.TransactionStatus.SUCCESS;

@Service
public class TransactionService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
   private LibraryCardRepository libraryCardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${book.maxLimit}")
    private Integer maxBookLimit;

    private final Integer bookLimit=6;
    public String issueBookFromLibrary(Integer cardId,Integer bookId)throws Exception
    {


        //card Relaetd Validation...
        Transaction transaction=new Transaction(TransactionStatus.PENDING,TransactionType.ISSUED,0);

        LibraryCard libraryCard=libraryCardRepository.findById(cardId).get();
        if(!libraryCardRepository.existsById(cardId))throw new CardNotFoundException("card With Id Doesn't Exists");
        if(!libraryCard.getCardStatus().equals(CardStatus.Active))
        {
            transaction.setTransactionStatus(FAILED);
            transactionRepository.save(transaction);
            throw new CardIsNotInCorrectState("Card State is in State " + libraryCard.getCardStatus());
        }

        //book Related Validation..
        Book book=bookRepository.findById(bookId).get();
        if(!bookRepository.existsById(bookId))throw new BookNotFoundException("Book with Id Not Found");
        if(!book.isAvailable())throw new BookIsNotAvailableException("Book is Not Avaliable");

        // as everything is happened..
        if(libraryCard.getNoOfBooksIssued()>=bookLimit) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction = transactionRepository.save(transaction);
            throw new BookLimitExceededException("You have already Exceeded the maximum Limit of Books");
        }

        //we have book as well as card..

        /*
        All Feilds done..
         */
        transaction=transactionRepository.save(transaction);

        transaction.setTransactionStatus(SUCCESS);
        //updaet card and Book..
        book.setAvailable(false);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);

        //We need to do unidirectional mappings :-->
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        bookRepository.save(book);
        libraryCardRepository.save(libraryCard);


        return "Transaction has been saved successfully";

    }
}
