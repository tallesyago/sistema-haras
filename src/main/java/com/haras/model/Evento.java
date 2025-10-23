package com.haras.model;

import java.time.LocalDateTime;

/**
 * Representa um evento no sistema, como consultas veterinárias,
 * treinamentos, competições, etc.
 */
public class Evento {
    private String id;
    private String titulo;
    private String tipo;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraTermino;
    private String status;
    private String descricao;
    private String local;
    private String observacoes;
    private String responsavel;
    private Equino equinoRelacionado;

    public Evento(String id, String titulo, String tipo, LocalDateTime dataHoraInicio, String status) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.dataHoraInicio = dataHoraInicio;
        this.status = status;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getDataHora() { return dataHoraInicio; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHoraInicio = dataHora; }

    public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) { this.dataHoraInicio = dataHoraInicio; }

    public LocalDateTime getDataHoraTermino() { return dataHoraTermino; }
    public void setDataHoraTermino(LocalDateTime dataHoraTermino) { this.dataHoraTermino = dataHoraTermino; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public Equino getEquinoRelacionado() { return equinoRelacionado; }
    public void setEquinoRelacionado(Equino equinoRelacionado) { this.equinoRelacionado = equinoRelacionado; }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", titulo, tipo, status);
    }
}