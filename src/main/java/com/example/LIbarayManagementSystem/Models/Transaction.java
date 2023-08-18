package com.example.LIbarayManagementSystem.Models;

import com.example.LIbarayManagementSystem.Enums.TransactionStatus;
import com.example.LIbarayManagementSystem.Enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    public Transaction(TransactionStatus transactionStatus, TransactionType transactionType, Integer fineAmount)
    {
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.fineAmount = fineAmount;
    }

    @CreationTimestamp
    private Date createDate;//creation Time Stamp insert the time of first insertion..
    @UpdateTimestamp
    private Date updateDate; //this time is use when there is updation.. instead firstly creation...

    private  Integer fineAmount;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private  Book book;//here also I'll have to do the bidirectional mapping..

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private LibraryCard libraryCard; //I'll have to the bidirectional mapping...
}
