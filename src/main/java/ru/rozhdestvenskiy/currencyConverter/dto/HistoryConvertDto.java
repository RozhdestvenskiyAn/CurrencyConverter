package ru.rozhdestvenskiy.currencyConverter.dto;

import java.time.LocalDate;

public class HistoryConvertDto {

    private ConvertDto convert;
    private LocalDate date;

    public ConvertDto getConvert() {
        return convert;
    }

    public void setConvert(ConvertDto convert) {
        this.convert = convert;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
