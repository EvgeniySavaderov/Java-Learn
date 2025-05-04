package homeworks.dto;

import homeworks.model.PizzaBase;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO for {@link homeworks.model.PizzaOrder}
 */
public record PizzaOrderResponseDto(Long id, Integer cost, List<String> size, List<Integer> amount, @NotNull List<PizzaBase> pizzaBaseList) {
}