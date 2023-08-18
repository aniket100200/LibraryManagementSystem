package com.example.LIbarayManagementSystem.Repository;

import com.example.LIbarayManagementSystem.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>
{

}
