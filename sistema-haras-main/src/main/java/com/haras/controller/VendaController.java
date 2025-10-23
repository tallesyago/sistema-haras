package com.haras.controller;

import com.haras.model.Venda;
import com.haras.model.Equino;
import com.haras.model.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsável pelo gerenciamento de vendas de equinos
 */
public class VendaController {
    private static VendaController instance;
    private final List<Venda> vendas;
    
    private VendaController() {
        this.vendas = new ArrayList<>();
    }
    
    public static VendaController getInstance() {
        if (instance == null) {
            instance = new VendaController();
        }
        return instance;
    }
    
    /**
     * Inicia uma nova venda de equino
     */
    public Venda iniciarVenda(Equino equino, Cliente cliente) {
        Venda venda = new Venda();
        venda.setEquino(equino);
        venda.setCliente(cliente);
        venda.setData(java.time.LocalDate.now());
        venda.setStatus("Pendente");
        vendas.add(venda);
        return venda;
    }
    
    /**
     * Confirma uma venda
     */
    public boolean confirmarVenda(Venda venda) {
        if (venda != null && "Pendente".equals(venda.getStatus())) {
            venda.setStatus("Confirmada");
            // TODO: Implementar lógica adicional de confirmação
            return true;
        }
        return false;
    }
    
    /**
     * Cancela uma venda
     */
    public boolean cancelarVenda(Venda venda) {
        if (venda != null && "Pendente".equals(venda.getStatus())) {
            venda.setStatus("Cancelada");
            vendas.remove(venda);
            return true;
        }
        return false;
    }
    
    /**
     * Lista todas as vendas
     */
    public List<Venda> listarVendas() {
        return new ArrayList<>(vendas);
    }
    
    /**
     * Lista vendas por cliente
     */
    public List<Venda> listarVendasPorCliente(Cliente cliente) {
        return vendas.stream()
            .filter(v -> v.getCliente().equals(cliente))
            .collect(java.util.stream.Collectors.toList());
    }
}