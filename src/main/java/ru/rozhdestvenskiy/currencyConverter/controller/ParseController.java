package ru.rozhdestvenskiy.currencyConverter.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rozhdestvenskiy.currencyConverter.service.ParseService;

import static org.slf4j.LoggerFactory.*;

@Controller
@RequestMapping("/parse")
public class ParseController {

    private static final Logger log = getLogger(ParseController.class);

    private final ParseService parseService;

    public ParseController(ParseService parseService) {
        this.parseService = parseService;
    }

    @GetMapping("/currency")
    public String parseCurrency(){

        log.info("Request for currency rate parsing");
        parseService.parseCurrency();

        return "redirect:/";
    }
    @GetMapping("/currencyRate")
    public String parseCurrencyRate(){

        log.info("Request for currency parsing");
        parseService.parseCurrencyRate();

        return "redirect:/";
    }
}
