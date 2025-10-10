package com.haras.view;

import javax.swing.*;
import java.awt.*;

public class MeusCavalosView extends JFrame {
    private JButton btnDashboard;
    private JButton btnMeusCavalos;
    private JButton btnMarketplace;
    private JButton btnAgenda;
    private JButton btnHistoricoVeterinario;
    private JButton btnTrocarPerfil;
    private JButton btnAdicionarCavalo;
    private JTextField txtBuscar;
    private JComboBox<String> cmbStatus;

    public MeusCavalosView() {
        initComponents();
        setupLayout();
        initEventListeners();
    }

    private void initComponents() {
        setTitle("Haras Premium - Meus Cavalos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        txtBuscar = new JTextField("Buscar por nome ou raça...");
        txtBuscar.setForeground(Color.GRAY);
        
        String[] statusOptions = {"Todos os Status", "Ativo", "Inativo", "Em Tratamento", "À Venda"};
        cmbStatus = new JComboBox<>(statusOptions);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 250, 252));

        // Sidebar
        JPanel sidebar = createSidebar();
        
        // Content area
        JPanel contentArea = createContentArea();

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(contentArea, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        sidebar.setPreferredSize(new Dimension(280, 0));

        // Logo e título
        JPanel logoPanel = createLogoPanel();
        
        // Perfil do cliente
        JPanel profilePanel = createProfilePanel();

        // Menu items
        JPanel menuPanel = createMenuPanel();

        // Botão Trocar Perfil
        btnTrocarPerfil = new JButton("🔄 Trocar Perfil");
        btnTrocarPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTrocarPerfil.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnTrocarPerfil.setBackground(new Color(240, 240, 240));
        btnTrocarPerfil.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnTrocarPerfil.setFocusPainted(false);

        sidebar.add(logoPanel);
        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(profilePanel);
        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(menuPanel);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnTrocarPerfil);

