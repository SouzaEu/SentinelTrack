package br.com.fiap.sentineltrack.service;

import br.com.fiap.sentineltrack.dto.IncidenteDTO;
import br.com.fiap.sentineltrack.entity.Incidente;
import br.com.fiap.sentineltrack.entity.Usuario;
import br.com.fiap.sentineltrack.entity.Local;
import br.com.fiap.sentineltrack.repository.IncidenteRepository;
import br.com.fiap.sentineltrack.repository.UsuarioRepository;
import br.com.fiap.sentineltrack.repository.LocalRepository;
import br.com.fiap.sentineltrack.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class IncidenteServiceTest {

    @Mock
    private IncidenteRepository incidenteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private LocalRepository localRepository;

    @InjectMocks
    private IncidenteService incidenteService;

    private Incidente incidente;
    private IncidenteDTO incidenteDTO;
    private Usuario usuario;
    private Local local;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste Usuario");

        local = new Local();
        local.setId(1L);
        local.setNome("Teste Local");

        incidente = new Incidente();
        incidente.setId(1L);
        incidente.setTitulo("Teste Incidente");
        incidente.setDescricao("Descrição do teste");
        incidente.setSeveridade(3);
        incidente.setStatus("EM_ANDAMENTO");
        incidente.setTipo("FALHA_SISTEMA");
        incidente.setDataOcorrencia(LocalDateTime.now());
        incidente.setUsuario(usuario);
        incidente.setLocal(local);

        incidenteDTO = new IncidenteDTO();
        incidenteDTO.setId(1L);
        incidenteDTO.setTitulo("Teste Incidente");
        incidenteDTO.setDescricao("Descrição do teste");
        incidenteDTO.setSeveridade(3);
        incidenteDTO.setStatus("EM_ANDAMENTO");
        incidenteDTO.setTipo("FALHA_SISTEMA");
        incidenteDTO.setDataOcorrencia(LocalDateTime.now());
        incidenteDTO.setUsuarioId(1L);
        incidenteDTO.setLocalId(1L);
    }

    @Test
    void quandoBuscarPorId_entaoRetornaIncidente() {
        when(incidenteRepository.findById(1L)).thenReturn(Optional.of(incidente));

        IncidenteDTO resultado = incidenteService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(incidente.getId(), resultado.getId());
        assertEquals(incidente.getTitulo(), resultado.getTitulo());
    }

    @Test
    void quandoBuscarPorIdInexistente_entaoLancaExcecao() {
        when(incidenteRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> {
            incidenteService.buscarPorId(999L);
        });
    }

    @Test
    void quandoListarTodos_entaoRetornaPaginaDeIncidentes() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Incidente> page = new PageImpl<>(Arrays.asList(incidente));
        when(incidenteRepository.findAll(pageable)).thenReturn(page);

        Page<IncidenteDTO> resultado = incidenteService.listarTodos(pageable);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        assertEquals(incidente.getId(), resultado.getContent().get(0).getId());
    }

    @Test
    void quandoCriar_entaoRetornaIncidenteCriado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(localRepository.findById(1L)).thenReturn(Optional.of(local));
        when(incidenteRepository.save(any(Incidente.class))).thenReturn(incidente);

        IncidenteDTO resultado = incidenteService.criar(incidenteDTO);

        assertNotNull(resultado);
        assertEquals(incidente.getId(), resultado.getId());
        assertEquals(incidente.getTitulo(), resultado.getTitulo());
        verify(incidenteRepository).save(any(Incidente.class));
    }

    @Test
    void quandoAtualizar_entaoRetornaIncidenteAtualizado() {
        when(incidenteRepository.findById(1L)).thenReturn(Optional.of(incidente));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(localRepository.findById(1L)).thenReturn(Optional.of(local));
        when(incidenteRepository.save(any(Incidente.class))).thenReturn(incidente);

        incidenteDTO.setTitulo("Título Atualizado");
        IncidenteDTO resultado = incidenteService.atualizar(1L, incidenteDTO);

        assertNotNull(resultado);
        assertEquals("Título Atualizado", resultado.getTitulo());
        verify(incidenteRepository).save(any(Incidente.class));
    }

    @Test
    void quandoExcluir_entaoRemoveIncidente() {
        when(incidenteRepository.findById(1L)).thenReturn(Optional.of(incidente));
        doNothing().when(incidenteRepository).delete(incidente);

        incidenteService.excluir(1L);

        verify(incidenteRepository).delete(incidente);
    }
} 