package homeworks.mapper;

import homeworks.dto.PizzaBaseResponseDto;
import homeworks.model.PizzaBase;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PizzaBaseResponseMapper {
    PizzaBase toEntity(PizzaBaseResponseDto pizzaBaseResponseDto);

    PizzaBaseResponseDto toPizzaBaseResponseDto(PizzaBase pizzaBase);
}