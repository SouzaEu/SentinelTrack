package br.com.SentinelTrack.services.interfaces;

import br.com.SentinelTrack.models.Motorcycle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface MotorcycleService {

    List<Motorcycle> findAll();

    Page<Motorcycle> findAllPageable(Pageable pageable);

    Motorcycle findById(UUID id);

    Motorcycle findByPlate(String plate);

    List<Motorcycle> findAllReadPlatesFalse();

    Motorcycle postMotorcycle(Motorcycle motorcycle);

    Motorcycle updateMotorcycle(Motorcycle motorcycle);

    void deleteMotorcycleById(UUID id);

    void deleteMotorcycleByPlate(String plate);
}
