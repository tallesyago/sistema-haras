package com.haras.model;

public class Administrador {
    private int id;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    
    public Administrador() {}
    
    public Administrador(int id, String cpf, String telefone, String email, String endereco) {
        this.id = id;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }
    
    public void aprovarCompraVenda() {}
    public String emitirRelatorio() { return ""; }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
