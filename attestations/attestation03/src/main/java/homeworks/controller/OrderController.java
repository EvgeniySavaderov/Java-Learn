package homeworks.controller;

import homeworks.dto.PizzaOrderResponseDto;
import homeworks.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/PizzaService")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("order/all")
    @Operation(summary = "список всех заказов", description = "вывод списка всех заказов")
    public ResponseEntity<List<PizzaOrderResponseDto>> getOrders() {
        return ResponseEntity.ok(orderService.getAllPizzaOrders());
    }

    @GetMapping("order/{id}")
    @Operation(summary = "вывод заказов", description = "вывод заказов по id")
    public ResponseEntity<PizzaOrderResponseDto> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.findPizzaOrderById(Long.valueOf(id)));
    }

    @DeleteMapping("order/{id}/delete")
    @Operation(summary = "удаление заказа", description = "удаление заказа по id")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeOrder(@PathVariable String id) {
        orderService.deletePizzaOrderById(Long.valueOf(id));
    }
}
