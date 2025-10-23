package com.haras.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Equino {
    private int id;
    private String nome;
    private String raca;
    private String genero;
    private String status;
    private double preco;
    private LocalDate dataNascimento;
    private int idade;
    private List<String> historicoMedico;
    private String proprietario;
    private String pedigree;
    private List<String> documentos;
    private String observacoes;

    public Equino() {
        this.historicoMedico = new ArrayList<>();
        this.documentos = new ArrayList<>();
    }
    
    public Equino(int id, String nome, String raca, String genero, String status, double preco) {
        this();
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

    // Métodos de negócio
    public void adicionarRegistroMedico(String registro) {
        if (registro != null && !registro.trim().isEmpty()) {
            historicoMedico.add(String.format("%s: %s", LocalDate.now(), registro));
        }
    }

    public void adicionarDocumento(String documento) {
        if (documento != null && !documento.trim().isEmpty()) {
            documentos.add(documento);
        }
    }

    public boolean isDisponivel() {
        return "Disponível".equalsIgnoreCase(status);
    }

    public boolean validarIdade() {
        return idade >= 0 && idade <= 40;
    }
    
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

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    
    public List<String> getHistoricoMedico() { return new ArrayList<>(historicoMedico); }
    public void setHistoricoMedico(List<String> historicoMedico) { 
        this.historicoMedico = historicoMedico != null ? new ArrayList<>(historicoMedico) : new ArrayList<>(); 
    }
    
    public String getProprietario() { return proprietario; }
    public void setProprietario(String proprietario) { this.proprietario = proprietario; }
    
    public String getPedigree() { return pedigree; }
    public void setPedigree(String pedigree) { this.pedigree = pedigree; }
    
    public List<String> getDocumentos() { return new ArrayList<>(documentos); }
    public void setDocumentos(List<String> documentos) { 
        this.documentos = documentos != null ? new ArrayList<>(documentos) : new ArrayList<>(); 
    }
    
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    @Override
    public String toString() {
        return String.format("Equino{id=%d, nome='%s', raca='%s', status='%s'}", id, nome, raca, status);
    }
}
