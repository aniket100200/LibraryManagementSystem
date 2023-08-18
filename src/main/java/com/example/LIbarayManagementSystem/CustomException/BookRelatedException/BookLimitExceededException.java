package com.example.LIbarayManagementSystem.CustomException.BookRelatedException;

public class BookLimitExceededException extends Exception{
    public BookLimitExceededException(String message) {
        super(message);
    }
}
