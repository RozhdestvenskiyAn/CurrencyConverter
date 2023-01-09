package ru.rozhdestvenskiy.currencyConverter.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozhdestvenskiy.currencyConverter.dto.ConvertDto;
import ru.rozhdestvenskiy.currencyConverter.dto.HistoryConvertDto;
import ru.rozhdestvenskiy.currencyConverter.dto.HistoryFilterDto;
import ru.rozhdestvenskiy.currencyConverter.exception.UserNotFoundException;
import ru.rozhdestvenskiy.currencyConverter.mapper.ConvertMapper;
import ru.rozhdestvenskiy.currencyConverter.mapper.HistoryConvertMapper;
import ru.rozhdestvenskiy.currencyConverter.model.Convert;
import ru.rozhdestvenskiy.currencyConverter.model.HistoryConvert;
import ru.rozhdestvenskiy.currencyConverter.model.User;
import ru.rozhdestvenskiy.currencyConverter.repository.HistoryConvertRepository;
import ru.rozhdestvenskiy.currencyConverter.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class HistoryConvertService {

    private static final Logger log = getLogger(HistoryConvertService.class);

    private final HistoryConvertRepository historyConvertRepository;
    private final UserRepository userRepository;

    private final ConvertMapper convertMapper;
    private final HistoryConvertMapper historyMapper;

    public HistoryConvertService(HistoryConvertRepository historyConvertRepository, UserRepository userRepository, ConvertMapper convertMapper, HistoryConvertMapper historyMapper) {
        this.historyConvertRepository = historyConvertRepository;
        this.userRepository = userRepository;
        this.convertMapper = convertMapper;
        this.historyMapper = historyMapper;
    }

    @Transactional
    public void save(ConvertDto convertDto, String name) {

        User user = userRepository.findByLogin(name).orElseThrow(UserNotFoundException::new);
        log.info("Got authentication  user {}", user.getLogin());

        log.info("Mapping ConvertDto to Convert");
        Convert convert = convertMapper.toConvert(convertDto);

        HistoryConvert historyConvert = new HistoryConvert();
        historyConvert.setUser(user);
        historyConvert.setDate(LocalDate.now());
        historyConvert.setConvert(convert);

        historyConvertRepository.save(historyConvert);
        log.info("Saving history is successful");

    }

    @Transactional
    public List<HistoryConvertDto> getHistory(String name) {

        return historyConvertRepository.findHistoryConvertsByUserLogin(name).stream()
                .map(historyMapper::toHistoryConvertDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<HistoryConvertDto> getHistory(String name, HistoryFilterDto historyFilterDto) {

        return historyConvertRepository.findHistory(name, historyFilterDto.getDate(),
                        historyFilterDto.getFromCurrencyNumCode(), historyFilterDto.getToCurrencyNumCode())
                .stream()
                .map(historyMapper::toHistoryConvertDto)
                .collect(Collectors.toList());
    }
}
