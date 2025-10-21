package com.haras.controller;

import com.haras.model.Equino;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EquinoController {
    private List<Equino> equinos;
    private int proximoId;
    
    public EquinoController() {
        this.equinos = new ArrayList<>();
        this.proximoId = 1;
        // Adicionar alguns dados de exemplo
        inicializarDadosExemplo();
    }
    
    private void inicializarDadosExemplo() {
        adicionarEquino(new Equino(0, "Thunder", "Puro Sangue", "Macho", "Disponível", 50000.0));
        adicionarEquino(new Equino(0, "Star", "Quarto de Milha", "Fêmea", "Treinamento", 35000.0));
        adicionarEquino(new Equino(0, "Lightning", "Árabe", "Macho", "Venda", 45000.0));
    }
    
    public boolean adicionarEquino(Equino equino) {
        if (equino != null) {
            equino.setId(proximoId++);
            equinos.add(equino);
            return true;
        }
        return false;
    }
    
    public boolean removerEquino(int id) {
        return equinos.removeIf(e -> e.getId() == id);
    }
    
    public Equino buscarEquino(int id) {
        return equinos.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
    
    public List<Equino> buscarPorNome(String nome) {
        return equinos.stream()
                .filter(e -> e.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Equino> buscarPorStatus(String status) {
        return equinos.stream()
                .filter(e -> e.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
    
    public List<Equino> listarEquinos() {
        return new ArrayList<>(equinos);
    }
    
    public boolean atualizarEquino(Equino equino) {
        for (int i = 0; i < equinos.size(); i++) {
            if (equinos.get(i).getId() == equino.getId()) {
                equinos.set(i, equino);
                return true;
            }
        }
        return false;
    }
    
    public int getTotalEquinos() {
        return equinos.size();
    }
}
