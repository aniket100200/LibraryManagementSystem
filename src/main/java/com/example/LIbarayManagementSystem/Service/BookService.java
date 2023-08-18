package com.example.LIbarayManagementSystem.Service;

import com.example.LIbarayManagementSystem.Enums.Genre;
import com.example.LIbarayManagementSystem.Enums.TransactionStatus;
import com.example.LIbarayManagementSystem.Enums.TransactionType;
import com.example.LIbarayManagementSystem.Models.Author;
import com.example.LIbarayManagementSystem.Models.Book;
import com.example.LIbarayManagementSystem.Models.LibraryCard;
import com.example.LIbarayManagementSystem.Models.Transaction;
import com.example.LIbarayManagementSystem.Repository.AuthorRepository;
import com.example.LIbarayManagementSystem.Repository.BookRepository;
import com.example.LIbarayManagementSystem.Repository.LibraryCardRepository;
import com.example.LIbarayManagementSystem.Repository.TransactionRepository;
import com.example.LIbarayManagementSystem.RequestDTO.AddBookRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.LIbarayManagementSystem.Enums.TransactionStatus.FAILED;
import static com.example.LIbarayManagementSystem.Enums.TransactionStatus.SUCCESS;

@Service
public class BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String addBook(AddBookRequestDTO request)throws Exception
    {
        Optional<Author>optionalAuthor=authorRepository.findById(request.getAuthorId());
        if(!optionalAuthor.isPresent()) throw new Exception("Unable to find the Author");

        Author author=optionalAuthor.get();

        //let's have the book entity...
        Book book=new Book(request.getTitle(),request.isAvailable(),request.getGenre(),request.getDataOfPublication(),request.getPrice());

        //let's update both author and book..
        book.setAuthor(author); //author is Set...

        List<Book> bookList=author.getBookList();
        bookList.add(book);

        bookRepository.save(book);

        return "Book has been added updated SuccessFully..!!";
    }

    public Integer countOfBooksOfGenre(Genre genre)throws  Exception
    {
        List<Book> list=bookRepository.findAll();
        if(list==null)throw new Exception("There is No Book Found related to genre"+genre);

        int count=0;
        for(Book book:list)
        {
            if(book.getGenre().equals(genre)){
                count++;
            }
        }
        return count;
    }


}
