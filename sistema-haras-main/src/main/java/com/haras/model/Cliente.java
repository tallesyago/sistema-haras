package com.haras.model;

import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    
    public Cliente() {}
    
    public Cliente(int id, String nome, String telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }
    
    public void atualizarDados() {}
    public void solicitarEvento(String evento) {}
    public void consultarAnimais(List<String> animais) {}
    public void remitirPagamento(List<String> atendimentos) {}
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
