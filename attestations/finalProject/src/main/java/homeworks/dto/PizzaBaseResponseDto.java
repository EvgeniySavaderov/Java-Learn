package homeworks.dto;

/**
 * DTO for {@link homeworks.model.PizzaBase}
 */
public record PizzaBaseResponseDto(Long id, String name, Integer baseCost) {
}