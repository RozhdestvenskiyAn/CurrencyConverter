package ru.rozhdestvenskiy.currencyConverter.dto;


import java.util.Objects;

public class CurrencyDto {
    private int numCode;
    private String charCode;
    private String name;

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyDto that = (CurrencyDto) o;
        return numCode == that.numCode && charCode.equals(that.charCode) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCode, charCode, name);
    }

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
