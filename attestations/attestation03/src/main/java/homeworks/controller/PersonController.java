package homeworks.controller;

import homeworks.dto.PersonRequestDto;
import homeworks.dto.PersonResponseDto;
import homeworks.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/PizzaService")
public class PersonController {
    private final PersonService personService;

    @GetMapping("person/all")
    @Operation(summary = "список всех пользователей", description = "вывод списка всех пользователей")
    public ResponseEntity<List<PersonResponseDto>> getPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("person/{id}")
    @Operation(summary = "вывод пользователя", description = "вывод пользователя по id")
    public ResponseEntity<PersonResponseDto> getPersonById(@PathVariable String id) {
        return ResponseEntity.ok(personService.findPersonById(Long.valueOf(id)));
    }

    @GetMapping("person/fio/{fio}")
    @Operation(summary = "вывод пользователя по имени", description = "вывод пользователя по имени")
    public ResponseEntity<PersonResponseDto> getPersonByFio(@PathVariable String fio) {
        return ResponseEntity.ok(personService.findPersonByFio(fio));
    }

    @PostMapping("person/add")
    @Operation(summary = "добавление польователя", description = "добавление (или изменение существующего) пользователя")
    public ResponseEntity<PersonResponseDto> addPerson(@RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(personService.addPerson(personRequestDto));
    }

    @DeleteMapping("person/{id}/delete")
    @Operation(summary = "удаление пользователя", description = "удаление пользователя по id")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removePerson(@PathVariable String id) {
        personService.deletePersonById(Long.valueOf(id));
    }
}
