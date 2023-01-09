package ru.rozhdestvenskiy.currencyConverter.mapper;

import org.mapstruct.Mapper;
import ru.rozhdestvenskiy.currencyConverter.dto.ConvertDto;
import ru.rozhdestvenskiy.currencyConverter.model.Convert;

@Mapper(componentModel = "spring", uses = CurrencyMapper.class)
public interface ConvertMapper {
    Convert toConvert(ConvertDto convertDto);
    ConvertDto toConvertDto(Convert convert);

}