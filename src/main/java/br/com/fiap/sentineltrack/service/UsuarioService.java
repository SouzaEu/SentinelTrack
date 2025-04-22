package br.com.fiap.sentineltrack.service;

import br.com.fiap.sentineltrack.dto.UsuarioDTO;
import br.com.fiap.sentineltrack.entity.Usuario;
import br.com.fiap.sentineltrack.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentineltrack.exception.RegraDeNegocioException;
import br.com.fiap.sentineltrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Cacheable(value = "usuarios", key = "#id")
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + id));
        return UsuarioDTO.fromEntity(usuario);
    }

    @Cacheable(value = "usuarios")
    public Page<UsuarioDTO> listarTodos(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(UsuarioDTO::fromEntity);
    }

    @Cacheable(value = "usuarios")
    public Page<UsuarioDTO> buscarPorFiltros(String nome, String email, String cargo, String departamento, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.buscarPorFiltros(nome, email, cargo, departamento, pageable);
        return usuarios.map(UsuarioDTO::fromEntity);
    }

    @Transactional
    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO criar(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new RegraDeNegocioException("Já existe um usuário com o email: " + usuarioDTO.getEmail());
        }
        
        Usuario usuario = usuarioDTO.toEntity();
        usuario = usuarioRepository.save(usuario);
        return UsuarioDTO.fromEntity(usuario);
    }

    @Transactional
    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + id));
        
        if (!usuarioExistente.getEmail().equals(usuarioDTO.getEmail()) && 
                usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new RegraDeNegocioException("Já existe um usuário com o email: " + usuarioDTO.getEmail());
        }
        
        usuarioExistente.setNome(usuarioDTO.getNome());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        if (usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().isEmpty()) {
            usuarioExistente.setSenha(usuarioDTO.getSenha());
        }
        usuarioExistente.setCargo(usuarioDTO.getCargo());
        usuarioExistente.setDepartamento(usuarioDTO.getDepartamento());
        
        usuarioExistente = usuarioRepository.save(usuarioExistente);
        return UsuarioDTO.fromEntity(usuarioExistente);
    }

    @Transactional
    @CacheEvict(value = "usuarios", allEntries = true)
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
