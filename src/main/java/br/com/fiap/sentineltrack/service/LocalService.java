package br.com.fiap.sentineltrack.service;

import br.com.fiap.sentineltrack.dto.LocalDTO;
import br.com.fiap.sentineltrack.entity.Local;
import br.com.fiap.sentineltrack.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentineltrack.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocalService {

    private final LocalRepository localRepository;

    @Autowired
    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Cacheable(value = "locais", key = "#id")
    public LocalDTO buscarPorId(Long id) {
        Local local = localRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Local não encontrado com o ID: " + id));
        return LocalDTO.fromEntity(local);
    }

    @Cacheable(value = "locais")
    public Page<LocalDTO> listarTodos(Pageable pageable) {
        Page<Local> locais = localRepository.findAll(pageable);
        return locais.map(LocalDTO::fromEntity);
    }

    @Cacheable(value = "locais")
    public Page<LocalDTO> buscarPorFiltros(String nome, String cidade, String estado, String pais, Pageable pageable) {
        Page<Local> locais = localRepository.buscarPorFiltros(nome, cidade, estado, pais, pageable);
        return locais.map(LocalDTO::fromEntity);
    }

    @Transactional
    @CacheEvict(value = "locais", allEntries = true)
    public LocalDTO criar(LocalDTO localDTO) {
        Local local = localDTO.toEntity();
        local = localRepository.save(local);
        return LocalDTO.fromEntity(local);
    }

    @Transactional
    @CacheEvict(value = "locais", allEntries = true)
    public LocalDTO atualizar(Long id, LocalDTO localDTO) {
        Local localExistente = localRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Local não encontrado com o ID: " + id));
        
        localExistente.setNome(localDTO.getNome());
        localExistente.setEndereco(localDTO.getEndereco());
        localExistente.setCidade(localDTO.getCidade());
        localExistente.setEstado(localDTO.getEstado());
        localExistente.setPais(localDTO.getPais());
        localExistente.setCep(localDTO.getCep());
        localExistente.setLatitude(localDTO.getLatitude());
        localExistente.setLongitude(localDTO.getLongitude());
        
        localExistente = localRepository.save(localExistente);
        return LocalDTO.fromEntity(localExistente);
    }

    @Transactional
    @CacheEvict(value = "locais", allEntries = true)
    public void excluir(Long id) {
        if (!localRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Local não encontrado com o ID: " + id);
        }
        localRepository.deleteById(id);
    }
}
