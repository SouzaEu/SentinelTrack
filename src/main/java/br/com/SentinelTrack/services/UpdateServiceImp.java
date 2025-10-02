package br.com.SentinelTrack.services;

import br.com.SentinelTrack.exceptions.ExistingUpdateErrorException;
import br.com.SentinelTrack.exceptions.MotorcycleNotFoundException;
import br.com.SentinelTrack.exceptions.UpdateNotFoundException;
import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.Update;
import br.com.SentinelTrack.models.User;
import br.com.SentinelTrack.repositories.MotorcycleRepository;
import br.com.SentinelTrack.repositories.UpdateRepository;
import br.com.SentinelTrack.repositories.UserRepository;
import br.com.SentinelTrack.services.interfaces.UpdatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UpdateServiceImp implements UpdatesService {

    @Autowired
    private UpdateRepository updateRepository;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Update> findAll() {
        return updateRepository.findAll();
    }

    @Override
    public Update findAllById(UUID id) {
        return updateRepository.findById(id).orElseThrow(() -> new UpdateNotFoundException("No update found with id " + id));
    }


    @Override
    public List<Update> findByUserId(User user) {
        return updateRepository.findAllByUser(user);
    }

    @Override
    public List<Update> findByMotorcycleId(Motorcycle motorcycle) {
        if(motorcycleRepository.existsById(motorcycle.getId())) {
            return updateRepository.findAllByMotorcycle(motorcycle);
        }else throw new MotorcycleNotFoundException("This Motorcycle does not exist");   //<----------------------------------------------
    }

    @Override
    public List<Update> findByDate(Date date) {
        return updateRepository.findAllByUpdateDate(date);
    }

    @Override
    public void deleteById(UUID id) {
        if(updateRepository.existsById(id)){
            updateRepository.deleteById(id);
        }else throw new ExistingUpdateErrorException("This update does not exist");

    }

    @Override
    public Update update(Update update) {
        if(userRepository.existsById(update.getId())){
            return updateRepository.save(update);
        }else throw new ExistingUpdateErrorException("This update does not exist");
    }

    @Override
    public Update post(Update update) {
        if (!updateRepository.existsById(update.getId())){
            return updateRepository.save(update);
        } else throw new ExistingUpdateErrorException("This update alread exists");
    }
}
