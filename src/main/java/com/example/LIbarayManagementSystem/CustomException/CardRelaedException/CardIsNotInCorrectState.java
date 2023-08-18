package com.example.LIbarayManagementSystem.CustomException.CardRelaedException;

public class CardIsNotInCorrectState extends Exception{
    public CardIsNotInCorrectState(String message) {
        super(message);
    }
}
