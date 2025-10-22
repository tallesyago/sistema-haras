package com.haras.view.pages;

import javax.swing.*;
import java.awt.*;

public class VeterinaryDashboardView {
    
    public JPanel getContentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 250, 252));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Header verde com gradiente
        JPanel headerPanel = createWelcomeHeader();
        
        // Cards de estatísticas
        JPanel statsPanel = createStatsPanel();
        
        // Seções inferiores
        JPanel bottomPanel = createBottomSections();
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(statsPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createWelcomeHeader() {
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Gradiente verde médico
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(16, 185, 129),
                    getWidth(), getHeight(), new Color(5, 150, 105)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 120));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        headerPanel.setOpaque(false);

        // Lado esquerdo - Texto de boas-vindas
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Boa tarde, Dr. Veterinário!");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("Painel Veterinário - Cuidados e Saúde Animal");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));

        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(subtitleLabel);

        // Lado direito - Botão Nova Consulta
        JButton novaConsultaBtn = new JButton("Nova Consulta");
        novaConsultaBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        novaConsultaBtn.setForeground(new Color(16, 185, 129));
        novaConsultaBtn.setBackground(Color.WHITE);
        novaConsultaBtn.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        novaConsultaBtn.setFocusPainted(false);
        novaConsultaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Delegar a ação para o controller (padrão MVC)
        novaConsultaBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    com.haras.controller.VeterinaryController.getInstance().createNewConsultationDialog(novaConsultaBtn);
                } catch (Exception ex) {
                    // Em caso de erro, mostrar mensagem simples (NavigationController trata erros globais)
                    javax.swing.JOptionPane.showMessageDialog(novaConsultaBtn, "Erro: " + ex.getMessage(), "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(novaConsultaBtn, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        statsPanel.setBackground(new Color(248, 250, 252));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        statsPanel.add(createStatCard("Consultas de Hoje", "0", "Agendadas", new Color(59, 130, 246)));
        statsPanel.add(createStatCard("Próximas Consultas", "0", "Agendadas", new Color(16, 185, 129)));
        statsPanel.add(createStatCard("Pacientes Ativos", "0", "Em tratamento", new Color(245, 158, 11)));
        statsPanel.add(createStatCard("Consultas Concluídas", "0", "Histórico", new Color(147, 51, 234)));
        
        return statsPanel;
    }
    
    private JPanel createStatCard(String title, String value, String subtitle, Color themeColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        card.setPreferredSize(new Dimension(180, 70));
        card.setMaximumSize(new Dimension(180, 70));
        
        // Layout principal
        JPanel mainLayout = new JPanel(new BorderLayout());
        mainLayout.setBackground(Color.WHITE);
        
        // Título no topo à esquerda
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        titleLabel.setForeground(new Color(107, 114, 128));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        // Container inferior com valor
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        
        // Lado esquerdo - Valor e subtítulo
        JPanel leftContent = new JPanel();
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));
        leftContent.setBackground(Color.WHITE);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        valueLabel.setForeground(new Color(17, 24, 39));
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("↗ " + subtitle);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        subtitleLabel.setForeground(new Color(34, 197, 94));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        leftContent.add(valueLabel);
        leftContent.add(subtitleLabel);
        
        bottomPanel.add(leftContent, BorderLayout.WEST);
        
        mainLayout.add(titleLabel, BorderLayout.NORTH);
        mainLayout.add(Box.createVerticalStrut(3));
        mainLayout.add(bottomPanel, BorderLayout.SOUTH);
        
        card.add(mainLayout, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createBottomSections() {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        bottomPanel.setBackground(new Color(248, 250, 252));
        bottomPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 300));
        
        // Seção Consultas de Hoje
        JPanel consultasSection = createSection("📅", "Consultas de Hoje", "Ver Todos os Pacientes →", 
            "Nenhuma consulta agendada para hoje");
        
        // Seção Próximas Consultas
        JPanel proximasSection = createSection("🕒", "Próximas Consultas", "", 
            "Nenhuma consulta agendada");
        
        bottomPanel.add(consultasSection);
        bottomPanel.add(proximasSection);
        
        return bottomPanel;
    }
    
    private JPanel createSection(String icon, String title, String link, String emptyMessage) {
        JPanel section = new JPanel(new BorderLayout());
        section.setBackground(Color.WHITE);
        section.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        
        // Header da seção
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(17, 24, 39));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        titlePanel.add(iconLabel);
        titlePanel.add(titleLabel);
        
        if (!link.isEmpty()) {
            JLabel linkLabel = new JLabel(link);
            linkLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            linkLabel.setForeground(new Color(16, 185, 129));
            linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            headerPanel.add(titlePanel, BorderLayout.WEST);
            headerPanel.add(linkLabel, BorderLayout.EAST);
        } else {
            headerPanel.add(titlePanel, BorderLayout.WEST);
        }
        
        // Conteúdo vazio com ícone
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
        emptyPanel.setBackground(Color.WHITE);
        
        JLabel emptyIconLabel = new JLabel(icon);
        emptyIconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        emptyIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel emptyLabel = new JLabel(emptyMessage);
        emptyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emptyLabel.setForeground(new Color(156, 163, 175));
        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        emptyPanel.add(Box.createVerticalGlue());
        emptyPanel.add(emptyIconLabel);
        emptyPanel.add(Box.createVerticalStrut(10));
        emptyPanel.add(emptyLabel);
        emptyPanel.add(Box.createVerticalGlue());
        
        section.add(headerPanel, BorderLayout.NORTH);
        section.add(emptyPanel, BorderLayout.CENTER);
        
        return section;
    }
}