package br.com.fiap.sentineltrack.controller;

import br.com.fiap.sentineltrack.dto.LocalDTO;
import br.com.fiap.sentineltrack.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locais")
public class LocalController {

    private final LocalService localService;

    @Autowired
    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping
    public ResponseEntity<Page<LocalDTO>> listarTodos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String pais,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        
        Page<LocalDTO> locais = localService.buscarPorFiltros(nome, cidade, estado, pais, pageable);
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> buscarPorId(@PathVariable Long id) {
        LocalDTO local = localService.buscarPorId(id);
        return ResponseEntity.ok(local);
    }

    @PostMapping
    public ResponseEntity<LocalDTO> criar(@Valid @RequestBody LocalDTO localDTO) {
        LocalDTO novoLocal = localService.criar(localDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLocal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LocalDTO localDTO) {
        LocalDTO localAtualizado = localService.atualizar(id, localDTO);
        return ResponseEntity.ok(localAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        localService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
