package br.com.fiap.sentineltrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_ATIVO")
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ATIVO")
    @SequenceGenerator(name = "SEQ_ATIVO", sequenceName = "SEQ_ATIVO", allocationSize = 1)
    @Column(name = "ID_ATIVO")
    private Long id;

    @NotBlank(message = "O nome do ativo é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do ativo deve ter entre 3 e 100 caracteres")
    @Column(name = "NM_ATIVO", nullable = false, length = 100)
    private String nome;

    @Column(name = "DS_TIPO", length = 50)
    private String tipo;

    @Column(name = "DS_NUMERO_SERIE", length = 50)
    private String numeroSerie;

    @Column(name = "DS_FABRICANTE", length = 100)
    private String fabricante;

    @Column(name = "DS_MODELO", length = 100)
    private String modelo;

    @Column(name = "DS_STATUS", length = 20)
    private String status;

    @Column(name = "DT_AQUISICAO")
    private LocalDateTime dataAquisicao;

    @Column(name = "DT_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DT_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @ManyToMany(mappedBy = "ativos")
    private Set<Incidente> incidentes = new HashSet<>();

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDateTime.now();
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

    public Set<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(Set<Incidente> incidentes) {
        this.incidentes = incidentes;
    }
}
