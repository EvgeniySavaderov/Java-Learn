package homeworks.dto;

import homeworks.model.PizzaOrder;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO for {@link homeworks.model.Person}
 */
public record PersonRequestDto(Long id, String fio, @NotNull List<PizzaOrder> pizzaOrderList) {
}