package com.haras.view;

import com.haras.controller.MarketplaceController;
import com.haras.model.Equino;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MarketplaceView {
    private JPanel contentPanel;
    private JPanel equiposPanel;
    private JTextField searchField;
    private JComboBox<String> filtroPreco;
    private MarketplaceController controller;

    public MarketplaceView(MarketplaceController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header com busca e filtros
        JPanel headerPanel = createHeaderPanel();
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // Painel de equinos
        equiposPanel = new JPanel();
        equiposPanel.setLayout(new BoxLayout(equiposPanel, BoxLayout.Y_AXIS));
        equiposPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(equiposPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBackground(new Color(245, 245, 245));

        // Campo de busca
        JPanel searchPanel = new JPanel(new BorderLayout(5, 0));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(8, 12, 8, 12)
        ));

        searchField = new JTextField();
        searchField.setBorder(BorderFactory.createEmptyBorder());
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel searchIcon = new JLabel("🔍");
        searchPanel.add(searchIcon, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);

        // Filtro de preço
        String[] faixasPreco = {
            "Todas as Faixas de Preço",
            "Até R$ 50.000",
            "R$ 50.000 - R$ 100.000",
            "Acima de R$ 100.000"
        };
        
        filtroPreco = new JComboBox<>(faixasPreco);
        filtroPreco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        filtroPreco.setBackground(Color.WHITE);

        headerPanel.add(searchPanel, BorderLayout.CENTER);
        headerPanel.add(filtroPreco, BorderLayout.EAST);

        // Listeners
        searchField.addActionListener(e -> aplicarFiltros());
        filtroPreco.addActionListener(e -> aplicarFiltros());

        return headerPanel;
    }

    private void aplicarFiltros() {
        String searchText = searchField.getText();
        String faixa = (String) filtroPreco.getSelectedItem();
        List<Equino> equiposFiltrados = controller.filtrarEquinos(searchText, faixa);
        atualizarEquinos(equiposFiltrados);
    }

    public void atualizarEquinos(List<Equino> equinos) {
        equiposPanel.removeAll();

        if (equinos.isEmpty()) {
            mostrarEstadoVazio();
        } else {
            for (Equino equino : equinos) {
                equiposPanel.add(createEquinoCard(equino));
                equiposPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        equiposPanel.revalidate();
        equiposPanel.repaint();
    }

    private void mostrarEstadoVazio() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
        emptyPanel.setBackground(Color.WHITE);
        emptyPanel.setBorder(new EmptyBorder(50, 20, 50, 20));

        JLabel emptyIcon = new JLabel("🛒", SwingConstants.CENTER);
        emptyIcon.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        emptyIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emptyLabel = new JLabel("Nenhum equino disponível no momento");
        emptyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emptySubLabel = new JLabel("Volte em breve para ver novos equinos à venda");
        emptySubLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emptySubLabel.setForeground(new Color(108, 117, 125));
        emptySubLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emptyPanel.add(Box.createVerticalGlue());
        emptyPanel.add(emptyIcon);
        emptyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        emptyPanel.add(emptyLabel);
        emptyPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        emptyPanel.add(emptySubLabel);
        emptyPanel.add(Box.createVerticalGlue());

        equiposPanel.add(emptyPanel);
    }

    private JPanel createEquinoCard(Equino equino) {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            new EmptyBorder(15, 15, 15, 15)
        ));

        // Imagem placeholder
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(120, 120));
        imagePanel.setBackground(new Color(230, 230, 230));
        imagePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        JLabel imageLabel = new JLabel("🐴", SwingConstants.CENTER);
        imageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        imagePanel.add(imageLabel);

        // Informações
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nomeLabel = new JLabel(equino.getNome());
        nomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel racaLabel = new JLabel(equino.getRaca());
        racaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        racaLabel.setForeground(new Color(108, 117, 125));

        JLabel detalhesLabel = new JLabel(String.format("%d anos • %s", 
            equino.getIdade(), equino.getGenero()));
        detalhesLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        detalhesLabel.setForeground(new Color(108, 117, 125));

        infoPanel.add(nomeLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(racaLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        infoPanel.add(detalhesLabel);

        // Botões
        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new BoxLayout(botoesPanel, BoxLayout.Y_AXIS));
        botoesPanel.setBackground(Color.WHITE);

        JButton detalhesButton = new JButton("Ver Detalhes");
        detalhesButton.setBackground(new Color(0, 133, 82));
        detalhesButton.setForeground(Color.WHITE);
        detalhesButton.setFocusPainted(false);
        detalhesButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        detalhesButton.addActionListener(e -> controller.visualizarDetalhes(equino));

        JButton contatoButton = new JButton("Entrar em Contato");
        contatoButton.setBackground(Color.WHITE);
        contatoButton.setForeground(new Color(0, 133, 82));
        contatoButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 133, 82)),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        contatoButton.setFocusPainted(false);
        contatoButton.addActionListener(e -> controller.entrarEmContato(equino));

        botoesPanel.add(detalhesButton);
        botoesPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        botoesPanel.add(contatoButton);

        card.add(imagePanel, BorderLayout.WEST);
        card.add(infoPanel, BorderLayout.CENTER);
        card.add(botoesPanel, BorderLayout.EAST);

        return card;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void updateEquinos(List<Equino> equinos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEquinos'");
    }
}
