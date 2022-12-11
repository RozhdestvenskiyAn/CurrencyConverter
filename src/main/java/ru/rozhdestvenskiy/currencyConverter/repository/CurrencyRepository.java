package ru.rozhdestvenskiy.currencyConverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rozhdestvenskiy.currencyConverter.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
