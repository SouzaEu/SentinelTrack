package br.com.fiap.sentineltrack.repository;

import br.com.fiap.sentineltrack.entity.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    
    @Query("SELECT l FROM Local l WHERE " +
           "(:nome IS NULL OR LOWER(l.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
           "(:cidade IS NULL OR LOWER(l.cidade) LIKE LOWER(CONCAT('%', :cidade, '%'))) AND " +
           "(:estado IS NULL OR LOWER(l.estado) LIKE LOWER(CONCAT('%', :estado, '%'))) AND " +
           "(:pais IS NULL OR LOWER(l.pais) LIKE LOWER(CONCAT('%', :pais, '%')))")
    Page<Local> buscarPorFiltros(
            @Param("nome") String nome,
            @Param("cidade") String cidade,
            @Param("estado") String estado,
            @Param("pais") String pais,
            Pageable pageable);
}
