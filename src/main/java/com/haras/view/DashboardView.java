package com.haras.view;

import com.haras.controller.DashboardController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardView {
    private JPanel contentPanel;
    @SuppressWarnings("unused")
    private DashboardController controller;
    
    // Labels para atualizar os valores
    private JLabel totalCavalosLabel;
    private JLabel cavalosAtivosLabel;
    private JLabel pendenciasLabel;
    private JLabel eventosHojeLabel;
    private JLabel emTratamentoLabel;
    
    // Construtor sem parâmetros
    public DashboardView() {
        this.controller = null;
        initialize();
    }
    
    public DashboardView(DashboardController controller) {
        this.controller = controller;
        initialize();
    }
    
    private void initialize() {
        contentPanel = new JPanel(new BorderLayout(0, 20));
        contentPanel.setBackground(new Color(248, 250, 252));
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        // Header
        JPanel headerPanel = createHeaderPanel();
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Cards Container
        JPanel cardsContainer = new JPanel(new GridLayout(1, 4, 20, 20));
        cardsContainer.setBackground(new Color(248, 250, 252));
        
        // Criar cards com referências aos labels
        cardsContainer.add(createStatCard("Total de Cavalos", "0", "0 ativos", 
            "👥", new Color(251, 191, 36), "totalCavalos"));
        cardsContainer.add(createStatCard("Pendências", "0", "Requer atenção", 
            "⚠️", new Color(239, 68, 68), "pendencias"));
        cardsContainer.add(createStatCard("Eventos Hoje", "0", "Agenda do dia", 
            "📅", new Color(16, 185, 129), "eventosHoje"));
        cardsContainer.add(createStatCard("Em Tratamento", "0", "Acompanhamento ativo", 
            "🔄", new Color(59, 130, 246), "emTratamento"));
        
        contentPanel.add(cardsContainer, BorderLayout.CENTER);
        
        // Seção de informações adicionais
        JPanel bottomSection = createBottomSection();
        contentPanel.add(bottomSection, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(248, 250, 252));
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(248, 250, 252));
        
        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(31, 41, 55));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Visão geral do sistema");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(107, 114, 128));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);
        
        headerPanel.add(titlePanel, BorderLayout.WEST);
        
        return headerPanel;
    }
    
    private JPanel createStatCard(String title, String value, String subtitle, 
                                   String icon, Color iconBgColor, String cardType) {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        // Ícone
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.Y_AXIS));
        iconPanel.setBackground(iconBgColor);
        iconPanel.setPreferredSize(new Dimension(60,60));
        iconPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconPanel.add(Box.createVerticalGlue());
        iconPanel.add(iconLabel);
        iconPanel.add(Box.createVerticalGlue());
        
        // Conteúdo
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(new Color(107, 114, 128));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        valueLabel.setForeground(new Color(31, 41, 55));
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(16, 185, 129));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Guardar referências aos labels para atualização
        switch (cardType) {
            case "totalCavalos":
                totalCavalosLabel = valueLabel;
                cavalosAtivosLabel = subtitleLabel;
                break;
            case "pendencias":
                pendenciasLabel = valueLabel;
                break;
            case "eventosHoje":
                eventosHojeLabel = valueLabel;
                break;
            case "emTratamento":
                emTratamentoLabel = valueLabel;
                break;
        }
        
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(valueLabel);
        contentPanel.add(Box.createVerticalStrut(4));
        contentPanel.add(subtitleLabel);
        
        card.add(iconPanel, BorderLayout.WEST);
        card.add(contentPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createBottomSection() {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setBackground(new Color(248, 250, 252));
        bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        // Aprovações Pendentes
        bottomPanel.add(createInfoCard(
            "⚠️ Aprovações Pendentes",
            "✓",
            "Todas as aprovações estão em dia!",
            new Color(16, 185, 129)
        ));
        
        // Eventos de Hoje
        bottomPanel.add(createInfoCard(
            "📅 Eventos de Hoje",
            "📅",
            "Nenhum evento agendado para hoje",
            new Color(209, 213, 219)
        ));
        
        return bottomPanel;
    }
    
    private JPanel createInfoCard(String title, String icon, String message, Color iconColor) {
        JPanel card = new JPanel(new BorderLayout(0, 15));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        // Header
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(31, 41, 55));
        
        // Content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(40, 20, 40, 20));
        
        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        iconLabel.setForeground(iconColor);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(107, 114, 128));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentPanel.add(iconLabel);
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(messageLabel);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(contentPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    public JPanel getContentPanel() {
        return contentPanel;
    }
    
    public void atualizarDados(int totalCavalos, int cavalosAtivos, int pendencias, 
                               int eventosHoje, int emTratamento, int consultasRecentes) {
        SwingUtilities.invokeLater(() -> {
            if (totalCavalosLabel != null) {
                totalCavalosLabel.setText(String.valueOf(totalCavalos));
            }
            if (cavalosAtivosLabel != null) {
                cavalosAtivosLabel.setText(cavalosAtivos + " ativos");
            }
            if (pendenciasLabel != null) {
                pendenciasLabel.setText(String.valueOf(pendencias));
            }
            if (eventosHojeLabel != null) {
                eventosHojeLabel.setText(String.valueOf(eventosHoje));
            }
            if (emTratamentoLabel != null) {
                emTratamentoLabel.setText(String.valueOf(emTratamento));
            }
            
            contentPanel.revalidate();
            contentPanel.repaint();
        });
    }
}
