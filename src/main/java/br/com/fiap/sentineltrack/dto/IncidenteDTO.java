package br.com.fiap.sentineltrack.dto;

import br.com.fiap.sentineltrack.entity.Incidente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(description = "DTO para representação de um incidente")
public class IncidenteDTO {

    @Schema(description = "ID do incidente", example = "1")
    private Long id;

    @Schema(description = "Título do incidente", example = "Falha no sistema de energia")
    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String titulo;

    @Schema(description = "Descrição detalhada do incidente", example = "Falha no sistema de energia do prédio A")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @Schema(description = "Nível de severidade do incidente (1-5)", example = "3")
    @NotNull(message = "A severidade é obrigatória")
    @Min(value = 1, message = "A severidade deve ser no mínimo 1")
    @Max(value = 5, message = "A severidade deve ser no máximo 5")
    private Integer severidade;

    @Schema(description = "Status atual do incidente", example = "EM_ANDAMENTO")
    @NotBlank(message = "O status é obrigatório")
    private String status;

    @Schema(description = "Tipo do incidente", example = "FALHA_SISTEMA")
    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    @Schema(description = "Data e hora da ocorrência do incidente")
    @NotNull(message = "A data de ocorrência é obrigatória")
    @PastOrPresent(message = "A data de ocorrência não pode ser futura")
    private LocalDateTime dataOcorrencia;

    @Schema(description = "Data e hora da resolução do incidente")
    private LocalDateTime dataResolucao;

    @Schema(description = "Data e hora de criação do registro")
    private LocalDateTime dataCriacao;

    @Schema(description = "Data e hora da última atualização")
    private LocalDateTime dataAtualizacao;

    @Schema(description = "ID do usuário responsável pelo incidente", example = "1")
    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;

    @Schema(description = "Nome do usuário responsável")
    private String usuarioNome;

    @Schema(description = "ID do local onde ocorreu o incidente", example = "1")
    @NotNull(message = "O local é obrigatório")
    private Long localId;

    @Schema(description = "Nome do local onde ocorreu o incidente")
    private String localNome;

    @Schema(description = "IDs dos ativos afetados pelo incidente")
    private Set<Long> ativosIds = new HashSet<>();

    @Schema(description = "Detalhes dos ativos afetados")
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
