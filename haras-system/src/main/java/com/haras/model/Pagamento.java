package com.haras.model;

public class Pagamento {
    private int id;
    private double valor;
    private String metodo;
    private String status;
    
    public Pagamento() {}
    
    public void realizarPagamento() {}
    public void gerarComprovante() {}
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
