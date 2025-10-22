package com.haras.controller;

import com.haras.view.pages.MarketplaceView;
import com.haras.model.ItemMarketplace;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MarketplaceController {
    private static MarketplaceController instance;
    private MarketplaceView view;
    private List<ItemMarketplace> items;
    
    private MarketplaceController() {
        this.view = new MarketplaceView();
        this.items = new ArrayList<>();
        initializeActions();
        inicializarDadosExemplo();
    }
    
    public static MarketplaceController getInstance() {
        if (instance == null) {
            instance = new MarketplaceController();
        }
        return instance;
    }
    
    private void initializeActions() {
        // Implementar ações do marketplace
    }
    
    public JPanel getView() {
        return view.getContentPanel();
    }
    
    private void inicializarDadosExemplo() {
        // Adicionar alguns itens de exemplo
        items.add(new ItemMarketplace(
            UUID.randomUUID().toString(),
            "Cavalo Puro Sangue Inglês",
            "Cavalo",
            "Excelente exemplar de PSI, 5 anos",
            50000.0,
            "1"
        ));
        
        items.add(new ItemMarketplace(
            UUID.randomUUID().toString(),
            "Sela Profissional",
            "Equipamento",
            "Sela em couro legítimo",
            2500.0,
            "2"
        ));
    }
    
    public void addItemForSale() {
        String[] itemTypes = {"Cavalo", "Equipamento", "Serviço"};
        String type = (String) JOptionPane.showInputDialog(null, 
            "Tipo de Item:", "Adicionar Item", 
            JOptionPane.QUESTION_MESSAGE, null, itemTypes, itemTypes[0]);
        
        if (type != null) {
            String title = JOptionPane.showInputDialog(null, 
                "Título do Anúncio:", "Adicionar Item", JOptionPane.QUESTION_MESSAGE);
            
            if (title != null && !title.trim().isEmpty()) {
                String descricao = JOptionPane.showInputDialog(null, 
                    "Descrição do Item:", "Adicionar Item", JOptionPane.QUESTION_MESSAGE);
                    
                String precoStr = JOptionPane.showInputDialog(null, 
                    "Preço do Item:", "Adicionar Item", JOptionPane.QUESTION_MESSAGE);
                    
                try {
                    double preco = Double.parseDouble(precoStr);
                    ItemMarketplace novoItem = new ItemMarketplace(
                        UUID.randomUUID().toString(),
                        title.trim(),
                        type,
                        descricao,
                        preco,
                        "1" // ID do vendedor atual (pode ser obtido do usuário logado)
                    );
                    
                    items.add(novoItem);
                    JOptionPane.showMessageDialog(null, 
                        "Item '" + title + "' adicionado com sucesso!", 
                        "Sucesso", 
                        JOptionPane.INFORMATION_MESSAGE);
                    refreshMarketplace();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, 
                        "Preço inválido!", 
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public List<ItemMarketplace> searchItems(String query) {
        final String searchQuery = query.toLowerCase();
        return items.stream()
            .filter(item -> 
                item.getTitulo().toLowerCase().contains(searchQuery) ||
                item.getDescricao().toLowerCase().contains(searchQuery))
            .collect(Collectors.toList());
    }
    
    public List<ItemMarketplace> filterByCategory(String category) {
        return items.stream()
            .filter(item -> item.getTipo().equalsIgnoreCase(category))
            .collect(Collectors.toList());
    }
    
    public ItemMarketplace getItemDetails(String itemId) {
        return items.stream()
            .filter(item -> item.getId().equals(itemId))
            .findFirst()
            .orElse(null);
    }
    
    public boolean removeItem(String itemId) {
        return items.removeIf(item -> item.getId().equals(itemId));
    }
    
    public List<ItemMarketplace> getAllItems() {
        return new ArrayList<>(items);
    }
    
    public void refreshMarketplace() {
        if (view != null) {
            view.getContentPanel().revalidate();
            view.getContentPanel().repaint();
        }
    }
}
