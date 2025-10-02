package br.com.SentinelTrack.repositories;


import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.Update;
import br.com.SentinelTrack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UpdateRepository extends JpaRepository<Update, UUID> {


    List<Update> findAll();

    List<Update> findAllByUser(User user);

    List<Update> findAllByMotorcycle(Motorcycle motorcycle);

    List<Update> findAllByUpdateDate(Date updateDate);

    boolean existsById(UUID id);

    boolean existsByUser(User user);

    @Override
    void deleteById(UUID id);
}
