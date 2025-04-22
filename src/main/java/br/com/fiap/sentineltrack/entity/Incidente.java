package br.com.fiap.sentineltrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TB_INCIDENTE")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INCIDENTE")
    @SequenceGenerator(name = "SEQ_INCIDENTE", sequenceName = "SEQ_INCIDENTE", allocationSize = 1)
    @Column(name = "ID_INCIDENTE")
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    @Column(name = "DS_TITULO", nullable = false, length = 100)
    private String titulo;

    @Column(name = "DS_DESCRICAO", length = 4000)
    private String descricao;

    @NotNull(message = "A severidade é obrigatória")
    @Column(name = "NR_SEVERIDADE", nullable = false)
    private Integer severidade;

    @Column(name = "DS_STATUS", length = 20)
    private String status;

    @Column(name = "DS_TIPO", length = 50)
    private String tipo;

    @Column(name = "DT_OCORRENCIA")
    private LocalDateTime dataOcorrencia;

    @Column(name = "DT_RESOLUCAO")
    private LocalDateTime dataResolucao;

    @Column(name = "DT_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DT_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LOCAL", nullable = false)
    private Local local;

    @ManyToMany
    @JoinTable(
            name = "TB_INCIDENTE_ATIVO",
            joinColumns = @JoinColumn(name = "ID_INCIDENTE"),
            inverseJoinColumns = @JoinColumn(name = "ID_ATIVO")
    )
    private Set<Ativo> ativos = new HashSet<>();

    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alerta> alertas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
        if (dataOcorrencia == null) {
            dataOcorrencia = LocalDateTime.now();
        }
        if (status == null) {
            status = "ABERTO";
        }
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    public void adicionarAtivo(Ativo ativo) {
        ativos.add(ativo);
        ativo.getIncidentes().add(this);
    }

    public void removerAtivo(Ativo ativo) {
        ativos.remove(ativo);
        ativo.getIncidentes().remove(this);
    }

    public void adicionarAlerta(Alerta alerta) {
        alertas.add(alerta);
        alerta.setIncidente(this);
    }

    public void removerAlerta(Alerta alerta) {
        alertas.remove(alerta);
        alerta.setIncidente(null);
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Set<Ativo> getAtivos() {
        return ativos;
    }

    public void setAtivos(Set<Ativo> ativos) {
        this.ativos = ativos;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }
}
