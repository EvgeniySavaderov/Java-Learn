package homeworks.controller;

import homeworks.dto.TVRequest;
import homeworks.dto.TVResponse;
import homeworks.service.TVService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tv")
public class TVController {
    private final TVService tvService;

    @GetMapping("alltv")
    @Operation(summary = "список всех телевизоров", description = "вывод списка всех телевизоров")
    public ResponseEntity<List<TVResponse>> getTVs() {
        return ResponseEntity.ok(tvService.getTVs());
    }

    @PostMapping("addtv")
    @Operation(summary = "добавление телевизора", description = "добавление (или изменение существующего)телевизора. Можно указать часть параметров")
    public ResponseEntity<TVResponse> addTV(@RequestBody TVRequest tvRequest) {
        return ResponseEntity.ok(tvService.addTV(tvRequest));
    }

    @GetMapping("{id}")
    @Operation(summary = "вывод телевизора", description = "вывод телевизора по id")//вывод TV по id
    public ResponseEntity<TVResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(tvService.findById(Long.valueOf(id)));
    }

    @DeleteMapping("deletealltv")
    @Operation(summary = "Удаление всех телевизоров")//удаление всех TV
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllTv() {
        tvService.deleteAllTVs();
    }

    @DeleteMapping("{id}/delete")
    @Operation(summary = "удаление телевизора", description = "удаление телевизора по id")//удаление TV
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeTV(@PathVariable String id) {
        tvService.deleteById(Long.valueOf(id));
    }

    @PostMapping("{id}/changestate")
    @Operation(summary = "включение/выключение телевизора", description = "изменение состояния работы телевизора по id")
    public ResponseEntity<TVResponse> changeState(@PathVariable String id) {
        return ResponseEntity.ok(tvService.changeState(Long.valueOf(id)));
    }

    @GetMapping("volume/{volume}")
    @Operation(summary = "тихие телевизоры", description = "Вывод списка всех включенных телевизоров по  возрастанию громкости до указанной максимальной")
    public ResponseEntity<List<TVResponse>> allowedVolumeTV(@PathVariable String volume) {
        return ResponseEntity.ok(tvService.getSilentTV(Integer.valueOf(volume)));
    }

    @PostMapping("{id}/nextchannel")
    @Operation(summary = "следующий канал", description = "переключение на следуюший канал(0-99) телевизора по id, если включен")
    public ResponseEntity<TVResponse> nextChannel(@PathVariable String id) {
        return ResponseEntity.ok(tvService.nextChannel(Long.valueOf(id)));
    }

    @PostMapping("{id}/prevchannel")
    @Operation(summary = "предыдущий канал", description = "переключение на предыдущий канал(0-99) телевизора по id, если включен")
    public ResponseEntity<TVResponse> prevChannel(@PathVariable String id) {
        return ResponseEntity.ok(tvService.prevChannel(Long.valueOf(id)));
    }

    @PostMapping("{id}/channel/{channel}")
    @Operation(summary = "переход на канал", description = "переключение на канал(0-99) телевизора по id, если включен")
    public ResponseEntity<TVResponse> goToChannel(@PathVariable String id, @PathVariable String channel) {
        return ResponseEntity.ok(tvService.goToChannel(Long.valueOf(id), Integer.valueOf(channel)));
    }
}
