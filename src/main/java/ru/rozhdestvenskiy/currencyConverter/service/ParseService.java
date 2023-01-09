package ru.rozhdestvenskiy.currencyConverter.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.rozhdestvenskiy.currencyConverter.exception.CurrencyNotFoundException;
import ru.rozhdestvenskiy.currencyConverter.model.Currency;
import ru.rozhdestvenskiy.currencyConverter.model.CurrencyRate;
import ru.rozhdestvenskiy.currencyConverter.repository.CurrencyRateRepository;
import ru.rozhdestvenskiy.currencyConverter.repository.CurrencyRepository;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;
import static java.time.LocalDate.*;
import static java.time.format.DateTimeFormatter.*;
import static org.slf4j.LoggerFactory.getLogger;
import static org.w3c.dom.Node.*;

@Service
public class ParseService {

    private static final Logger log = getLogger(ParseService.class);
    private static final String URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;

    public ParseService(CurrencyRepository currencyRepository, CurrencyRateRepository currencyRateRepository) {
        this.currencyRepository = currencyRepository;
        this.currencyRateRepository = currencyRateRepository;
    }

    @Transactional
    public List<Currency> parseCurrency() {

        log.info("Getting an xml file with a list of currencies");
        List<Currency> currencies = new ArrayList<>();

        try {
            Document doc = getXML();

            NodeList nodeList = doc.getElementsByTagName("Valute");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == ELEMENT_NODE) {

                    Element elem = (Element) node;
                    Currency currency = createCurrency(elem);

                    log.info("Created currency: {}", currency);

                    currencies.add(currency);
                    log.info("{} is added to list", currency.getCharCode());
                }
            }
            currencies.add(new Currency(810, "RUB", "Росийский рубль"));
            currencyRepository.saveAll(currencies);
            log.info("Saving the list of currency");
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return currencies;
    }

    @Transactional
    public void parseCurrencyRate() {

        log.info("Getting an xml file with a list of exchange rates for the current date");

        List<CurrencyRate> currencyRates = new ArrayList<>();

        try {
            Document doc = getXML();

            LocalDate date = parse(doc.getDocumentElement().getAttribute("Date"), ofPattern("dd.MM.yyyy"));
            NodeList nodeList = doc.getElementsByTagName("Valute");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == ELEMENT_NODE) {

                    Element elem = (Element) node;

                    CurrencyRate currencyRate = createCurrencyRate(elem, date);
                    log.info("The {} exchange rate was created on {}", currencyRate.getCurrency().getCharCode(), currencyRate.getDate());

                    currencyRates.add(currencyRate);
                    log.info("The {} exchange rate was added to list", currencyRate.getCurrency().getCharCode());
                }
            }

            Currency currencyRU = currencyRepository.findById(810).orElseThrow(CurrencyNotFoundException::new);
            currencyRates.add(new CurrencyRate(LocalDate.now(), currencyRU, BigDecimal.ONE));

            currencyRateRepository.saveAll(currencyRates);
            log.info("Saving the list of exchange rate on {}", date);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    private static Document getXML() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(URL);
        doc.getDocumentElement().normalize();
        return doc;
    }

    private Currency createCurrency(Element elem) {
        return new Currency(
                Integer.parseInt(elem.getElementsByTagName("NumCode").item(0).getTextContent()),
                elem.getElementsByTagName("CharCode").item(0).getTextContent(),
                elem.getElementsByTagName("Name").item(0).getTextContent());
    }
    private CurrencyRate createCurrencyRate(Element elem, LocalDate date) {

        int numCode = Integer.parseInt(elem.getElementsByTagName("NumCode").item(0).getTextContent());
        Currency currency = currencyRepository.findById(numCode).orElseThrow(CurrencyNotFoundException::new);

        BigDecimal value =  new BigDecimal(elem.getElementsByTagName("Value")
                .item(0).getTextContent()
                .replace(",", "."));

        int nominal = Integer.parseInt(elem.getElementsByTagName("Nominal").item(0).getTextContent());
        value = value.divide(new BigDecimal(nominal),4, HALF_UP );

        return new CurrencyRate(date, currency, value);
    }
}

