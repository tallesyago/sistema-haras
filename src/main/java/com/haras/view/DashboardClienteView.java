package com.haras.view;

import javax.swing.*;
import java.awt.*;

public class DashboardClienteView extends JFrame {
    private JButton btnMeusCavalos;
    private JButton btnMarketplace;
    private JButton btnAgenda;
    private JButton btnHistoricoVeterinario;
    private JButton btnTrocarPerfil;
    private JLabel lblCavalosCount;
    private JLabel lblEventosCount;
    private JLabel lblConsultasCount;
    private JLabel lblVendaCount;

    public DashboardClienteView() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        setTitle("Haras Premium - Dashboard Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        lblCavalosCount = new JLabel("0");
        lblEventosCount = new JLabel("0");
        lblConsultasCount = new JLabel("0");
        lblVendaCount = new JLabel("0");
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

    private JPanel createProfilePanel() {
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        // Ícone do cliente
        JLabel iconLabel = new JLabel("C");
        iconLabel.setFont(new Font("Arial", Font.BOLD, 24));
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setOpaque(true);
        iconLabel.setBackground(new Color(147, 51, 234));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Informações do cliente
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

        // Dashboard (ativo)
        JButton dashboardBtn = createMenuButton("📊 Dashboard", true);
        btnMeusCavalos = createMenuButton("👥 Meus Cavalos", false);
        btnMarketplace = createMenuButton("🛒 Marketplace", false);
        btnAgenda = createMenuButton("📅 Agenda", false);
        btnHistoricoVeterinario = createMenuButton("📋 Histórico Veterinário", false);

        menuPanel.add(dashboardBtn);
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

        // Header roxo
        JPanel header = createHeader();
        
        // Stats cards
        JPanel statsPanel = createStatsPanel();
        
        // Bottom section
        JPanel bottomSection = createBottomSection();

        contentArea.add(header, BorderLayout.NORTH);
        contentArea.add(statsPanel, BorderLayout.CENTER);
        contentArea.add(bottomSection, BorderLayout.SOUTH);

        return contentArea;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(147, 51, 234));
        header.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        header.setPreferredSize(new Dimension(0, 150));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(147, 51, 234));

        JLabel welcomeLabel = new JLabel("Bom dia, Cliente!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Bem-vindo ao seu painel de gerenciamento");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(welcomeLabel);
        textPanel.add(Box.createVerticalStrut(8));
        textPanel.add(subtitleLabel);

        header.add(textPanel, BorderLayout.WEST);
        return header;
    }

    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 30, 0));
        statsPanel.setBackground(new Color(248, 250, 252));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 40));

        // Cards de estatísticas
        JPanel cavalosCard = createStatsCard("Meus Cavalos", lblCavalosCount, "📈 Cadastrados", new Color(147, 51, 234));
        JPanel eventosCard = createStatsCard("Eventos Agendados", lblEventosCount, "📅 Próximos eventos", new Color(16, 185, 129));
        JPanel consultasCard = createStatsCard("Consultas", lblConsultasCount, "📋 Histórico", new Color(59, 130, 246));
        JPanel vendaCard = createStatsCard("À Venda", lblVendaCount, "🛒 No marketplace", new Color(245, 158, 11));

        statsPanel.add(cavalosCard);
        statsPanel.add(eventosCard);
        statsPanel.add(consultasCard);
        statsPanel.add(vendaCard);

        return statsPanel;
    }

    private JPanel createStatsCard(String title, JLabel countLabel, String subtitle, Color iconColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235)),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);

        // Título
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Contador
        countLabel.setFont(new Font("Arial", Font.BOLD, 42));
        countLabel.setForeground(Color.BLACK);
        countLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Subtítulo
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(16, 185, 129));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(countLabel);
        leftPanel.add(Box.createVerticalStrut(8));
        leftPanel.add(subtitleLabel);

        // Ícone
        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(iconColor);
        iconPanel.setPreferredSize(new Dimension(60, 60));
        iconPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        card.add(leftPanel, BorderLayout.CENTER);
        card.add(iconPanel, BorderLayout.EAST);

        return card;
    }

    private JPanel createBottomSection() {
        JPanel bottomSection = new JPanel(new GridLayout(1, 2, 30, 0));
        bottomSection.setBackground(new Color(248, 250, 252));
        bottomSection.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
        bottomSection.setPreferredSize(new Dimension(0, 350));

        // Cavalos Recentes
        JPanel cavalosRecentes = createEmptyStatePanel("🐎 Cavalos Recentes", "Nenhum cavalo cadastrado", "Ver todos →");
        
        // Próximos Eventos
        JPanel proximosEventos = createEmptyStatePanel("📅 Próximos Eventos", "Nenhum evento agendado", "");

        bottomSection.add(cavalosRecentes);
        bottomSection.add(proximosEventos);

        return bottomSection;
    }

    private JPanel createEmptyStatePanel(String title, String emptyMessage, String actionText) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235)),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        if (!actionText.isEmpty()) {
            JLabel actionLabel = new JLabel(actionText);
            actionLabel.setForeground(new Color(245, 158, 11));
            actionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            headerPanel.add(actionLabel, BorderLayout.EAST);
        }

        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Empty state
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
        emptyPanel.setBackground(Color.WHITE);

        JLabel emptyIcon = new JLabel("👥", SwingConstants.CENTER);
        emptyIcon.setFont(new Font("Arial", Font.PLAIN, 48));
        emptyIcon.setForeground(Color.LIGHT_GRAY);
        emptyIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emptyLabel = new JLabel(emptyMessage, SwingConstants.CENTER);
        emptyLabel.setForeground(Color.GRAY);
        emptyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emptyPanel.add(Box.createVerticalGlue());
        emptyPanel.add(emptyIcon);
        emptyPanel.add(Box.createVerticalStrut(15));
        emptyPanel.add(emptyLabel);
        emptyPanel.add(Box.createVerticalGlue());

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(emptyPanel, BorderLayout.CENTER);

        return panel;
    }

    // Método para criar apenas o conteúdo (sem sidebar)
    public JPanel getContentPanel() {
        return createContentArea();
    }

    // Getters para os botões (para o controller)
    public JButton getBtnMeusCavalos() { return btnMeusCavalos; }
    public JButton getBtnMarketplace() { return btnMarketplace; }
    public JButton getBtnAgenda() { return btnAgenda; }
    public JButton getBtnHistoricoVeterinario() { return btnHistoricoVeterinario; }
    public JButton getBtnTrocarPerfil() { return btnTrocarPerfil; }

    // Métodos para atualizar os contadores
    public void setCavalosCount(int count) { lblCavalosCount.setText(String.valueOf(count)); }
    public void setEventosCount(int count) { lblEventosCount.setText(String.valueOf(count)); }
    public void setConsultasCount(int count) { lblConsultasCount.setText(String.valueOf(count)); }
    public void setVendaCount(int count) { lblVendaCount.setText(String.valueOf(count)); }
}