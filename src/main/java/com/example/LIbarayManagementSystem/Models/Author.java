package com.example.LIbarayManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String name;
    private String emailId;
    private int age;
    private String penName;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    List<Book> bookList=new ArrayList<>();
}
