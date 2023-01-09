package ru.rozhdestvenskiy.currencyConverter.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozhdestvenskiy.currencyConverter.exception.CurrencyNotFoundException;
import ru.rozhdestvenskiy.currencyConverter.model.Currency;
import ru.rozhdestvenskiy.currencyConverter.repository.CurrencyRepository;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;


    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Transactional
    public Currency findById(int numCode) {
        return currencyRepository.findById(numCode).orElseThrow(CurrencyNotFoundException::new);
    }

    public int getIdByCurrency(Currency currency) {
        return currency.getNumCode();
    }
}
