package homeworks.dto;

/**
 * DTO for {@link homeworks.model.PizzaBase}
 */
public record PizzaBaseRequestDto(Long id, String name, Integer baseCost) {
}