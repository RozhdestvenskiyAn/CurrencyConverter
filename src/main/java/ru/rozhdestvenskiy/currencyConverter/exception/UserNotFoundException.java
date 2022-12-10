package ru.rozhdestvenskiy.currencyConverter.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}
