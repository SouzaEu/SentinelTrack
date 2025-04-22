package br.com.fiap.sentineltrack.repository;

import br.com.fiap.sentineltrack.entity.Alerta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    
    Page<Alerta> findByIncidenteId(Long incidenteId, Pageable pageable);
    
    @Query("SELECT a FROM Alerta a WHERE " +
           "(:titulo IS NULL OR LOWER(a.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) AND " +
           "(:status IS NULL OR LOWER(a.status) LIKE LOWER(CONCAT('%', :status, '%'))) AND " +
           "(:tipo IS NULL OR LOWER(a.tipo) LIKE LOWER(CONCAT('%', :tipo, '%'))) AND " +
           "(:prioridade IS NULL OR a.prioridade = :prioridade) AND " +
           "(:incidenteId IS NULL OR a.incidente.id = :incidenteId)")
    Page<Alerta> buscarPorFiltros(
            @Param("titulo") String titulo,
            @Param("status") String status,
            @Param("tipo") String tipo,
            @Param("prioridade") Integer prioridade,
            @Param("incidenteId") Long incidenteId,
            Pageable pageable);
}
