package ru.rozhdestvenskiy.currencyConverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rozhdestvenskiy.currencyConverter.model.Currency;
import ru.rozhdestvenskiy.currencyConverter.model.CurrencyRate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    Optional<CurrencyRate> findFirstByDate(LocalDate currentDate);
    Optional<CurrencyRate> findByCurrencyAndDate(Currency currency, LocalDate date);

}
