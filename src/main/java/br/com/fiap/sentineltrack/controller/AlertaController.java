package br.com.fiap.sentineltrack.controller;

import br.com.fiap.sentineltrack.dto.AlertaDTO;
import br.com.fiap.sentineltrack.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    @Autowired
    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @GetMapping
    public ResponseEntity<Page<AlertaDTO>> listarTodos(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) Integer prioridade,
            @RequestParam(required = false) Long incidenteId,
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {
        
        Page<AlertaDTO> alertas = alertaService.buscarPorFiltros(
                titulo, status, tipo, prioridade, incidenteId, pageable);
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaDTO> buscarPorId(@PathVariable Long id) {
        AlertaDTO alerta = alertaService.buscarPorId(id);
        return ResponseEntity.ok(alerta);
    }

    @GetMapping("/incidente/{incidenteId}")
    public ResponseEntity<Page<AlertaDTO>> listarPorIncidente(
            @PathVariable Long incidenteId,
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable pageable) {
        
        Page<AlertaDTO> alertas = alertaService.buscarPorIncidente(incidenteId, pageable);
        return ResponseEntity.ok(alertas);
    }

    @PostMapping
    public ResponseEntity<AlertaDTO> criar(@Valid @RequestBody AlertaDTO alertaDTO) {
        AlertaDTO novoAlerta = alertaService.criar(alertaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAlerta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AlertaDTO alertaDTO) {
        AlertaDTO alertaAtualizado = alertaService.atualizar(id, alertaDTO);
        return ResponseEntity.ok(alertaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        alertaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/marcar-como-lido")
    public ResponseEntity<AlertaDTO> marcarComoLido(@PathVariable Long id) {
        AlertaDTO alertaAtualizado = alertaService.marcarComoLido(id);
        return ResponseEntity.ok(alertaAtualizado);
    }
}
