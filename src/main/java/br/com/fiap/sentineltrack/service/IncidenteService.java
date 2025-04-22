package br.com.fiap.sentineltrack.service;

import br.com.fiap.sentineltrack.dto.IncidenteDTO;
import br.com.fiap.sentineltrack.entity.Ativo;
import br.com.fiap.sentineltrack.entity.Incidente;
import br.com.fiap.sentineltrack.entity.Local;
import br.com.fiap.sentineltrack.entity.Usuario;
import br.com.fiap.sentineltrack.exception.RecursoNaoEncontradoException;
import br.com.fiap.sentineltrack.repository.AtivoRepository;
import br.com.fiap.sentineltrack.repository.IncidenteRepository;
import br.com.fiap.sentineltrack.repository.LocalRepository;
import br.com.fiap.sentineltrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class IncidenteService {

    private final IncidenteRepository incidenteRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalRepository localRepository;
    private final AtivoRepository ativoRepository;

    @Autowired
    public IncidenteService(
            IncidenteRepository incidenteRepository,
            UsuarioRepository usuarioRepository,
            LocalRepository localRepository,
            AtivoRepository ativoRepository) {
        this.incidenteRepository = incidenteRepository;
        this.usuarioRepository = usuarioRepository;
        this.localRepository = localRepository;
        this.ativoRepository = ativoRepository;
    }

    @Cacheable(value = "incidentes", key = "#id")
    public IncidenteDTO buscarPorId(Long id) {
        Incidente incidente = incidenteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Incidente não encontrado com o ID: " + id));
        return IncidenteDTO.fromEntity(incidente);
    }

    @Cacheable(value = "incidentes")
    public Page<IncidenteDTO> listarTodos(Pageable pageable) {
        Page<Incidente> incidentes = incidenteRepository.findAll(pageable);
        return incidentes.map(IncidenteDTO::fromEntity);
    }

    @Cacheable(value = "incidentes")
    public Page<IncidenteDTO> buscarPorFiltros(
            String titulo, String status, String tipo, Integer severidade,
            Long usuarioId, Long localId, LocalDateTime dataInicio, LocalDateTime dataFim,
            Pageable pageable) {
        Page<Incidente> incidentes = incidenteRepository.buscarPorFiltros(
                titulo, status, tipo, severidade, usuarioId, localId, dataInicio, dataFim, pageable);
        return incidentes.map(IncidenteDTO::fromEntity);
    }

    @Transactional
    @CacheEvict(value = {"incidentes", "alertas"}, allEntries = true)
    public IncidenteDTO criar(IncidenteDTO incidenteDTO) {
        Usuario usuario = usuarioRepository.findById(incidenteDTO.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + incidenteDTO.getUsuarioId()));
        
        Local local = localRepository.findById(incidenteDTO.getLocalId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Local não encontrado com o ID: " + incidenteDTO.getLocalId()));
        
        Incidente incidente = new Incidente();
        incidente.setTitulo(incidenteDTO.getTitulo());
        incidente.setDescricao(incidenteDTO.getDescricao());
        incidente.setSeveridade(incidenteDTO.getSeveridade());
        incidente.setStatus(incidenteDTO.getStatus());
        incidente.setTipo(incidenteDTO.getTipo());
        incidente.setDataOcorrencia(incidenteDTO.getDataOcorrencia());
        incidente.setDataResolucao(incidenteDTO.getDataResolucao());
        incidente.setUsuario(usuario);
        incidente.setLocal(local);
        
        if (incidenteDTO.getAtivosIds() != null && !incidenteDTO.getAtivosIds().isEmpty()) {
            Set<Ativo> ativos = new HashSet<>();
            for (Long ativoId : incidenteDTO.getAtivosIds()) {
                Ativo ativo = ativoRepository.findById(ativoId)
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Ativo não encontrado com o ID: " + ativoId));
                ativos.add(ativo);
            }
            incidente.setAtivos(ativos);
        }
        
        incidente = incidenteRepository.save(incidente);
        return IncidenteDTO.fromEntity(incidente);
    }

    @Transactional
    @CacheEvict(value = {"incidentes", "alertas"}, allEntries = true)
    public IncidenteDTO atualizar(Long id, IncidenteDTO incidenteDTO) {
        Incidente incidenteExistente = incidenteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Incidente não encontrado com o ID: " + id));
        
        Usuario usuario = usuarioRepository.findById(incidenteDTO.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + incidenteDTO.getUsuarioId()));
        
        Local local = localRepository.findById(incidenteDTO.getLocalId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Local não encontrado com o ID: " + incidenteDTO.getLocalId()));
        
        incidenteExistente.setTitulo(incidenteDTO.getTitulo());
        incidenteExistente.setDescricao(incidenteDTO.getDescricao());
        incidenteExistente.setSeveridade(incidenteDTO.getSeveridade());
        incidenteExistente.setStatus(incidenteDTO.getStatus());
        incidenteExistente.setTipo(incidenteDTO.getTipo());
        incidenteExistente.setDataOcorrencia(incidenteDTO.getDataOcorrencia());
        incidenteExistente.setDataResolucao(incidenteDTO.getDataResolucao());
        incidenteExistente.setUsuario(usuario);
        incidenteExistente.setLocal(local);
        
        if (incidenteDTO.getAtivosIds() != null) {
            incidenteExistente.getAtivos().clear();
            for (Long ativoId : incidenteDTO.getAtivosIds()) {
                Ativo ativo = ativoRepository.findById(ativoId)
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Ativo não encontrado com o ID: " + ativoId));
                incidenteExistente.adicionarAtivo(ativo);
            }
        }
        
        incidenteExistente = incidenteRepository.save(incidenteExistente);
        return IncidenteDTO.fromEntity(incidenteExistente);
    }

    @Transactional
    @CacheEvict(value = {"incidentes", "alertas"}, allEntries = true)
    public void excluir(Long id) {
        if (!incidenteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Incidente não encontrado com o ID: " + id);
        }
        incidenteRepository.deleteById(id);
    }
}
