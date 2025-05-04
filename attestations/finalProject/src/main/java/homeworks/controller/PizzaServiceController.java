package homeworks.controller;

import homeworks.dto.*;
import homeworks.service.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/PizzaService")
public class PizzaServiceController {
    private final PizzaService pizzaService;

    @GetMapping("person/all")
    @Operation(summary = "список всех пользователей", description = "вывод списка всех пользователей")
    public ResponseEntity<List<PersonResponseDto>> getPersons() {
        return ResponseEntity.ok(pizzaService.getAllPersons());
    }

    @GetMapping("person/{id}")
    @Operation(summary = "вывод пользователя", description = "вывод пользователя по id")
    public ResponseEntity<PersonResponseDto> getPersonById(@PathVariable String id) {
        return ResponseEntity.ok(pizzaService.findPersonById(Long.valueOf(id)));
    }

    @PostMapping("person/add")
    @Operation(summary = "добавление польователя", description = "добавление (или изменение существующего) пользователя")
    public ResponseEntity<PersonResponseDto> addPerson(@RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(pizzaService.addPerson(personRequestDto));
    }

    @DeleteMapping("person/{id}/delete")
    @Operation(summary = "удаление пользователя", description = "удаление пользователя по id")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removePerson(@PathVariable String id) {
        pizzaService.deletePersonById(Long.valueOf(id));
    }

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

    @GetMapping("order/all")
    @Operation(summary = "список всех заказов", description = "вывод списка всех заказов")
    public ResponseEntity<List<PizzaOrderResponseDto>> getOrders() {
        return ResponseEntity.ok(pizzaService.getAllPizzaOrders());
    }

    @GetMapping("order/{id}")
    @Operation(summary = "вывод заказов", description = "вывод заказов по id")
    public ResponseEntity<PizzaOrderResponseDto> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(pizzaService.findPizzaOrderById(Long.valueOf(id)));
    }

    @DeleteMapping("order/{id}/delete")
    @Operation(summary = "удаление заказа", description = "удаление заказа по id")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeOrder(@PathVariable String id) {
        pizzaService.deletePizzaOrderById(Long.valueOf(id));
    }

    @GetMapping("person/fio/{fio}")
    @Operation(summary = "вывод пользователя по имени", description = "вывод пользователя по имени")
    public ResponseEntity<PersonResponseDto> getPersonByFio(@PathVariable String fio) {
        return ResponseEntity.ok(pizzaService.findPersonByFio(fio));
    }

    @PostMapping("person/{id}/addorder")
    @Operation(summary = "добавление заказа пользователю", description = "добавление (или изменение существующего) заказа пользователю")
    public ResponseEntity<PersonResponseDto> addOrderForPerson(@PathVariable String id, @RequestBody PizzaOrderRequestDto pizzaorderRequestDto) {
        return ResponseEntity.ok(pizzaService.addOrderForPersonById(Long.valueOf(id), pizzaorderRequestDto));
    }

    @PostMapping("person/fio/{fio}/addorder")
    @Operation(summary = "добавление заказа пользователю по имени", description = "добавление (или изменение существующего) заказа пользователю по имени")
    public ResponseEntity<PersonResponseDto> addOrderForPersonByName(@PathVariable String fio, @RequestBody PizzaOrderRequestDto pizzaorderRequestDto) {
        return ResponseEntity.ok(pizzaService.addOrderForPersonByFio(fio, pizzaorderRequestDto));
    }
}
