package com.example.LIbarayManagementSystem.Service;

import com.example.LIbarayManagementSystem.Models.Author;
import com.example.LIbarayManagementSystem.Models.Book;
import com.example.LIbarayManagementSystem.Repository.AuthorRepository;
import com.example.LIbarayManagementSystem.Repository.BookRepository;
import com.example.LIbarayManagementSystem.RequestDTO.UpdateNameAndPenName;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;


    public String add(Author author)throws  Exception
    {
        if(author.getAuthorId()!=null)throw new Exception("You cannot provide the authorId");
         authorRepository.save(author);
         return "Author Added Successfully";
    }

    public String updateNameAndPenName(UpdateNameAndPenName updateNameAndPenName)throws Exception
    {
        Optional<Author> author=authorRepository.findById(updateNameAndPenName.getAuthorId());
        if(!author.isPresent())throw  new Exception("Author does not exists!!");
        Author author1=author.get();
        author1.setName(updateNameAndPenName.getNewName());
        author1.setPenName(updateNameAndPenName.getNewPenName());
        authorRepository.save(author1);
        return "Author has been Updated Successfully!!";
    }

    public List<Book> getListOfBooks(Integer authorId)throws Exception
    {
        //you have to return List of Titles//
        Optional<Author> optionalAuthor=authorRepository.findById(authorId);
        Author author=optionalAuthor.get();
        if(author==null)throw new Exception("Author Not Found");
        List<Book>bookList=author.getBookList();
       return bookList;
    }

    public Author getAuthorByAuthorId(Integer authorId)throws  Exception
    {
        if(!authorRepository.existsById(authorId))throw new Exception("Author Not Found");
        return authorRepository.findById(authorId).get();
    }
}
