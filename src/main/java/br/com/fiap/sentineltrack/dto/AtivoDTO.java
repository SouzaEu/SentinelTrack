package br.com.fiap.sentineltrack.dto;

import br.com.fiap.sentineltrack.entity.Ativo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class AtivoDTO {

    private Long id;

    @NotBlank(message = "O nome do ativo é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do ativo deve ter entre 3 e 100 caracteres")
    private String nome;

    private String tipo;
    private String numeroSerie;
    private String fabricante;
    private String modelo;
    private String status;
    private LocalDateTime dataAquisicao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public AtivoDTO() {
    }

    public AtivoDTO(Long id, String nome, String tipo, String numeroSerie, String fabricante, String modelo, String status, LocalDateTime dataAquisicao, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.numeroSerie = numeroSerie;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.status = status;
        this.dataAquisicao = dataAquisicao;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public static AtivoDTO fromEntity(Ativo ativo) {
        AtivoDTO dto = new AtivoDTO();
        dto.setId(ativo.getId());
        dto.setNome(ativo.getNome());
        dto.setTipo(ativo.getTipo());
        dto.setNumeroSerie(ativo.getNumeroSerie());
        dto.setFabricante(ativo.getFabricante());
        dto.setModelo(ativo.getModelo());
        dto.setStatus(ativo.getStatus());
        dto.setDataAquisicao(ativo.getDataAquisicao());
        dto.setDataCriacao(ativo.getDataCriacao());
        dto.setDataAtualizacao(ativo.getDataAtualizacao());
        return dto;
    }

    public Ativo toEntity() {
        Ativo ativo = new Ativo();
        ativo.setId(this.id);
        ativo.setNome(this.nome);
        ativo.setTipo(this.tipo);
        ativo.setNumeroSerie(this.numeroSerie);
        ativo.setFabricante(this.fabricante);
        ativo.setModelo(this.modelo);
        ativo.setStatus(this.status);
        ativo.setDataAquisicao(this.dataAquisicao);
        return ativo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDateTime dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
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
}
