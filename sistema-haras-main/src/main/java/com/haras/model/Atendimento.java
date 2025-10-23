package com.haras.model;

import java.util.Date;

public class Atendimento {
    private int id;
    private Date dataAbertura;
    private Date dataFechamento;
    private String descricao;
    private String tipo;
    private String status;
    private String feedbackCliente;
    
    public Atendimento() {}
    
    public void registrarAtendimento(String cliente, String equino) {}
    public void gerarPresuposto(String feedback) {}
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(Date dataAbertura) { this.dataAbertura = dataAbertura; }
    public Date getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(Date dataFechamento) { this.dataFechamento = dataFechamento; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getFeedbackCliente() { return feedbackCliente; }
    public void setFeedbackCliente(String feedbackCliente) { this.feedbackCliente = feedbackCliente; }
}
