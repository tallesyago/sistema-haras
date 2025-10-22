package com.haras.model;

import java.time.LocalDateTime;

public class Pagamento {
    private int id;
    private Venda venda;
    private double valor;
    private String metodo;
    private String status;
    private LocalDateTime dataHora;
    
    public Pagamento() {
        this.dataHora = LocalDateTime.now();
    }
    
    // Métodos de negócio
    public void realizarPagamento() {
        // Implementar lógica de pagamento
    }
    
    public void gerarComprovante() {
        // Implementar geração de comprovante
    }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Venda getVenda() { return venda; }
    public void setVenda(Venda venda) { this.venda = venda; }
    
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}
