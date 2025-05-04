package homeworks.mapper;

import homeworks.dto.PizzaOrderResponseDto;
import homeworks.model.PizzaOrder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PizzaOrderResponseMapper {
    PizzaOrder toEntity(PizzaOrderResponseDto pizzaOrderResponseDto);

    PizzaOrderResponseDto toPizzaOrderResponseDto(PizzaOrder pizzaOrder);
}