package com.haras.model;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String perfil;
    
    public Usuario() {}
    
    public Usuario(int id, String nome, String cpf, String senha, String perfil) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.perfil = perfil;
    }
    
    public boolean logarEmail(String senha, boolean autenticado) { return autenticado; }
    public void logout() {}
    public String acessarDashboard() { return "dashboard"; }
    public void alterarSenha(String novaSenha) { this.senha = novaSenha; }
    
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
}
