package homeworks.dto;

import homeworks.model.PizzaBase;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO for {@link homeworks.model.PizzaOrder}
 */
public record PizzaOrderRequestDto(Long id, List<String> size, List<Integer> amount, @NotNull List<PizzaBase> pizzaBaseList) {
}