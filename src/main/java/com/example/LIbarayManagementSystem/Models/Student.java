package com.example.LIbarayManagementSystem.Models;

import com.example.LIbarayManagementSystem.Enums.Department;
import com.example.LIbarayManagementSystem.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rollNO;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private Department department;

    @Column(unique = true)
    private String emailId;


    //this is bidirectional Mapping...
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private  LibraryCard libraryCard;

}
