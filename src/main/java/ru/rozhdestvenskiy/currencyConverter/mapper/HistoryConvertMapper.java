package ru.rozhdestvenskiy.currencyConverter.mapper;

import org.mapstruct.Mapper;
import ru.rozhdestvenskiy.currencyConverter.dto.HistoryConvertDto;
import ru.rozhdestvenskiy.currencyConverter.model.HistoryConvert;

@Mapper(componentModel = "spring", uses = {ConvertMapper.class})
public interface HistoryConvertMapper {
    HistoryConvertDto toHistoryConvertDto (HistoryConvert historyConvert);
}
