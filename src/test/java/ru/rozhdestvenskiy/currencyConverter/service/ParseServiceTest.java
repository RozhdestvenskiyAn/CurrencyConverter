package ru.rozhdestvenskiy.currencyConverter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.w3c.dom.Document;
import ru.rozhdestvenskiy.currencyConverter.model.Currency;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParseServiceTest {

    @InjectMocks
    private ParseService parseService;

    @Test
    void should_get_list_parser_currencies_test() {
        //given
        Document doc = new

        //when
        when(doc.method1()).thenReturn(10L);
        List<Currency> currencies = parseService.parseCurrency();

        //then

    }
}

