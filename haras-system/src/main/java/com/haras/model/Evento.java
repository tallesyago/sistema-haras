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
    private LocalDateTime dataHora;
    private String status;
    
    public Evento(String id, String titulo, String tipo, LocalDateTime dataHora, String status) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.dataHora = dataHora;
        this.status = status;
    }
    
    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}