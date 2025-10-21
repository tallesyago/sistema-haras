package com.haras.model;

public class Veterinario {
    private int id;
    private String cpf;
    private String telefone;
    private String endereco;
    
    public Veterinario() {}
    
    public Veterinario(int id, String cpf, String telefone, String endereco) {
        this.id = id;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
    public String analisarConsulta(String equino, String descricao, String atendimento) { return ""; }
    public void realizarTratamento(String equino, String descricao) {}
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
