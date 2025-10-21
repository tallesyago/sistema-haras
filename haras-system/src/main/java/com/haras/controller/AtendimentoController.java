package com.haras.controller;

import com.haras.model.Atendimento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AtendimentoController {
    private List<Atendimento> atendimentos;
    private int proximoId;
    
    public AtendimentoController() {
        this.atendimentos = new ArrayList<>();
        this.proximoId = 1;
        inicializarDadosExemplo();
    }
    
    private void inicializarDadosExemplo() {
        Atendimento at1 = new Atendimento();
        at1.setId(proximoId++);
        at1.setDataAbertura(new Date());
        at1.setDescricao("Consulta de rotina");
        at1.setTipo("Consulta");
        at1.setStatus("Aberto");
        atendimentos.add(at1);
    }
    
    public boolean adicionarAtendimento(Atendimento atendimento) {
        if (atendimento != null) {
            atendimento.setId(proximoId++);
            atendimentos.add(atendimento);
            return true;
        }
        return false;
    }
    
    public boolean removerAtendimento(int id) {
        return atendimentos.removeIf(a -> a.getId() == id);
    }
    
    public Atendimento buscarAtendimento(int id) {
        return atendimentos.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }
    
    public List<Atendimento> listarAtendimentos() {
        return new ArrayList<>(atendimentos);
    }
    
    public boolean atualizarAtendimento(Atendimento atendimento) {
        for (int i = 0; i < atendimentos.size(); i++) {
            if (atendimentos.get(i).getId() == atendimento.getId()) {
                atendimentos.set(i, atendimento);
                return true;
            }
        }
        return false;
    }
    
    public int getTotalAtendimentos() {
        return atendimentos.size();
    }
}
