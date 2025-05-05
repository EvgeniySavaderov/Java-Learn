package homeworks.controller;

import homeworks.dto.PizzaBaseRequestDto;
import homeworks.dto.PizzaBaseResponseDto;
import homeworks.service.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/PizzaService")
public class PizzaController {
    private final PizzaService pizzaService;

    @GetMapping("pizza/all")
    @Operation(summary = "список всех пицц", description = "вывод списка всех пицц")
    public ResponseEntity<List<PizzaBaseResponseDto>> getPizzas() {
        return ResponseEntity.ok(pizzaService.getAllPizzaBases());
    }

    @GetMapping("pizza/{id}")
    @Operation(summary = "вывод пиццы", description = "вывод пиццы по id")
    public ResponseEntity<PizzaBaseResponseDto> getPizzaById(@PathVariable String id) {
        return ResponseEntity.ok(pizzaService.findPizzaBaseById(Long.valueOf(id)));
    }

    @PostMapping("pizza/add")
    @Operation(summary = "добавление пиццы", description = "добавление (или изменение существующего) типа пиццы")
    public ResponseEntity<PizzaBaseResponseDto> addPizza(@RequestBody PizzaBaseRequestDto pizzaBaseRequestDto) {
        return ResponseEntity.ok(pizzaService.addPizzaBase(pizzaBaseRequestDto));
    }
}
