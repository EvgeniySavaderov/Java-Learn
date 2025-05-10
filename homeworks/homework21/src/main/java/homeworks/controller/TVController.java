package homeworks.controller;

import homeworks.dto.TVRequest;
import homeworks.dto.TVResponse;
import homeworks.service.TVService;
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
    public ResponseEntity<List<TVResponse>> getTVs() {
        return ResponseEntity.ok(tvService.getTVs());
    }

    @PostMapping("addtv")
    public ResponseEntity<TVResponse> addTV(@RequestBody TVRequest tvRequest) {
        return ResponseEntity.ok(tvService.addTV(tvRequest));
    }

    @GetMapping("{id}") //вывод TV по id
    public ResponseEntity<TVResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(tvService.findById(Long.valueOf(id)));
    }

    @DeleteMapping("deletealltv") //удаление всех TV
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllTv() {
        tvService.deleteAllTVs();
    }

    @DeleteMapping("{id}/delete") //удаление TV
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeTV(@PathVariable String id) {
        tvService.deleteById(Long.valueOf(id));
    }

    @PostMapping("{id}/changestate")
    public ResponseEntity<TVResponse> changeState(@PathVariable String id) {
        return ResponseEntity.ok(tvService.changeState(Long.valueOf(id)));
    }

    @GetMapping("volume/{volume}")
    public ResponseEntity<List<TVResponse>> allowedVolumeTV(@PathVariable String volume) {
        return ResponseEntity.ok(tvService.getSilentTV(Integer.valueOf(volume)));
    }

    @PostMapping("{id}/nextchannel")
    public ResponseEntity<TVResponse> nextChannel(@PathVariable String id) {
        return ResponseEntity.ok(tvService.nextChannel(Long.valueOf(id)));
    }

    @PostMapping("{id}/prevchannel")
    public ResponseEntity<TVResponse> prevChannel(@PathVariable String id) {
        return ResponseEntity.ok(tvService.prevChannel(Long.valueOf(id)));
    }

    @PostMapping("{id}/channel/{channel}")
    public ResponseEntity<TVResponse> goToChannel(@PathVariable String id, @PathVariable String channel) {
        return ResponseEntity.ok(tvService.goToChannel(Long.valueOf(id), Integer.valueOf(channel)));
    }
}