        return sidebar;
    }

    private JPanel createLogoPanel() {
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel logoLabel = new JLabel("🐎");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Haras Premium");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(139, 69, 19));

        JLabel subtitleLabel = new JLabel("Sistema de Gerenciamento");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(Color.GRAY);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        logoPanel.add(logoLabel);
        logoPanel.add(Box.createHorizontalStrut(10));
        logoPanel.add(titlePanel);

        return logoPanel;
    }

    private JPanel createProfilePanel() {
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JLabel iconLabel = new JLabel("C");
        iconLabel.setFont(new Font("Arial", Font.BOLD, 24));
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setOpaque(true);
        iconLabel.setBackground(new Color(147, 51, 234));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel clienteLabel = new JLabel("Cliente");
        clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel perfilLabel = new JLabel("Perfil Ativo");
        perfilLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        perfilLabel.setForeground(new Color(147, 51, 234));

        infoPanel.add(clienteLabel);
        infoPanel.add(perfilLabel);

        profilePanel.add(iconLabel);
        profilePanel.add(Box.createHorizontalStrut(15));
        profilePanel.add(infoPanel);

        return profilePanel;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.WHITE);

        btnDashboard = createMenuButton("📊 Dashboard", false);
        btnMeusCavalos = createMenuButton("👥 Meus Cavalos", true);
        btnMarketplace = createMenuButton("🛒 Marketplace", false);
        btnAgenda = createMenuButton("📅 Agenda", false);
        btnHistoricoVeterinario = createMenuButton("📋 Histórico Veterinário", false);

        menuPanel.add(btnDashboard);
        menuPanel.add(btnMeusCavalos);
        menuPanel.add(btnMarketplace);
        menuPanel.add(btnAgenda);
        menuPanel.add(btnHistoricoVeterinario);

        return menuPanel;
    }

    private JButton createMenuButton(String text, boolean active) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));

        if (active) {
            button.setBackground(new Color(147, 51, 234));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(248, 250, 252));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.WHITE);
                }
            });
        }

        return button;
    }

    private JPanel createContentArea() {
        JPanel contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(new Color(248, 250, 252));
        contentArea.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Header com título e botão
        JPanel headerPanel = createHeaderPanel();
        
        // Barra de pesquisa e filtros
        JPanel searchPanel = createSearchPanel();
        
        // Estado vazio
        JPanel emptyStatePanel = createEmptyStatePanel();

        contentArea.add(headerPanel, BorderLayout.NORTH);
        contentArea.add(searchPanel, BorderLayout.CENTER);
        contentArea.add(emptyStatePanel, BorderLayout.SOUTH);

        return contentArea;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(248, 250, 252));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(248, 250, 252));

        JLabel titleLabel = new JLabel("Meus Cavalos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Gerencie seus equinos");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        btnAdicionarCavalo = new JButton("+ Adicionar Cavalo");
        btnAdicionarCavalo.setBackground(new Color(194, 120, 3));
        btnAdicionarCavalo.setForeground(Color.WHITE);
        btnAdicionarCavalo.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdicionarCavalo.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        btnAdicionarCavalo.setFocusPainted(false);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(btnAdicionarCavalo, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(248, 250, 252));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        // Campo de busca
        txtBuscar.setPreferredSize(new Dimension(400, 40));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235)),
            BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ));
        txtBuscar.setFont(new Font("Arial", Font.PLAIN, 14));

        // Combo de status
        cmbStatus.setPreferredSize(new Dimension(200, 40));
        cmbStatus.setBackground(Color.WHITE);
        cmbStatus.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
        cmbStatus.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftPanel.setBackground(new Color(248, 250, 252));
        leftPanel.add(txtBuscar);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setBackground(new Color(248, 250, 252));
        rightPanel.add(cmbStatus);

        searchPanel.add(leftPanel, BorderLayout.WEST);
        searchPanel.add(rightPanel, BorderLayout.EAST);

        return searchPanel;
    }

    private JPanel createEmptyStatePanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
        emptyPanel.setBackground(new Color(248, 250, 252));
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 80, 0));

        // Ícone do cavalo
        JLabel iconLabel = new JLabel("🐎");
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        iconLabel.setForeground(new Color(156, 163, 175));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Texto principal
        JLabel titleLabel = new JLabel("Nenhum cavalo encontrado");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Texto secundário
        JLabel subtitleLabel = new JLabel("Adicione seu primeiro cavalo para começar");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.GRAY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão Adicionar Cavalo
        JButton btnAdicionar = new JButton("+ Adicionar Cavalo");
        btnAdicionar.setBackground(new Color(194, 120, 3));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdicionar.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);

        emptyPanel.add(iconLabel);
        emptyPanel.add(Box.createVerticalStrut(20));
        emptyPanel.add(titleLabel);
        emptyPanel.add(Box.createVerticalStrut(10));
        emptyPanel.add(subtitleLabel);
        emptyPanel.add(Box.createVerticalStrut(30));
        emptyPanel.add(btnAdicionar);

        return emptyPanel;
    }

    private void initEventListeners() {
        // Placeholder do campo de busca
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtBuscar.getText().equals("Buscar por nome ou raça...")) {
                    txtBuscar.setText("");
                    txtBuscar.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtBuscar.getText().isEmpty()) {
                    txtBuscar.setText("Buscar por nome ou raça...");
                    txtBuscar.setForeground(Color.GRAY);
                }
            }
        });

        btnAdicionarCavalo.addActionListener(e -> System.out.println("Adicionando Cavalo"));
    }

    // Método para criar apenas o conteúdo (sem sidebar)
    public JPanel getContentPanel() {
        return createContentArea();
    }

    // Getters para os componentes (para o controller)
    public JButton getBtnDashboard() { return btnDashboard; }
    public JButton getBtnMeusCavalos() { return btnMeusCavalos; }
    public JButton getBtnMarketplace() { return btnMarketplace; }
    public JButton getBtnAgenda() { return btnAgenda; }
    public JButton getBtnHistoricoVeterinario() { return btnHistoricoVeterinario; }
    public JButton getBtnTrocarPerfil() { return btnTrocarPerfil; }
    public JButton getBtnAdicionarCavalo() { return btnAdicionarCavalo; }
    public JTextField getTxtBuscar() { return txtBuscar; }
    public JComboBox<String> getCmbStatus() { return cmbStatus; }
}
