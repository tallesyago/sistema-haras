package com.haras.controller;

import com.haras.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteController {
    private static ClienteController instance;
    private List<Cliente> clientes;
    private int proximoId;
    
    private ClienteController() {
        this.clientes = new ArrayList<>();
        this.proximoId = 1;
        inicializarDadosExemplo();
    }
    
    public static ClienteController getInstance() {
        if (instance == null) {
            instance = new ClienteController();
        }
        return instance;
    }
    
    private void inicializarDadosExemplo() {
        adicionarCliente(new Cliente(0, "João Silva", "(11) 99999-9999", "joao@email.com", "Rua A, 123"));
        adicionarCliente(new Cliente(0, "Maria Santos", "(11) 88888-8888", "maria@email.com", "Rua B, 456"));
    }
    
    public boolean adicionarCliente(Cliente cliente) {
        if (cliente != null) {
            cliente.setId(proximoId++);
            clientes.add(cliente);
            return true;
        }
        return false;
    }
    
    public boolean removerCliente(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }
    
    public Cliente buscarCliente(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
    
    public List<Cliente> buscarPorNome(String nome) {
        return clientes.stream()
                .filter(c -> c.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }
    
    public boolean atualizarCliente(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                return true;
            }
        }
        return false;
    }
    
    public int getTotalClientes() {
        return clientes.size();
    }
}
