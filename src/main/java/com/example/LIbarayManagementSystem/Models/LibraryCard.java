package com.example.LIbarayManagementSystem.Models;

import com.example.LIbarayManagementSystem.Enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private int noOfBooksIssued;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    private  Student student; //this is the Foreign key bro..


    //bidirectional as Trancsaction is child..
    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();
}
