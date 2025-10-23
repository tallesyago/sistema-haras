package com.haras.model;

import java.time.LocalDate;

/**
 * Representa uma venda de equino no sistema
 */
public class Venda {
    private int id;
    private LocalDate data;
    private String status;
    private double valor;
    private Cliente cliente;
    private Equino equino;
    
    public Venda() {
        this.data = LocalDate.now();
        this.status = "Pendente";
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Equino getEquino() {
        return equino;
    }
    
    public void setEquino(Equino equino) {
        this.equino = equino;
    }
}