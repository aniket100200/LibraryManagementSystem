package com.example.LIbarayManagementSystem.CustomException.BookRelatedException;

public class BookIsNotAvailableException extends Exception{
    public BookIsNotAvailableException(String message) {
        super(message);
    }
}
