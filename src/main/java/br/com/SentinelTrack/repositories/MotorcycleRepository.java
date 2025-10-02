package br.com.SentinelTrack.repositories;

import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.enums.BikeType;
import br.com.SentinelTrack.models.enums.Sectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, UUID> {

    List<Motorcycle> findAll();


    List<Motorcycle> findAllBySector(Sectors sector);

    List<Motorcycle> findAllByType(BikeType type);

    List<Motorcycle> findAllByPlateReadableIsFalse();

    Motorcycle findByPlate(String plate);

    boolean existsByPlate(String plate);

    void deleteByPlate(String plate);

}
