package homeworks.dto;

import homeworks.model.PizzaOrder;

import java.util.List;

/**
 * DTO for {@link homeworks.model.PizzaBase}
 */
public record PizzaBaseResponseDto(Long id, String name, Integer baseCost) {
}