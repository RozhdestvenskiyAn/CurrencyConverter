package ru.rozhdestvenskiy.currencyConverter.dto;

import java.math.BigDecimal;

public class ConvertDto {
    private CurrencyDto fromCurrency;
    private BigDecimal amountFrom;
    private CurrencyDto toCurrency;
    private BigDecimal amountTo;

    public CurrencyDto getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(CurrencyDto fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public CurrencyDto getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(CurrencyDto toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom;
    }

    @Override
    public String toString() {
        return "ConvertDto{" +
                "fromCurrency=" + fromCurrency +
                ", amountFrom=" + amountFrom +
                ", toCurrency=" + toCurrency +
                ", amountTo=" + amountTo +
                '}';
    }
}
