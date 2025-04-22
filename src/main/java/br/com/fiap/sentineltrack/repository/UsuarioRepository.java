package br.com.fiap.sentineltrack.repository;

import br.com.fiap.sentineltrack.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);
    
    @Query("SELECT u FROM Usuario u WHERE " +
           "(:nome IS NULL OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
           "(:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
           "(:cargo IS NULL OR LOWER(u.cargo) LIKE LOWER(CONCAT('%', :cargo, '%'))) AND " +
           "(:departamento IS NULL OR LOWER(u.departamento) LIKE LOWER(CONCAT('%', :departamento, '%')))")
    Page<Usuario> buscarPorFiltros(
            @Param("nome") String nome,
            @Param("email") String email,
            @Param("cargo") String cargo,
            @Param("departamento") String departamento,
            Pageable pageable);
    
    boolean existsByEmail(String email);
}
