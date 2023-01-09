package ru.rozhdestvenskiy.currencyConverter.controller;

import org.slf4j.Logger;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.rozhdestvenskiy.currencyConverter.dto.ConvertDto;
import ru.rozhdestvenskiy.currencyConverter.dto.HistoryFilterDto;
import ru.rozhdestvenskiy.currencyConverter.service.CurrencyConverterService;
import ru.rozhdestvenskiy.currencyConverter.service.HistoryConvertService;

import java.time.LocalDate;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class CurrencyConverterController {

    private static final Logger log = getLogger(CurrencyConverterController.class);
    private final CurrencyConverterService currencyConverterService;
    private final HistoryConvertService historyConvertService;


    public CurrencyConverterController(CurrencyConverterService currencyConverterService, HistoryConvertService historyConvertService, DaoAuthenticationProvider authenticationProvider) {
        this.currencyConverterService = currencyConverterService;
        this.historyConvertService = historyConvertService;
    }

    @GetMapping("/")
    public String getIndexPage(@ModelAttribute("ConvertDto") ConvertDto convertDto, Model model) {

        log.info("Request for get index page");

        model.addAttribute("currencies", currencyConverterService.getCurrencies());

        return "/index";
    }

    @PostMapping("/")
    public String convert(@ModelAttribute("ConvertDto") ConvertDto convertDto, Authentication authentication, Model model) {

        log.info("Request for convert currency");
        model.addAttribute("currencies", currencyConverterService.getCurrencies());

        log.info("Checking current exchange rate on {}", LocalDate.now());
        currencyConverterService.checkCurrentExchangeRate();

        log.info("Converting currency");
        convertDto.setAmountTo(currencyConverterService.convert(convertDto));

        log.info("Saving history");
        historyConvertService.save(convertDto, authentication.getName());

        return "/index";
    }

    @GetMapping("/history")
    public String getHistory(@ModelAttribute ("filterHistoryDto") HistoryFilterDto historyFilterDto,
                             Authentication authentication, Model model) {

        log.info("Request for get history");

        model.addAttribute("currencies", currencyConverterService.getCurrencies());

        model.addAttribute("historyList", historyConvertService.getHistory(authentication.getName()));
        log.info("Got list of historyConvertDto");

        return "/history";
    }

    @GetMapping("/history/params")
    public String getFilterHistory(@ModelAttribute ("filterHistoryDto") HistoryFilterDto historyFilterDto,
                             Authentication authentication, Model model) {

        log.info("Request for get history");

        model.addAttribute("currencies", currencyConverterService.getCurrencies());

        model.addAttribute("historyList", historyConvertService.getHistory(authentication.getName(), historyFilterDto));
        log.info("Got list of historyConvertDto");

        return "/history";
    }
}
