package br.com.fiap.sentineltrack.dto;

import br.com.fiap.sentineltrack.entity.Local;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class LocalDTO {

    private Long id;

    @NotBlank(message = "O nome do local é obrigatório")
    @Size(min = 3, max = 100, message = "O nome do local deve ter entre 3 e 100 caracteres")
    private String nome;

    private String endereco;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    private Double latitude;
    private Double longitude;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public LocalDTO() {
    }

    public LocalDTO(Long id, String nome, String endereco, String cidade, String estado, String pais, String cep, Double latitude, Double longitude, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.cep = cep;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public static LocalDTO fromEntity(Local local) {
        LocalDTO dto = new LocalDTO();
        dto.setId(local.getId());
        dto.setNome(local.getNome());
        dto.setEndereco(local.getEndereco());
        dto.setCidade(local.getCidade());
        dto.setEstado(local.getEstado());
        dto.setPais(local.getPais());
        dto.setCep(local.getCep());
        dto.setLatitude(local.getLatitude());
        dto.setLongitude(local.getLongitude());
        dto.setDataCriacao(local.getDataCriacao());
        dto.setDataAtualizacao(local.getDataAtualizacao());
        return dto;
    }

    public Local toEntity() {
        Local local = new Local();
        local.setId(this.id);
        local.setNome(this.nome);
        local.setEndereco(this.endereco);
        local.setCidade(this.cidade);
        local.setEstado(this.estado);
        local.setPais(this.pais);
        local.setCep(this.cep);
        local.setLatitude(this.latitude);
        local.setLongitude(this.longitude);
        return local;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
