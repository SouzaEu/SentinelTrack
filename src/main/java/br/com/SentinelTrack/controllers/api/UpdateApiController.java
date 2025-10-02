package br.com.SentinelTrack.controllers.api;


import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.Update;
import br.com.SentinelTrack.models.User;
import br.com.SentinelTrack.models.dto.UpdateDTO;
import br.com.SentinelTrack.services.interfaces.UpdatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/updates")
public class UpdateApiController {

    @Autowired
    private UpdatesService updatesService;

    @GetMapping
    public List<UpdateDTO> findAll() {
        return UpdateDTO.fromEntityList(updatesService.findAll());
    }


    @GetMapping("/user")
    public List<UpdateDTO> findByUser(
            @RequestBody User user) {
        return UpdateDTO.fromEntityList(updatesService.findByUserId(user));
    }


    @GetMapping("/bike")
    public List<UpdateDTO> findByMotorcycle(
            @RequestBody Motorcycle motorcycle) {
        return UpdateDTO.fromEntityList(updatesService.findByMotorcycleId(motorcycle));
    }

    @GetMapping("/date")
    public List<UpdateDTO> findByDate(
            @RequestBody Date date) {
        return UpdateDTO.fromEntityList(updatesService.findByDate(date));
    }

    @PutMapping
    public UpdateDTO updateUpdate(
            @RequestBody UpdateDTO update) {
        return UpdateDTO.fromEntity(updatesService.update(update.toEntity()));
    }

    @PostMapping
    public UpdateDTO postUpdate(
            @RequestBody UpdateDTO update) {
        Update toMap = update.toEntity();
        Update response = updatesService.post(toMap);
        return UpdateDTO.fromEntity(response);
    }

    @DeleteMapping("/{id:[0-9a-fA-F\\-]{36}}")
    public void deleteUpdate(
            @PathVariable UUID id) {
        updatesService.deleteById(id);
    }
}
