package com.example.LIbarayManagementSystem.Controller;

import com.example.LIbarayManagementSystem.Enums.Genre;
import com.example.LIbarayManagementSystem.Models.Book;
import com.example.LIbarayManagementSystem.Repository.BookRepository;
import com.example.LIbarayManagementSystem.RequestDTO.AddBookRequestDTO;
import com.example.LIbarayManagementSystem.ResponceDto.AddBookResponceDto;
import com.example.LIbarayManagementSystem.Service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController
{
    @Autowired
    private BookService bookRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody AddBookRequestDTO addBookRequestDTO)
    {
        try
        {
                String result= bookRepository.addBook(addBookRequestDTO);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            log.error("Book Not add {}"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/countBook-By-Genre/{genre}")
    public ResponseEntity<Integer>countOfBook(@PathVariable("genre") Genre genre)
    {
        try{
            Integer count = bookRepository.countOfBooksOfGenre(genre);
            return new ResponseEntity<>(count,HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Book list is Empty"+e.getMessage());
            return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
        }

    }




}
