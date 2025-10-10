package com.haras.view;

import javax.swing.*;
import java.awt.*;

public class BaseView extends JFrame {
    protected JPanel contentArea;
    private JButton btnDashboard;
    private JButton btnMeusCavalos;
    private JButton btnMarketplace;
    private JButton btnAgenda;
    private JButton btnHistoricoVeterinario;
    private JButton btnTrocarPerfil;

    public BaseView() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        setTitle("Haras Premium");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 250, 252));

        // Sidebar
        JPanel sidebar = createSidebar();
        
        // Content area (será substituída)
        contentArea = new JPanel(new BorderLayout());
        contentArea.setBackground(new Color(248, 250, 252));

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(contentArea, BorderLayout.CENTER);

        add(mainPanel);
        
        // Carregar dashboard inicial
        showDashboard();
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

        // Inicializar botão Trocar Perfil antes do menu
        btnTrocarPerfil = new JButton("🔄 Trocar Perfil");
        btnTrocarPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTrocarPerfil.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnTrocarPerfil.setBackground(new Color(240, 240, 240));
        btnTrocarPerfil.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnTrocarPerfil.setFocusPainted(false);

        // Menu items
        JPanel menuPanel = createMenuPanel();

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
        btnMeusCavalos = createMenuButton("👥 Meus Cavalos", false);
        btnMarketplace = createMenuButton("🛒 Marketplace", false);
        btnAgenda = createMenuButton("📅 Agenda", false);
        btnHistoricoVeterinario = createMenuButton("📋 Histórico Veterinário", false);

        // Event listeners
        btnDashboard.addActionListener(e -> showDashboard());
        btnMeusCavalos.addActionListener(e -> showMeusCavalos());
        btnMarketplace.addActionListener(e -> System.out.println("Abrindo Marketplace"));
        btnAgenda.addActionListener(e -> showAgenda());
        btnHistoricoVeterinario.addActionListener(e -> System.out.println("Abrindo Histórico Veterinário"));
        btnTrocarPerfil.addActionListener(e -> System.out.println("Trocando Perfil"));

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
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!button.getBackground().equals(new Color(147, 51, 234))) {
                    button.setBackground(new Color(248, 250, 252));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!button.getBackground().equals(new Color(147, 51, 234))) {
                    button.setBackground(Color.WHITE);
                }
            }
        });

        return button;
    }

    private void updateActiveButton(JButton activeButton) {
        // Resetar todos os botões
        btnDashboard.setBackground(Color.WHITE);
        btnDashboard.setForeground(Color.BLACK);
        btnMeusCavalos.setBackground(Color.WHITE);
        btnMeusCavalos.setForeground(Color.BLACK);
        btnMarketplace.setBackground(Color.WHITE);
        btnMarketplace.setForeground(Color.BLACK);
        btnAgenda.setBackground(Color.WHITE);
        btnAgenda.setForeground(Color.BLACK);
        btnHistoricoVeterinario.setBackground(Color.WHITE);
        btnHistoricoVeterinario.setForeground(Color.BLACK);

        // Ativar o botão selecionado
        activeButton.setBackground(new Color(147, 51, 234));
        activeButton.setForeground(Color.WHITE);
    }

    protected void showDashboard() {
        DashboardClienteView dashboardView = new DashboardClienteView();
        JPanel dashboardContent = dashboardView.getContentPanel();
        
        contentArea.removeAll();
        contentArea.add(dashboardContent, BorderLayout.CENTER);
        contentArea.revalidate();
        contentArea.repaint();
        updateActiveButton(btnDashboard);
        setTitle("Haras Premium - Dashboard Cliente");
    }

    protected void showMeusCavalos() {
        MeusCavalosView meusCavalosView = new MeusCavalosView();
        JPanel meusCavalosContent = meusCavalosView.getContentPanel();
        
        contentArea.removeAll();
        contentArea.add(meusCavalosContent, BorderLayout.CENTER);
        contentArea.revalidate();
        contentArea.repaint();
        updateActiveButton(btnMeusCavalos);
        setTitle("Haras Premium - Meus Cavalos");
    }

    protected void showAgenda() {
        AgendaView agendaView = new AgendaView();
        JPanel agendaContent = agendaView.getContentPanel();
        
        contentArea.removeAll();
        contentArea.add(agendaContent, BorderLayout.CENTER);
        contentArea.revalidate();
        contentArea.repaint();
        updateActiveButton(btnAgenda);
        setTitle("Haras Premium - Agenda de Eventos");
    }
}
