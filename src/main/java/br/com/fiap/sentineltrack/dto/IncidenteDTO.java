package br.com.fiap.sentineltrack.dto;

import br.com.fiap.sentineltrack.entity.Incidente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class IncidenteDTO {

    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String titulo;

    private String descricao;

    @NotNull(message = "A severidade é obrigatória")
    private Integer severidade;

    private String status;
    private String tipo;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataResolucao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;
    private String usuarioNome;

    @NotNull(message = "O local é obrigatório")
    private Long localId;
    private String localNome;

    private Set<Long> ativosIds = new HashSet<>();
    private Set<AtivoDTO> ativos = new HashSet<>();

    public static IncidenteDTO fromEntity(Incidente incidente) {
        IncidenteDTO dto = new IncidenteDTO();
        dto.setId(incidente.getId());
        dto.setTitulo(incidente.getTitulo());
        dto.setDescricao(incidente.getDescricao());
        dto.setSeveridade(incidente.getSeveridade());
        dto.setStatus(incidente.getStatus());
        dto.setTipo(incidente.getTipo());
        dto.setDataOcorrencia(incidente.getDataOcorrencia());
        dto.setDataResolucao(incidente.getDataResolucao());
        dto.setDataCriacao(incidente.getDataCriacao());
        dto.setDataAtualizacao(incidente.getDataAtualizacao());
        
        if (incidente.getUsuario() != null) {
            dto.setUsuarioId(incidente.getUsuario().getId());
            dto.setUsuarioNome(incidente.getUsuario().getNome());
        }
        
        if (incidente.getLocal() != null) {
            dto.setLocalId(incidente.getLocal().getId());
            dto.setLocalNome(incidente.getLocal().getNome());
        }
        
        if (incidente.getAtivos() != null) {
            dto.setAtivosIds(incidente.getAtivos().stream()
                    .map(ativo -> ativo.getId())
                    .collect(Collectors.toSet()));
            
            dto.setAtivos(incidente.getAtivos().stream()
                    .map(ativo -> AtivoDTO.fromEntity(ativo))
                    .collect(Collectors.toSet()));
        }
        
        return dto;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getSeveridade() {
        return severidade;
    }

    public void setSeveridade(Integer severidade) {
        this.severidade = severidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(LocalDateTime dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public LocalDateTime getDataResolucao() {
        return dataResolucao;
    }

    public void setDataResolucao(LocalDateTime dataResolucao) {
        this.dataResolucao = dataResolucao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public Long getLocalId() {
        return localId;
    }

    public void setLocalId(Long localId) {
        this.localId = localId;
    }

    public String getLocalNome() {
        return localNome;
    }

    public void setLocalNome(String localNome) {
        this.localNome = localNome;
    }

    public Set<Long> getAtivosIds() {
        return ativosIds;
    }

    public void setAtivosIds(Set<Long> ativosIds) {
        this.ativosIds = ativosIds;
    }

    public Set<AtivoDTO> getAtivos() {
        return ativos;
    }

    public void setAtivos(Set<AtivoDTO> ativos) {
        this.ativos = ativos;
    }
}
