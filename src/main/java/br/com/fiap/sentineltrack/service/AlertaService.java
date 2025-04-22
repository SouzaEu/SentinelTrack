package br.com.fiap.sentineltrack.service;

import br.com.fiap.sentineltrack.dto.AlertaDTO;
import br.com.fiap.sentineltrack.entity.Alerta;
import br.com.fiap.sentineltrack.entity.Incidente;
import br.com.fiap.sentineltrack.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentineltrack.repository.AlertaRepository;
import br.com.fiap.sentineltrack.repository.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final IncidenteRepository incidenteRepository;

    @Autowired
    public AlertaService(AlertaRepository alertaRepository, IncidenteRepository incidenteRepository) {
        this.alertaRepository = alertaRepository;
        this.incidenteRepository = incidenteRepository;
    }

    @Cacheable(value = "alertas", key = "#id")
    public AlertaDTO buscarPorId(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Alerta não encontrado com o ID: " + id));
        return AlertaDTO.fromEntity(alerta);
    }

    @Cacheable(value = "alertas")
    public Page<AlertaDTO> listarTodos(Pageable pageable) {
        Page<Alerta> alertas = alertaRepository.findAll(pageable);
        return alertas.map(AlertaDTO::fromEntity);
    }

    @Cacheable(value = "alertas")
    public Page<AlertaDTO> buscarPorFiltros(
            String titulo, String status, String tipo, Integer prioridade, Long incidenteId, Pageable pageable) {
        Page<Alerta> alertas = alertaRepository.buscarPorFiltros(titulo, status, tipo, prioridade, incidenteId, pageable);
        return alertas.map(AlertaDTO::fromEntity);
    }

    @Cacheable(value = "alertas")
    public Page<AlertaDTO> buscarPorIncidente(Long incidenteId, Pageable pageable) {
        Page<Alerta> alertas = alertaRepository.findByIncidenteId(incidenteId, pageable);
        return alertas.map(AlertaDTO::fromEntity);
    }

    @Transactional
    @CacheEvict(value = "alertas", allEntries = true)
    public AlertaDTO criar(AlertaDTO alertaDTO) {
        Incidente incidente = incidenteRepository.findById(alertaDTO.getIncidenteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Incidente não encontrado com o ID: " + alertaDTO.getIncidenteId()));
        
        Alerta alerta = alertaDTO.toEntity();
        alerta.setIncidente(incidente);
        alerta = alertaRepository.save(alerta);
        return AlertaDTO.fromEntity(alerta);
    }

    @Transactional
    @CacheEvict(value = "alertas", allEntries = true)
    public AlertaDTO atualizar(Long id, AlertaDTO alertaDTO) {
        Alerta alertaExistente = alertaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Alerta não encontrado com o ID: " + id));
        
        Incidente incidente = incidenteRepository.findById(alertaDTO.getIncidenteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Incidente não encontrado com o ID: " + alertaDTO.getIncidenteId()));
        
        alertaExistente.setTitulo(alertaDTO.getTitulo());
        alertaExistente.setMensagem(alertaDTO.getMensagem());
        alertaExistente.setPrioridade(alertaDTO.getPrioridade());
        alertaExistente.setStatus(alertaDTO.getStatus());
        alertaExistente.setTipo(alertaDTO.getTipo());
        alertaExistente.setIncidente(incidente);
        
        alertaExistente = alertaRepository.save(alertaExistente);
        return AlertaDTO.fromEntity(alertaExistente);
    }

    @Transactional
    @CacheEvict(value = "alertas", allEntries = true)
    public void excluir(Long id) {
        if (!alertaRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Alerta não encontrado com o ID: " + id);
        }
        alertaRepository.deleteById(id);
    }

    @Transactional
    @CacheEvict(value = "alertas", allEntries = true)
    public AlertaDTO marcarComoLido(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Alerta não encontrado com o ID: " + id));
        
        alerta.setStatus("LIDO");
        alerta.setDataLeitura(LocalDateTime.now());
        
        alerta = alertaRepository.save(alerta);
        return AlertaDTO.fromEntity(alerta);
    }
}
