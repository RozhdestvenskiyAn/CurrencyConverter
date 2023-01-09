package ru.rozhdestvenskiy.currencyConverter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rozhdestvenskiy.currencyConverter.dto.CurrencyDto;
import ru.rozhdestvenskiy.currencyConverter.mapper.CurrencyMapper;
import ru.rozhdestvenskiy.currencyConverter.model.Currency;
import ru.rozhdestvenskiy.currencyConverter.repository.CurrencyRepository;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyConvertServiceTest {

    @InjectMocks
    private CurrencyConverterService currencyConverterService;


    @Mock
    private CurrencyRepository currencyRepository;
    @Mock
    private CurrencyMapper currencyMapper;
    @Mock
    private ParseService parseService;

    @Test
    void should_get_list_currencies_test() {
        //given
        Currency USDCurrency = new Currency(840, "USD", "Доллар США");
        Currency EURCurrency = new Currency(978, "EUR", "Евро");
        List<Currency> currencies = List.of(USDCurrency, EURCurrency);

        CurrencyDto USDCurrencyDto = new CurrencyDto();
        USDCurrencyDto.setNumCode(USDCurrency.getNumCode());
        USDCurrencyDto.setCharCode(USDCurrency.getCharCode());
        USDCurrencyDto.setName(USDCurrency.getName());

        CurrencyDto EURCurrencyDto = new CurrencyDto();
        EURCurrencyDto.setNumCode(EURCurrency.getNumCode());
        EURCurrencyDto.setCharCode(EURCurrency.getCharCode());
        EURCurrencyDto.setName(EURCurrency.getName());

        //when
        when(currencyRepository.findAll()).thenReturn(currencies);
        when(currencyMapper.toCurrencyDto(USDCurrency)).thenReturn(USDCurrencyDto);
        when(currencyMapper.toCurrencyDto(EURCurrency)).thenReturn(EURCurrencyDto);
        List<CurrencyDto> currenciesRes = currencyConverterService.getCurrencies();

        //then
        Assertions.assertNotNull(currenciesRes);
        Assertions.assertEquals(2, currenciesRes.size());
        Assertions.assertEquals(USDCurrencyDto, currenciesRes.get(0));
        Assertions.assertEquals(EURCurrencyDto, currenciesRes.get(1));


    }

    @Test
    void should_call_parser_currencies_method() {
        //given
        List<Currency> currencies = Collections.emptyList();

        //when
        when(currencyRepository.findAll()).thenReturn(currencies);
        currencyConverterService.getCurrencies();

        //then
        verify(parseService, times(1)).parseCurrency();
    }
}
