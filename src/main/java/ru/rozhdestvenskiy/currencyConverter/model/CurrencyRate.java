package ru.rozhdestvenskiy.currencyConverter.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "currency_id", referencedColumnName = "num_code")
    private Currency currency;
    @Column(name = "value")
    private BigDecimal value;

    public CurrencyRate() {

    }
    public CurrencyRate(LocalDate date, Currency currency, BigDecimal value) {
        this.date = date;
        this.currency = currency;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "id=" + id +
                ", date=" + date +
                ", currency=" + currency +
                ", value=" + value +
                '}';
    }
}
