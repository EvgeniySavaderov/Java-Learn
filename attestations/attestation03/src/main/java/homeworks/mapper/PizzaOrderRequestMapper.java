package homeworks.mapper;

import homeworks.dto.PizzaOrderRequestDto;
import homeworks.model.PizzaOrder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PizzaOrderRequestMapper {
    PizzaOrder toEntity(PizzaOrderRequestDto pizzaOrderRequestDto);

    PizzaOrderRequestDto toPizzaOrderRequestDto(PizzaOrder pizzaOrder);
}