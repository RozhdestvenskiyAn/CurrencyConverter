package ru.rozhdestvenskiy.currencyConverter.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table
public class Currency {
    @Id
    @Column(name = "num_code")
    private int numCode;
    @Column(name = "char_code")
    private String charCode;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "currency")
    private List<CurrencyRate> currencyRates;

    public Currency() {
    }

    public Currency(int munCode, String charCode, String name) {
        this.numCode = munCode;
        this.charCode = charCode;
        this.name = name;
    }

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int munCode) {
        this.numCode = munCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "numCode=" + numCode +
                ", charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
