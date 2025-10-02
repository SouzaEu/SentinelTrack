package br.com.SentinelTrack.repositories;

import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

   User findAllByRm(String rm);

   List<User> findByName(String name);

   void deleteByRm(String rm);

   boolean existsByRm(String rm);
}
