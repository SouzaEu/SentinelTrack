package br.com.fiap.sentineltrack.repository;

import br.com.fiap.sentineltrack.entity.Incidente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
    
    Page<Incidente> findByUsuarioId(Long usuarioId, Pageable pageable);
    
    Page<Incidente> findByLocalId(Long localId, Pageable pageable);
    
    @Query("SELECT i FROM Incidente i JOIN i.ativos a WHERE a.id = :ativoId")
    Page<Incidente> findByAtivoId(@Param("ativoId") Long ativoId, Pageable pageable);
    
    @Query("SELECT i FROM Incidente i WHERE " +
           "(:titulo IS NULL OR LOWER(i.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) AND " +
           "(:status IS NULL OR LOWER(i.status) LIKE LOWER(CONCAT('%', :status, '%'))) AND " +
           "(:tipo IS NULL OR LOWER(i.tipo) LIKE LOWER(CONCAT('%', :tipo, '%'))) AND " +
           "(:severidade IS NULL OR i.severidade = :severidade) AND " +
           "(:usuarioId IS NULL OR i.usuario.id = :usuarioId) AND " +
           "(:localId IS NULL OR i.local.id = :localId) AND " +
           "(:dataInicio IS NULL OR i.dataOcorrencia >= :dataInicio) AND " +
           "(:dataFim IS NULL OR i.dataOcorrencia <= :dataFim)")
    Page<Incidente> buscarPorFiltros(
            @Param("titulo") String titulo,
            @Param("status") String status,
            @Param("tipo") String tipo,
            @Param("severidade") Integer severidade,
            @Param("usuarioId") Long usuarioId,
            @Param("localId") Long localId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            Pageable pageable);
}
