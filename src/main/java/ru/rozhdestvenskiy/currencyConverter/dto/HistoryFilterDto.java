package ru.rozhdestvenskiy.currencyConverter.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class HistoryFilterDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int fromCurrencyNumCode;
    private int toCurrencyNumCode;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getFromCurrencyNumCode() {
        return fromCurrencyNumCode;
    }

    public void setFromCurrencyNumCode(int fromCurrencyNumCode) {
        this.fromCurrencyNumCode = fromCurrencyNumCode;
    }

    public int getToCurrencyNumCode() {
        return toCurrencyNumCode;
    }

    public void setToCurrencyNumCode(int toCurrencyNumCode) {
        this.toCurrencyNumCode = toCurrencyNumCode;
    }
}
