package ru.rozhdestvenskiy.currencyConverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyConverterController {

    @GetMapping
    public String index(){
        return "/index";
    }
}
