package com.haras.view;

import javax.swing.*;
import java.awt.*;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;
import com.haras.controller.NavigationController;
import com.haras.controller.NavigationController.Page;

public class BaseView extends JFrame {
    protected JPanel contentArea;
    private JButton btnDashboard;
    private JButton btnMeusCavalos;
    private JButton btnMarketplace;
    private JButton btnAgenda;
    private JButton btnHistoricoVeterinario;
    private JButton btnTrocarPerfil;
    
    // Controller para navegação
    private NavigationController navigationController;

    public BaseView() {
        this.navigationController = new NavigationController();
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
        sidebar.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        sidebar.setPreferredSize(new Dimension(280, 0));

        // Logo e título
        JPanel logoPanel = createLogoPanel();
        
        // Perfil do cliente
        JPanel profilePanel = createProfilePanel();

        // Botão Trocar Perfil com FontIcon
        FontIcon switchIcon = FontIcon.of(FontAwesomeSolid.EXCHANGE_ALT, 14, new Color(107, 114, 128));
        btnTrocarPerfil = new JButton("Trocar Perfil", switchIcon);
        btnTrocarPerfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTrocarPerfil.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btnTrocarPerfil.setBackground(new Color(249, 250, 251));
        btnTrocarPerfil.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));
        btnTrocarPerfil.setFocusPainted(false);
        btnTrocarPerfil.setContentAreaFilled(false);
        btnTrocarPerfil.setOpaque(true);
        btnTrocarPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnTrocarPerfil.setForeground(new Color(107, 114, 128));
        
        btnTrocarPerfil.addActionListener(e -> System.out.println("Trocando Perfil"));
        
        // Hover effect
        btnTrocarPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTrocarPerfil.setBackground(new Color(243, 244, 246));
                switchIcon.setIconColor(new Color(55, 65, 81));
                btnTrocarPerfil.setForeground(new Color(55, 65, 81));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTrocarPerfil.setBackground(new Color(249, 250, 251));
                switchIcon.setIconColor(new Color(107, 114, 128));
                btnTrocarPerfil.setForeground(new Color(107, 114, 128));
            }
        });

        // Menu items
        JPanel menuPanel = createMenuPanel();

        sidebar.add(logoPanel);
        sidebar.add(Box.createVerticalStrut(32));
        sidebar.add(profilePanel);
        sidebar.add(Box.createVerticalStrut(32));
        sidebar.add(menuPanel);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnTrocarPerfil);

        return sidebar;
    }

    private JPanel createLogoPanel() {
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Ícone Font Awesome para cavalos
        FontIcon logoIcon = FontIcon.of(FontAwesomeSolid.HORSE, 24, new Color(139, 69, 19));
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(logoIcon);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Haras Premium");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(139, 69, 19));

        JLabel subtitleLabel = new JLabel("Sistema de Gerenciamento");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
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

        // Container circular para o avatar
        JPanel iconContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(147, 51, 234));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        iconContainer.setPreferredSize(new Dimension(48, 48));
        iconContainer.setOpaque(false);
        iconContainer.setLayout(new BorderLayout());

        // Ícone Font Awesome de usuário
        FontIcon userIcon = FontIcon.of(FontAwesomeSolid.USER, 18, Color.WHITE);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(userIcon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setVerticalAlignment(SwingConstants.CENTER);

        iconContainer.add(iconLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel clienteLabel = new JLabel("Cliente");
        clienteLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        clienteLabel.setForeground(new Color(31, 41, 55));

        JLabel perfilLabel = new JLabel("Perfil Ativo");
        perfilLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        perfilLabel.setForeground(new Color(147, 51, 234));

        infoPanel.add(clienteLabel);
        infoPanel.add(perfilLabel);

        profilePanel.add(iconContainer);
        profilePanel.add(Box.createHorizontalStrut(15));
        profilePanel.add(infoPanel);

        return profilePanel;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.WHITE);

        // Criar ícones Font Awesome
        FontIcon dashboardIcon = FontIcon.of(FontAwesomeSolid.CHART_BAR, 16);
        FontIcon horseIcon = FontIcon.of(FontAwesomeSolid.HORSE, 16);
        FontIcon marketIcon = FontIcon.of(FontAwesomeSolid.SHOPPING_CART, 16);
        FontIcon calendarIcon = FontIcon.of(FontAwesomeSolid.CALENDAR, 16);
        FontIcon medicalIcon = FontIcon.of(FontAwesomeSolid.STETHOSCOPE, 16);

        // Criar botões com ícones Font Awesome
        btnDashboard = new JButton("Dashboard", dashboardIcon);
        btnMeusCavalos = new JButton("Meus Cavalos", horseIcon);
        btnMarketplace = new JButton("Marketplace", marketIcon);
        btnAgenda = new JButton("Agenda", calendarIcon);
        btnHistoricoVeterinario = new JButton("Histórico Veterinário", medicalIcon);

        // Configurar estilo dos botões
        JButton[] buttons = {btnDashboard, btnMeusCavalos, btnMarketplace, btnAgenda, btnHistoricoVeterinario};
        for (JButton button : buttons) {
            configureMenuButton(button);
        }

        // Event listeners usando o NavigationController
        btnDashboard.addActionListener(e -> navigateToPage(Page.DASHBOARD, btnDashboard));
        btnMeusCavalos.addActionListener(e -> navigateToPage(Page.MEUS_CAVALOS, btnMeusCavalos));
        btnMarketplace.addActionListener(e -> navigateToPage(Page.MARKETPLACE, btnMarketplace));
        btnAgenda.addActionListener(e -> navigateToPage(Page.AGENDA, btnAgenda));
        btnHistoricoVeterinario.addActionListener(e -> navigateToPage(Page.HISTORICO_VETERINARIO, btnHistoricoVeterinario));

        menuPanel.add(btnDashboard);
        menuPanel.add(Box.createVerticalStrut(4));
        menuPanel.add(btnMeusCavalos);
        menuPanel.add(Box.createVerticalStrut(4));
        menuPanel.add(btnMarketplace);
        menuPanel.add(Box.createVerticalStrut(4));
        menuPanel.add(btnAgenda);
        menuPanel.add(Box.createVerticalStrut(4));
        menuPanel.add(btnHistoricoVeterinario);

        return menuPanel;
    }

    private void configureMenuButton(JButton button) {
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        button.setPreferredSize(new Dimension(240, 44));
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setForeground(new Color(107, 114, 128));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        
        FontIcon icon = (FontIcon) button.getIcon();
        if (icon != null) {
            icon.setIconColor(new Color(107, 114, 128));
        }
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!button.getBackground().equals(new Color(147, 51, 234))) {
                    button.setBackground(new Color(249, 250, 251));
                    button.setForeground(new Color(55, 65, 81));
                    if (icon != null) {
                        icon.setIconColor(new Color(55, 65, 81));
                    }
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!button.getBackground().equals(new Color(147, 51, 234))) {
                    button.setBackground(Color.WHITE);
                    button.setForeground(new Color(107, 114, 128));
                    if (icon != null) {
                        icon.setIconColor(new Color(107, 114, 128));
                    }
                }
            }
        });
    }

    private void updateActiveButton(JButton activeButton) {
        JButton[] buttons = {btnDashboard, btnMeusCavalos, btnMarketplace, btnAgenda, btnHistoricoVeterinario};
        
        // Resetar todos os botões
        for (JButton button : buttons) {
            if (button != null) {
                button.setBackground(Color.WHITE);
                button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
                button.setForeground(new Color(107, 114, 128));
                FontIcon icon = (FontIcon) button.getIcon();
                if (icon != null) {
                    icon.setIconColor(new Color(107, 114, 128));
                }
            }
        }

        // Ativar botão selecionado
        if (activeButton != null) {
            activeButton.setBackground(new Color(147, 51, 234));
            activeButton.setForeground(Color.WHITE);
            activeButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(199, 125, 255)),
                BorderFactory.createEmptyBorder(10, 13, 10, 16)
            ));
            FontIcon icon = (FontIcon) activeButton.getIcon();
            if (icon != null) {
                icon.setIconColor(Color.WHITE);
            }
        }
    }

    /**
     * Navega para uma página específica
     */
    private void navigateToPage(Page page, JButton activeButton) {
        try {
            // Obter conteúdo da página através do controller
            JPanel pageContent = navigationController.getPage(page);
            String pageTitle = navigationController.getPageTitle(page);
            
            // Atualizar interface
            contentArea.removeAll();
            contentArea.add(pageContent, BorderLayout.CENTER);
            contentArea.revalidate();
            contentArea.repaint();
            
            // Atualizar botão ativo e título
            updateActiveButton(activeButton);
            setTitle(pageTitle);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar página: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void showDashboard() {
        navigateToPage(Page.DASHBOARD, btnDashboard);
    }

    protected void showMeusCavalos() {
        navigateToPage(Page.MEUS_CAVALOS, btnMeusCavalos);
    }

    protected void showAgenda() {
        navigateToPage(Page.AGENDA, btnAgenda);
    }
}
