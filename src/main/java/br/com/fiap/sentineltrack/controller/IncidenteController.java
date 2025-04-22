package br.com.fiap.sentineltrack.controller;

import br.com.fiap.sentineltrack.dto.IncidenteDTO;
import br.com.fiap.sentineltrack.service.IncidenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/incidentes")
public class IncidenteController {

    private final IncidenteService incidenteService;

    @Autowired
    public IncidenteController(IncidenteService incidenteService) {
        this.incidenteService = incidenteService;
    }

    @GetMapping
    public ResponseEntity<Page<IncidenteDTO>> listarTodos(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) Integer severidade,
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long localId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {
        
        Page<IncidenteDTO> incidentes = incidenteService.buscarPorFiltros(
                titulo, status, tipo, severidade, usuarioId, localId, dataInicio, dataFim, pageable);
        return ResponseEntity.ok(incidentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenteDTO> buscarPorId(@PathVariable Long id) {
        IncidenteDTO incidente = incidenteService.buscarPorId(id);
        return ResponseEntity.ok(incidente);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<IncidenteDTO>> listarPorUsuario(
            @PathVariable Long usuarioId,
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {
        
        Page<IncidenteDTO> incidentes = incidenteService.buscarPorFiltros(
                null, null, null, null, usuarioId, null, null, null, pageable);
        return ResponseEntity.ok(incidentes);
    }

    @GetMapping("/local/{localId}")
    public ResponseEntity<Page<IncidenteDTO>> listarPorLocal(
            @PathVariable Long localId,
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {
        
        Page<IncidenteDTO> incidentes = incidenteService.buscarPorFiltros(
                null, null, null, null, null, localId, null, null, pageable);
        return ResponseEntity.ok(incidentes);
    }

    @PostMapping
    public ResponseEntity<IncidenteDTO> criar(@Valid @RequestBody IncidenteDTO incidenteDTO) {
        IncidenteDTO novoIncidente = incidenteService.criar(incidenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoIncidente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidenteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody IncidenteDTO incidenteDTO) {
        IncidenteDTO incidenteAtualizado = incidenteService.atualizar(id, incidenteDTO);
        return ResponseEntity.ok(incidenteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        incidenteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
