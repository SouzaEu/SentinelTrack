package br.com.SentinelTrack.services.interfaces;

import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.Update;
import br.com.SentinelTrack.models.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UpdatesService {
    List<Update> findAll();

    Update findAllById(UUID id);

    List<Update> findByUserId(User user);

    List<Update> findByMotorcycleId(Motorcycle motorcycle);

    List<Update> findByDate(Date date);

    void deleteById(UUID id);

    Update update(Update update);

    Update post(Update update);
}
