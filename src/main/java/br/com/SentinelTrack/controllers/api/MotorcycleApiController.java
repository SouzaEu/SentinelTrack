package br.com.SentinelTrack.controllers.api;


import br.com.SentinelTrack.models.dto.MotorcycleDTO;
import br.com.SentinelTrack.models.Motorcycle;

import java.util.List;
import java.util.UUID;

import br.com.SentinelTrack.services.interfaces.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motorcycles")
public class MotorcycleApiController {

    @Autowired
    private MotorcycleService motorcycleService;

    @GetMapping
    public Page<MotorcycleDTO> getAllMotorcycles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "plate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return motorcycleService.findAllPageable(pageable)
                .map(MotorcycleDTO::fromEntity);
    }

    @GetMapping("/plate-error")
    public List<MotorcycleDTO> findAllPlateErrors() {
        return MotorcycleDTO.fromEntityList(motorcycleService.findAllReadPlatesFalse());
    }

    @GetMapping("/id/{id:[0-9a-fA-F\\-]{36}}")
    public MotorcycleDTO findById(@PathVariable UUID id) {
        return MotorcycleDTO.fromEntity(motorcycleService.findById(id));
    }

    @GetMapping("/{plate}")
    public MotorcycleDTO findByPlate(@PathVariable String plate) {
        return MotorcycleDTO.fromEntity(motorcycleService.findByPlate(plate));
    }

    @PutMapping
    public MotorcycleDTO updateMotorclycle(@RequestBody MotorcycleDTO dto) {
        return MotorcycleDTO.fromEntity(motorcycleService.updateMotorcycle(dto.toEntity()));
    }

    @PostMapping
    public MotorcycleDTO postMotorcycle(@RequestBody MotorcycleDTO dto) {
        Motorcycle toMap = dto.toEntity();
        Motorcycle response = motorcycleService.postMotorcycle(toMap);
        return MotorcycleDTO.fromEntity(response);
    }

    @DeleteMapping("/{id:[0-9a-fA-F\\-]{36}}")
    public void deleteMotocycleById(@PathVariable UUID id) {
        motorcycleService.deleteMotorcycleById(id);
    }

    @DeleteMapping("/{plate}")
    public void deleteMotocycleByPlate(@PathVariable String plate) {
        motorcycleService.deleteMotorcycleByPlate(plate);
    }
}