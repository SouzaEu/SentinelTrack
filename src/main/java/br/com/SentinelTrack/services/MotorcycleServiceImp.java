package br.com.SentinelTrack.services;

import br.com.SentinelTrack.exceptions.ExistingPlateErrorException;
import br.com.SentinelTrack.exceptions.MotorcycleNotFoundException;
import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.repositories.MotorcycleRepository;
import br.com.SentinelTrack.services.interfaces.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class MotorcycleServiceImp implements MotorcycleService {

    @Autowired
    private MotorcycleRepository bikeRepository;

    public List<Motorcycle> findAll() {
        return bikeRepository.findAll();
    }

    public Page<Motorcycle> findAllPageable(Pageable pageable) {return bikeRepository.findAll(pageable);}

    @Override
    public Motorcycle findById(UUID id) {
        return bikeRepository.findById(id).orElseThrow(()-> new MotorcycleNotFoundException("Could not find motorcycle with id " + id));
    }

    @Override
    public Motorcycle findByPlate(String plate) {
        return bikeRepository.findByPlate(plate);
    }

    @Override
    public List<Motorcycle> findAllReadPlatesFalse() {
        return bikeRepository.findAllByPlateReadableIsFalse();
    }

    @Override
    public Motorcycle postMotorcycle(Motorcycle motorcycle) {
        if (bikeRepository.existsByPlate(motorcycle.getPlate())) throw new ExistingPlateErrorException("Motorcyle with this plate: " + motorcycle.getPlate() + " already exists.");
        return bikeRepository.save(motorcycle);
    }

    @Override
    public Motorcycle updateMotorcycle(Motorcycle motorcycle) {
        if (bikeRepository.existsByPlate(motorcycle.getPlate())) {
            throw new ExistingPlateErrorException("Motorcyle with this plate: " + motorcycle.getPlate() + " already exists.");
        } else return bikeRepository.save(motorcycle);
    }


    @Override
    public void deleteMotorcycleById(UUID id) {
        if (bikeRepository.existsById(id)) {
            bikeRepository.deleteById(id);
        }else throw new MotorcycleNotFoundException("Motorcycle with ID: " + id + " not found.");
    }

    @Override
    public void deleteMotorcycleByPlate(String plate) {
        if (bikeRepository.existsByPlate(plate)) {
            bikeRepository.deleteByPlate(plate);
        }else throw new MotorcycleNotFoundException("Motorcycle with Plate: " + plate + " not found.");
    }
}
