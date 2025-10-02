package br.com.SentinelTrack.repositories;

import br.com.SentinelTrack.models.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SectorRepository extends JpaRepository<Sector, UUID> {
}
