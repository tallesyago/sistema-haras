package com.haras.view.pages;

import javax.swing.*;
import java.awt.*;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

public class DashboardView {
    
    public JPanel getContentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 250, 252));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Header roxo com gradiente
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
                
                // Gradiente roxo
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(147, 51, 234),
                    getWidth(), getHeight(), new Color(120, 40, 200)
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

        JLabel titleLabel = new JLabel("Bom dia, Cliente!");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("Bem-vindo ao seu painel de gerenciamento");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));

        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(subtitleLabel);

        // Lado direito - Ícone decorativo
        FontIcon decorativeIcon = FontIcon.of(FontAwesomeSolid.HORSE_HEAD, 64, new Color(255, 255, 255, 100));
        JLabel decorativeLabel = new JLabel();
        decorativeLabel.setIcon(decorativeIcon);

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(decorativeLabel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        statsPanel.setBackground(new Color(248, 250, 252));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Reduzido de 30 para 20
        
        statsPanel.add(createStatCard("Meus Cavalos", "0", "Cadastrados", new Color(147, 51, 234), FontAwesomeSolid.HORSE));
        statsPanel.add(createStatCard("Eventos Agendados", "0", "Próximos eventos", new Color(16, 185, 129), FontAwesomeSolid.CALENDAR));
        statsPanel.add(createStatCard("Consultas", "0", "Histórico", new Color(59, 130, 246), FontAwesomeSolid.STETHOSCOPE));
        statsPanel.add(createStatCard("À Venda", "0", "No marketplace", new Color(245, 158, 11), FontAwesomeSolid.SHOPPING_CART));
        
        return statsPanel;
    }
    
    private JPanel createStatCard(String title, String value, String subtitle, Color iconColor, FontAwesomeSolid iconType) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(12, 12, 12, 12) // Reduzido de 15 para 12
        ));
        card.setPreferredSize(new Dimension(180, 70)); // Muito menor: 180x70
        card.setMaximumSize(new Dimension(180, 70));
        
        // Layout principal
        JPanel mainLayout = new JPanel(new BorderLayout());
        mainLayout.setBackground(Color.WHITE);
        
        // Título no topo à esquerda
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10)); // Fonte menor
        titleLabel.setForeground(new Color(107, 114, 128));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        // Container inferior com valor e ícone
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        
        // Lado esquerdo - Valor e subtítulo
        JPanel leftContent = new JPanel();
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));
        leftContent.setBackground(Color.WHITE);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Reduzido de 28 para 20
        valueLabel.setForeground(new Color(17, 24, 39));
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("↗ " + subtitle);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 9)); // Reduzido de 11 para 9
        subtitleLabel.setForeground(new Color(34, 197, 94));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        leftContent.add(valueLabel);
        leftContent.add(subtitleLabel);
        
        // Lado direito - Ícone menor
        FontIcon icon = FontIcon.of(iconType, 14, new Color(156, 163, 175)); // Reduzido de 18 para 14
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        bottomPanel.add(leftContent, BorderLayout.WEST);
        bottomPanel.add(iconLabel, BorderLayout.EAST);
        
        mainLayout.add(titleLabel, BorderLayout.NORTH);
        mainLayout.add(Box.createVerticalStrut(3)); // Reduzido de 5 para 3
        mainLayout.add(bottomPanel, BorderLayout.SOUTH);
        
        card.add(mainLayout, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createBottomSections() {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        bottomPanel.setBackground(new Color(248, 250, 252));
        bottomPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 300));
        
        // Seção Cavalos Recentes
        JPanel cavalosSection = createSection(FontAwesomeSolid.HORSE, "Cavalos Recentes", "Ver todos →", 
            "Nenhum cavalo cadastrado");
        
        // Seção Próximos Eventos
        JPanel eventosSection = createSection(FontAwesomeSolid.CALENDAR, "Próximos Eventos", "", 
            "Nenhum evento agendado");
        
        bottomPanel.add(cavalosSection);
        bottomPanel.add(eventosSection);
        
        return bottomPanel;
    }
    
    private JPanel createSection(FontAwesomeSolid iconType, String title, String link, String emptyMessage) {
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
        
        FontIcon headerIcon = FontIcon.of(iconType, 20, new Color(147, 51, 234));
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(headerIcon);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(17, 24, 39));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        titlePanel.add(iconLabel);
        titlePanel.add(titleLabel);
        
        if (!link.isEmpty()) {
            JLabel linkLabel = new JLabel(link);
            linkLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            linkLabel.setForeground(new Color(147, 51, 234));
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
        
        FontIcon emptyIcon = FontIcon.of(iconType, 48, new Color(209, 213, 219));
        JLabel emptyIconLabel = new JLabel();
        emptyIconLabel.setIcon(emptyIcon);
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
