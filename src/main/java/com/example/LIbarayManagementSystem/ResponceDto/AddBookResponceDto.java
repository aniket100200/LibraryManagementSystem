package com.example.LIbarayManagementSystem.ResponceDto;

import com.example.LIbarayManagementSystem.Enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddBookResponceDto
{
    private String title;
    private boolean isAvailable;
    private Genre genre;
    private Date dataOfPublication;
    private Integer authorId;
    private Integer price;
}
