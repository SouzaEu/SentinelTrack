package br.com.fiap.sentineltrack.controller;

import br.com.fiap.sentineltrack.dto.AtivoDTO;
import br.com.fiap.sentineltrack.service.AtivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ativos")
public class AtivoController {

    private final AtivoService ativoService;

    @Autowired
    public AtivoController(AtivoService ativoService) {
        this.ativoService = ativoService;
    }

    @GetMapping
    public ResponseEntity<Page<AtivoDTO>> listarTodos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String numeroSerie,
            @RequestParam(required = false) String fabricante,
            @RequestParam(required = false) String status,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        
        Page<AtivoDTO> ativos = ativoService.buscarPorFiltros(nome, tipo, numeroSerie, fabricante, status, pageable);
        return ResponseEntity.ok(ativos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtivoDTO> buscarPorId(@PathVariable Long id) {
        AtivoDTO ativo = ativoService.buscarPorId(id);
        return ResponseEntity.ok(ativo);
    }

    @PostMapping
    public ResponseEntity<AtivoDTO> criar(@Valid @RequestBody AtivoDTO ativoDTO) {
        AtivoDTO novoAtivo = ativoService.criar(ativoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAtivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtivoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AtivoDTO ativoDTO) {
        AtivoDTO ativoAtualizado = ativoService.atualizar(id, ativoDTO);
        return ResponseEntity.ok(ativoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        ativoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
