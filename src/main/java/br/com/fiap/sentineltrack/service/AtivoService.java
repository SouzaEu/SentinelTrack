package br.com.fiap.sentineltrack.service;

import br.com.fiap.sentineltrack.dto.AtivoDTO;
import br.com.fiap.sentineltrack.entity.Ativo;
import br.com.fiap.sentineltrack.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentineltrack.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtivoService {

    private final AtivoRepository ativoRepository;

    @Autowired
    public AtivoService(AtivoRepository ativoRepository) {
        this.ativoRepository = ativoRepository;
    }

    @Cacheable(value = "ativos", key = "#id")
    public AtivoDTO buscarPorId(Long id) {
        Ativo ativo = ativoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ativo não encontrado com o ID: " + id));
        return AtivoDTO.fromEntity(ativo);
    }

    @Cacheable(value = "ativos")
    public Page<AtivoDTO> listarTodos(Pageable pageable) {
        Page<Ativo> ativos = ativoRepository.findAll(pageable);
        return ativos.map(AtivoDTO::fromEntity);
    }

    @Cacheable(value = "ativos")
    public Page<AtivoDTO> buscarPorFiltros(String nome, String tipo, String numeroSerie, String fabricante, String status, Pageable pageable) {
        Page<Ativo> ativos = ativoRepository.buscarPorFiltros(nome, tipo, numeroSerie, fabricante, status, pageable);
        return ativos.map(AtivoDTO::fromEntity);
    }

    @Transactional
    @CacheEvict(value = "ativos", allEntries = true)
    public AtivoDTO criar(AtivoDTO ativoDTO) {
        Ativo ativo = ativoDTO.toEntity();
        ativo = ativoRepository.save(ativo);
        return AtivoDTO.fromEntity(ativo);
    }

    @Transactional
    @CacheEvict(value = "ativos", allEntries = true)
    public AtivoDTO atualizar(Long id, AtivoDTO ativoDTO) {
        Ativo ativoExistente = ativoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ativo não encontrado com o ID: " + id));
        
        ativoExistente.setNome(ativoDTO.getNome());
        ativoExistente.setTipo(ativoDTO.getTipo());
        ativoExistente.setNumeroSerie(ativoDTO.getNumeroSerie());
        ativoExistente.setFabricante(ativoDTO.getFabricante());
        ativoExistente.setModelo(ativoDTO.getModelo());
        ativoExistente.setStatus(ativoDTO.getStatus());
        ativoExistente.setDataAquisicao(ativoDTO.getDataAquisicao());
        
        ativoExistente = ativoRepository.save(ativoExistente);
        return AtivoDTO.fromEntity(ativoExistente);
    }

    @Transactional
    @CacheEvict(value = "ativos", allEntries = true)
    public void excluir(Long id) {
        if (!ativoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Ativo não encontrado com o ID: " + id);
        }
        ativoRepository.deleteById(id);
    }
}
