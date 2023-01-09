package ru.rozhdestvenskiy.currencyConverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rozhdestvenskiy.currencyConverter.model.HistoryConvert;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoryConvertRepository extends JpaRepository<HistoryConvert, Long> {

    @Query("""
            from HistoryConvert 
            where user.login = :login and date = :date 
            and convert.fromCurrency.numCode = :fromNumCode 
            and convert.toCurrency.numCode = :toNumCode""")
    List<HistoryConvert> findHistory(@Param("login") String name, @Param("date") LocalDate date,
                                     @Param("fromNumCode") int fromCurrencyNumCode, @Param("toNumCode") int toCurrencyNumCode);
    List<HistoryConvert> findHistoryConvertsByUserLogin(String userName);
}
