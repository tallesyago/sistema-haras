package com.haras.view;

import javax.swing.*;
import java.awt.*;

public class AgendaView extends JFrame {
    private JButton btnNovoEvento;

    public AgendaView() {
        initComponents();
        setupLayout();
        initEventListeners();
    }

    private void initComponents() {
        setTitle("Haras Premium - Agenda de Eventos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBackground(new Color(248, 250, 252));
    }

    private JPanel createContentArea() {
        JPanel contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(new Color(248, 250, 252));
        contentArea.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Header com título e botão
        JPanel headerPanel = createHeaderPanel();
        
        // Estado vazio
        JPanel emptyStatePanel = createEmptyStatePanel();

        contentArea.add(headerPanel, BorderLayout.NORTH);
        contentArea.add(emptyStatePanel, BorderLayout.CENTER);

        return contentArea;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(248, 250, 252));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(248, 250, 252));

        JLabel titleLabel = new JLabel("Agenda de Eventos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Gerencie seus compromissos");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        btnNovoEvento = new JButton("+ Novo Evento");
        btnNovoEvento.setBackground(new Color(16, 185, 129));
        btnNovoEvento.setForeground(Color.WHITE);
        btnNovoEvento.setFont(new Font("Arial", Font.BOLD, 14));
        btnNovoEvento.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        btnNovoEvento.setFocusPainted(false);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(btnNovoEvento, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createEmptyStatePanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
        emptyPanel.setBackground(new Color(248, 250, 252));
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));

        // Ícone do calendário
        JLabel iconLabel = new JLabel("📅");
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        iconLabel.setForeground(new Color(156, 163, 175));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Texto principal
        JLabel titleLabel = new JLabel("Nenhum evento agendado");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Texto secundário
        JLabel subtitleLabel = new JLabel("Crie seu primeiro evento para começar");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão Criar Evento
        JButton btnCriarEvento = new JButton("+ Criar Evento");
        btnCriarEvento.setBackground(new Color(16, 185, 129));
        btnCriarEvento.setForeground(Color.WHITE);
        btnCriarEvento.setFont(new Font("Arial", Font.BOLD, 14));
        btnCriarEvento.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        btnCriarEvento.setFocusPainted(false);
        btnCriarEvento.setAlignmentX(Component.CENTER_ALIGNMENT);

        emptyPanel.add(iconLabel);
        emptyPanel.add(Box.createVerticalStrut(20));
        emptyPanel.add(titleLabel);
        emptyPanel.add(Box.createVerticalStrut(10));
        emptyPanel.add(subtitleLabel);
        emptyPanel.add(Box.createVerticalStrut(30));
        emptyPanel.add(btnCriarEvento);

        return emptyPanel;
    }

    private void initEventListeners() {
        btnNovoEvento.addActionListener(e -> System.out.println("Criando Novo Evento"));
    }

    // Método para criar apenas o conteúdo (sem sidebar)
    public JPanel getContentPanel() {
        return createContentArea();
    }

    // Getters para os componentes (para o controller)
    public JButton getBtnNovoEvento() { return btnNovoEvento; }
}
