package ru.rozhdestvenskiy.currencyConverter.mapper;

import org.mapstruct.Mapper;
import ru.rozhdestvenskiy.currencyConverter.dto.CurrencyDto;
import ru.rozhdestvenskiy.currencyConverter.model.Currency;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    CurrencyDto toCurrencyDto(Currency currency);
    Currency toCurrency(CurrencyDto currency);
}
