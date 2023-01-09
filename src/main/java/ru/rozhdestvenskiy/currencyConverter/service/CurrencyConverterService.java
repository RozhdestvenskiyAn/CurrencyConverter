package ru.rozhdestvenskiy.currencyConverter.service;

import org.slf4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozhdestvenskiy.currencyConverter.dto.ConvertDto;
import ru.rozhdestvenskiy.currencyConverter.dto.CurrencyDto;
import ru.rozhdestvenskiy.currencyConverter.exception.CurrencyRateNotFound;
import ru.rozhdestvenskiy.currencyConverter.mapper.ConvertMapper;
import ru.rozhdestvenskiy.currencyConverter.mapper.CurrencyMapper;
import ru.rozhdestvenskiy.currencyConverter.model.Convert;
import ru.rozhdestvenskiy.currencyConverter.model.Currency;
import ru.rozhdestvenskiy.currencyConverter.model.CurrencyRate;
import ru.rozhdestvenskiy.currencyConverter.repository.CurrencyRateRepository;
import ru.rozhdestvenskiy.currencyConverter.repository.CurrencyRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.math.RoundingMode.HALF_UP;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CurrencyConverterService {

    private static final Logger log = getLogger(CurrencyConverterService.class);

    private final ParseService parseService;

    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;

    private final CurrencyMapper currencyMapper;
    private final ConvertMapper convertMapper;

    public CurrencyConverterService(ParseService parseService, CurrencyRepository currencyRepository, CurrencyRateRepository currencyRateRepository, CurrencyMapper currencyMapper, ConvertMapper convertMapper) {
        this.parseService = parseService;
        this.currencyRepository = currencyRepository;
        this.currencyRateRepository = currencyRateRepository;
        this.currencyMapper = currencyMapper;
        this.convertMapper = convertMapper;
    }

    @Transactional
    public List<CurrencyDto> getCurrencies() {

        log.info("Getting currencies");
        List<Currency> currencies = currencyRepository.findAll();

        if (currencies.isEmpty()) {
            log.info("Parsing currencies");
            currencies = parseService.parseCurrency();
        }
        return currencies.stream()
                .map(currencyMapper::toCurrencyDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BigDecimal convert(ConvertDto convertDto) {

        log.info("Mapping convertDto to convert");
        Convert convert = convertMapper.toConvert(convertDto);

        BigDecimal valueCurrencyRateFrom = getValueCurrencyRate(convert.getFromCurrency());
        BigDecimal valueCurrencyRateTo = getValueCurrencyRate(convert.getToCurrency());

        BigDecimal result = valueCurrencyRateFrom.multiply(convert.getAmountFrom()).divide(valueCurrencyRateTo, 2, HALF_UP);
        log.info("Got result {}", result);

        return result;
    }

    private BigDecimal getValueCurrencyRate(Currency currency) {
        CurrencyRate currencyRateFrom = currencyRateRepository.findByCurrencyAndDate(currency, LocalDate.now()).orElseThrow(CurrencyRateNotFound::new);
        return currencyRateFrom.getValue();
    }

    @Transactional
    public void checkCurrentExchangeRate() {

        LocalDate currentDate = LocalDate.now();
        Optional<CurrencyRate> currencyRate = currencyRateRepository.findFirstByDate(currentDate);

        if (currencyRate.isEmpty()) {
            log.info("Parsing currency rate");
            parseService.parseCurrencyRate();
        } else {
            log.info("Check is successful");
        }

    }
}
