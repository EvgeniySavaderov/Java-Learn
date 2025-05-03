package homeworks.mapper;

import homeworks.dto.PizzaBaseRequestDto;
import homeworks.model.PizzaBase;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PizzaBaseRequestMapper {
    PizzaBase toEntity(PizzaBaseRequestDto pizzaBaseRequestDto);

    PizzaBaseRequestDto toPizzaBaseRequestDto(PizzaBase pizzaBase);
}