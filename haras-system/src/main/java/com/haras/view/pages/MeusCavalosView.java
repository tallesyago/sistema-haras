package com.haras.view.pages;

import javax.swing.*;
import java.awt.*;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

public class MeusCavalosView {
    
    public JPanel getContentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 250, 252));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Header com botão adicionar
        JPanel headerPanel = createHeaderWithButton();
        
        // Lista de cavalos
        JPanel cavalosList = createCavalosList();
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(cavalosList, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createHeaderWithButton() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(248, 250, 252));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(new Color(248, 250, 252));
        
        FontIcon horseIcon = FontIcon.of(FontAwesomeSolid.HORSE, 32, new Color(147, 51, 234));
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(horseIcon);
        
        JLabel titleLabel = new JLabel("Meus Cavalos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(31, 41, 55));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        
        titlePanel.add(iconLabel);
        titlePanel.add(titleLabel);
        
        // Botão adicionar com ícone
        FontIcon addIcon = FontIcon.of(FontAwesomeSolid.PLUS, 14, Color.WHITE);
        JButton addButton = new JButton("Adicionar Cavalo", addIcon);
        addButton.setBackground(new Color(147, 51, 234));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addButton.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Funcionalidade em desenvolvimento!"));
        
        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(addButton, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createCavalosList() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(248, 250, 252));
        
        // Exemplo de cavalos
        String[][] cavalos = {
            {"Thunder", "Puro Sangue", "5 anos", "Ativo"},
            {"Lightning", "Quarto de Milha", "3 anos", "Treinamento"},
            {"Storm", "Andaluz", "7 anos", "Recuperação"}
        };
        
        for (String[] cavalo : cavalos) {
            listPanel.add(createCavaloCard(cavalo[0], cavalo[1], cavalo[2], cavalo[3]));
            listPanel.add(Box.createVerticalStrut(10));
        }
        
        return listPanel;
    }
    
    private JPanel createCavaloCard(String nome, String raca, String idade, String status) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel nomeLabel = new JLabel(nome);
        nomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nomeLabel.setForeground(new Color(31, 41, 55));
        
        JLabel racaLabel = new JLabel("Raça: " + raca);
        racaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        racaLabel.setForeground(new Color(107, 114, 128));
        
        JLabel idadeLabel = new JLabel("Idade: " + idade);
        idadeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idadeLabel.setForeground(new Color(107, 114, 128));
        
        JLabel statusLabel = new JLabel("Status: " + status);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusLabel.setForeground(status.equals("Ativo") ? new Color(34, 197, 94) : new Color(234, 179, 8));
        
        infoPanel.add(nomeLabel);
        infoPanel.add(statusLabel);
        infoPanel.add(racaLabel);
        infoPanel.add(idadeLabel);
        
        card.add(infoPanel, BorderLayout.CENTER);
        
        return card;
    }
}
