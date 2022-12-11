package ru.rozhdestvenskiy.currencyConverter.exception;

public class CurrencyNotFoundException extends RuntimeException{
    public CurrencyNotFoundException() {
        super("Currency not found");
    }
}
