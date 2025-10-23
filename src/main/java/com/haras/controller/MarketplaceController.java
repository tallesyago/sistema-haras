package com.haras.controller;

import com.haras.model.Equino;
import com.haras.view.MarketplaceView;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MarketplaceController {
    private static MarketplaceController instance;
    private final List<Equino> equinos;
    private MarketplaceView marketplaceView;
    private boolean initialized = false;

    private MarketplaceController() {
        this.equinos = new ArrayList<>();
        initializeIfNeeded();
    }

    public static MarketplaceController getInstance() {
        if (instance == null) {
            instance = new MarketplaceController();
        }
        return instance;
    }

    private void initializeIfNeeded() {
        if (!initialized) {
            initialized = true;
            loadEquinos();
        }
    }

    private void loadEquinos() {
        equinos.clear();
        
        // Dados de exemplo - ajustar conforme a estrutura da model Equino
        Equino equino1 = new Equino();
        equino1.setNome("Thunder");
        equino1.setRaca("Puro Sangue Inglês");
        equino1.setIdade(5);
        equino1.setGenero("Macho");
        
        Equino equino2 = new Equino();
        equino2.setNome("Bella");
        equino2.setRaca("Quarto de Milha");
        equino2.setIdade(3);
        equino2.setGenero("Fêmea");

        Equino equino3 = new Equino();
        equino3.setNome("Apollo");
        equino3.setRaca("Lusitano");
        equino3.setIdade(7);
        equino3.setGenero("Macho");

        equinos.add(equino1);
        equinos.add(equino2);
        equinos.add(equino3);
        
        System.out.println("Equinos carregados: " + equinos.size());
    }

    public void initializeView() {
        try {
            if (marketplaceView == null) {
                System.out.println("Criando MarketplaceView...");
                marketplaceView = new MarketplaceView(this);
                System.out.println("MarketplaceView criada com sucesso");
            }
            marketplaceView.updateEquinos(equinos);
            System.out.println("View atualizada com " + equinos.size() + " equinos");
        } catch (Exception e) {
            System.err.println("Erro ao inicializar view: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void refreshView() {
        if (marketplaceView != null) {
            SwingUtilities.invokeLater(() -> {
                try {
                    marketplaceView.updateEquinos(equinos);
                } catch (Exception e) {
                    System.err.println("Erro ao atualizar view: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }

    public JPanel getView() {
        try {
            if (marketplaceView == null) {
                System.out.println("View nula, inicializando...");
                initializeView();
            }
            
            if (marketplaceView != null) {
                JPanel panel = marketplaceView.getContentPanel();
                if (panel != null) {
                    System.out.println("Retornando panel do marketplace");
                    return panel;
                } else {
                    System.err.println("getContentPanel() retornou null!");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter view: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.err.println("Retornando JPanel vazio como fallback");
        JPanel fallbackPanel = new JPanel();
        fallbackPanel.add(new JLabel("Erro ao carregar Marketplace"));
        return fallbackPanel;
    }

    public List<Equino> getEquinos() {
        return new ArrayList<>(equinos);
    }

    public List<Equino> filtrarEquinos(String searchText, String faixaPreco) {
        List<Equino> resultado = new ArrayList<>(equinos);

        // Filtrar por texto
        if (searchText != null && !searchText.trim().isEmpty()) {
            String search = searchText.toLowerCase();
            resultado = resultado.stream()
                .filter(e -> e.getNome().toLowerCase().contains(search) ||
                           e.getRaca().toLowerCase().contains(search))
                .collect(Collectors.toList());
        }

        return resultado;
    }

    public void visualizarDetalhes(Equino equino) {
        String detalhes = String.format(
            "Nome: %s\nRaça: %s\nIdade: %d anos\nSexo: %s",
            equino.getNome(),
            equino.getRaca(),
            equino.getIdade(),
            equino.getGenero()
        );

        JOptionPane.showMessageDialog(null,
            detalhes,
            "Detalhes do Equino",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void entrarEmContato(Equino equino) {
        JOptionPane.showMessageDialog(null,
            String.format("Entre em contato para negociar %s", equino.getNome()),
            "Contato",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
