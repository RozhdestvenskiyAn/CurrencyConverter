package ru.rozhdestvenskiy.currencyConverter.model;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Embeddable
public class Convert {
    @ManyToOne()
    @JoinColumn(name = "from_currency_num_code")
    private Currency fromCurrency;
    @Column(name = "amount_from")
    private BigDecimal amountFrom;

    @ManyToOne()
    @JoinColumn(name = "to_currency_num_code")
    private Currency toCurrency;
    @Column(name = "amount_to")
    private BigDecimal amountTo;

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrencyNumCode) {
        this.fromCurrency = fromCurrencyNumCode;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Currency toCurrencyNumCode) {
        this.toCurrency = toCurrencyNumCode;
    }

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom;
    }

    @Override
    public String toString() {
        return "Convert{" +
                "fromCurrency=" + fromCurrency +
                ", amountFrom=" + amountFrom +
                ", toCurrency=" + toCurrency +
                ", amountTo=" + amountTo +
                '}';
    }
}
