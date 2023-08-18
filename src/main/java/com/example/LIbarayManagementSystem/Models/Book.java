package com.example.LIbarayManagementSystem.Models;

import com.example.LIbarayManagementSystem.Enums.Genre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    @Column(unique = true)
    private String title;

    private boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private Date dataOfPublication;

    private Integer price;
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Author author;


    //bidirection mapping krni hai bro..
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();


    public Book(String title, boolean isAvailable, Genre genre, Date dataOfPublication, Integer price) {
        this.title = title;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.dataOfPublication = dataOfPublication;
        this.price = price;
    }
}
