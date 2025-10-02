package br.com.SentinelTrack.services;

import br.com.SentinelTrack.models.Sector;
import br.com.SentinelTrack.repositories.SectorRepository;
import br.com.SentinelTrack.services.interfaces.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SectorServiceImp implements SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> findAll() {
        return sectorRepository.findAll();
    }

    public Sector findById(UUID id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector not found: " + id));
    }

    public Sector save(Sector sector) {
        return sectorRepository.save(sector);
    }

    public Sector update(Sector sector) {
        sectorRepository.findById(sector.getId())
                .orElseThrow(() -> new RuntimeException("Sector not found: " + sector.getId()));;
        Sector found = findById(sector.getId());
        found.setName(sector.getName());
        found.setSpots(sector.getSpots());
        return sectorRepository.save(found);
    }

    public void delete(UUID id) {
        sectorRepository.deleteById(id);
    }
}
