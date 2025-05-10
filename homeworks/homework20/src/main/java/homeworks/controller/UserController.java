package homeworks.controller;

import homeworks.dto.MessageResponse;
import homeworks.dto.UserRequest;
import homeworks.dto.UserResponse;
import homeworks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("allusers") //вывод все пользователей
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("deleteallusers") //удаление всех пользователей
    public ResponseEntity<MessageResponse> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok(new MessageResponse("all users deleted"));
    }

    @PostMapping("adduser") //добавление либо изменение пользователя
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.addUser(userRequest));
    }

    @DeleteMapping("remove/{id}") //удаление пользователя
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable String id) {
        userService.deleteById(Long.valueOf(id));
    }

    @GetMapping("{id}") //вывод пользователя по id
    public ResponseEntity<UserResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(Long.valueOf(id)));
    }
}
