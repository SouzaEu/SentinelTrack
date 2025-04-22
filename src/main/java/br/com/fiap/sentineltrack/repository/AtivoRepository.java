package br.com.fiap.sentineltrack.repository;

import br.com.fiap.sentineltrack.entity.Ativo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    
    @Query("SELECT a FROM Ativo a WHERE " +
           "(:nome IS NULL OR LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
           "(:tipo IS NULL OR LOWER(a.tipo) LIKE LOWER(CONCAT('%', :tipo, '%'))) AND " +
           "(:numeroSerie IS NULL OR LOWER(a.numeroSerie) LIKE LOWER(CONCAT('%', :numeroSerie, '%'))) AND " +
           "(:fabricante IS NULL OR LOWER(a.fabricante) LIKE LOWER(CONCAT('%', :fabricante, '%'))) AND " +
           "(:status IS NULL OR LOWER(a.status) LIKE LOWER(CONCAT('%', :status, '%')))")
    Page<Ativo> buscarPorFiltros(
            @Param("nome") String nome,
            @Param("tipo") String tipo,
            @Param("numeroSerie") String numeroSerie,
            @Param("fabricante") String fabricante,
            @Param("status") String status,
            Pageable pageable);
}
