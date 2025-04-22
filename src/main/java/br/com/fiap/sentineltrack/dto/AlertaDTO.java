package br.com.fiap.sentineltrack.dto;

import br.com.fiap.sentineltrack.entity.Alerta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertaDTO {

    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String titulo;

    private String mensagem;

    @NotNull(message = "A prioridade é obrigatória")
    private Integer prioridade;

    private String status;
    private String tipo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataLeitura;

    @NotNull(message = "O incidente é obrigatório")
    private Long incidenteId;
    private String incidenteTitulo;

    public static AlertaDTO fromEntity(Alerta alerta) {
        AlertaDTO dto = new AlertaDTO();
        dto.setId(alerta.getId());
        dto.setTitulo(alerta.getTitulo());
        dto.setMensagem(alerta.getMensagem());
        dto.setPrioridade(alerta.getPrioridade());
        dto.setStatus(alerta.getStatus());
        dto.setTipo(alerta.getTipo());
        dto.setDataCriacao(alerta.getDataCriacao());
        dto.setDataAtualizacao(alerta.getDataAtualizacao());
        dto.setDataLeitura(alerta.getDataLeitura());
        
        if (alerta.getIncidente() != null) {
            dto.setIncidenteId(alerta.getIncidente().getId());
            dto.setIncidenteTitulo(alerta.getIncidente().getTitulo());
        }
        
        return dto;
    }

    public Alerta toEntity() {
        Alerta alerta = new Alerta();
        alerta.setId(this.id);
        alerta.setTitulo(this.titulo);
        alerta.setMensagem(this.mensagem);
        alerta.setPrioridade(this.prioridade);
        alerta.setStatus(this.status);
        alerta.setTipo(this.tipo);
        alerta.setDataLeitura(this.dataLeitura);
        return alerta;
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
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

    public LocalDateTime getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(LocalDateTime dataLeitura) {
        this.dataLeitura = dataLeitura;
    }

    public Long getIncidenteId() {
        return incidenteId;
    }

    public void setIncidenteId(Long incidenteId) {
        this.incidenteId = incidenteId;
    }

    public String getIncidenteTitulo() {
        return incidenteTitulo;
    }

    public void setIncidenteTitulo(String incidenteTitulo) {
        this.incidenteTitulo = incidenteTitulo;
    }
}
