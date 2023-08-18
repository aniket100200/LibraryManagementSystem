package com.example.LIbarayManagementSystem.Controller;

import com.example.LIbarayManagementSystem.Models.Author;
import com.example.LIbarayManagementSystem.Models.Book;
import com.example.LIbarayManagementSystem.RequestDTO.UpdateNameAndPenName;
import com.example.LIbarayManagementSystem.Service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController
{
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
            try{
                String result=authorService.add(author);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }
            catch (Exception e)
            {
                log.error("Author cannot be added {} "+e.getMessage());
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }

    }

    @PutMapping("/updateNameAndPenName")
    public ResponseEntity<String>updateNameAndPenName(@RequestBody UpdateNameAndPenName updateNameAndPenName)
    {
        try{
            String result=authorService.updateNameAndPenName(updateNameAndPenName);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Author Id is Invalid {} "+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-book-list-by-authorId/{id}")
    private ResponseEntity<List<Book>>bookList(@PathVariable("id") Integer authorId)
    {
        try{
            List<Book> ans = authorService.getListOfBooks(authorId);
            return new ResponseEntity<>(ans, HttpStatus.OK);
        }
        catch (Exception e){
            log.error("List Not Fount"+e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-author-by-author-id")
    public ResponseEntity<Author>getAuthorByAuthorId(@RequestParam("id") Integer authorId)
    {
       try{
           Author author=authorService.getAuthorByAuthorId(authorId);
           return new ResponseEntity<>(author,HttpStatus.ACCEPTED);
       }
       catch (Exception e){
           log.error("Could not preocess your Request {} "+e.getMessage());
           return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
       }
    }
}
