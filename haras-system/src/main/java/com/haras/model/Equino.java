package com.haras.model;

public class Equino {
    private int id;
    private String nome;
    private String raca;
    private String genero;
    private String status;
    private double preco;
    
    public Equino() {}
    
    public Equino(int id, String nome, String raca, String genero, String status, double preco) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.genero = genero;
        this.status = status;
        this.preco = preco;
    }
    
    public void atualizarStatus(String status) { this.status = status; }
    public void colocarAVenda() { this.status = "Venda"; }
    public void alocarParaEvento(String evento) {}
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
}
