package homeworks.controller;

import homeworks.dto.PersonResponseDto;
import homeworks.dto.PizzaOrderRequestDto;
import homeworks.service.PersonAddOrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/PizzaService")
public class PersonAddOrderController {
    private final PersonAddOrderService personAddOrderService;

    @PostMapping("person/{id}/addorder")
    @Operation(summary = "добавление заказа пользователю", description = "добавление (или изменение существующего) заказа пользователю")
    public ResponseEntity<PersonResponseDto> addOrderForPerson(@PathVariable String id, @RequestBody PizzaOrderRequestDto pizzaorderRequestDto) {
        return ResponseEntity.ok(personAddOrderService.addOrderForPersonById(Long.valueOf(id), pizzaorderRequestDto));
    }

    @PostMapping("person/fio/{fio}/addorder")
    @Operation(summary = "добавление заказа пользователю по имени", description = "добавление (или изменение существующего) заказа пользователю по имени")
    public ResponseEntity<PersonResponseDto> addOrderForPersonByName(@PathVariable String fio, @RequestBody PizzaOrderRequestDto pizzaorderRequestDto) {
        return ResponseEntity.ok(personAddOrderService.addOrderForPersonByFio(fio, pizzaorderRequestDto));
    }
}
