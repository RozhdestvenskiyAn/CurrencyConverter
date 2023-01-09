package ru.rozhdestvenskiy.currencyConverter.exception;

public class CurrencyRateNotFound extends RuntimeException{
    public CurrencyRateNotFound() {
        super("Currency rate not found");
    }
}
