package com.haras.controller;

import javax.swing.JPanel;
import com.haras.model.Usuario;
import com.haras.view.BaseView;
import com.haras.view.DashboardView;
import com.haras.view.MeusCavalosView;
import com.haras.view.MarketplaceView;
import com.haras.view.AgendaView;
import com.haras.view.HistoricoVeterinarioView;

/**
 * Controller responsável pela navegação entre páginas e gerenciamento dos controladores específicos
 */
public class NavigationController {
    private static NavigationController instance;
    private Usuario loggedUser;
    private BaseView currentView;
    private JPanel mainContent;
    
    // Views específicas
    private DashboardView dashboardView;
    private MeusCavalosView meusCavalosView;
    private MarketplaceView marketplaceView;
    private AgendaView agendaView;
    private HistoricoVeterinarioView historicoVeterinarioView;
    
    // Controladores específicos
    private AgendaController agendaController;
    private AtendimentoController atendimentoController;
    private ClienteController clienteController;
    private EquinoController equinoController;
    private MarketplaceController marketplaceController;
    private VeterinaryController veterinaryController;
    
    private NavigationController() {
        initializeControllers();
        initializeViews();
    }
    
    /**
     * Obtém a instância única do NavigationController (Singleton)
     */
    public static NavigationController getInstance() {
        if (instance == null) {
            instance = new NavigationController();
        }
        return instance;
    }
    
    /**
     * Inicializa todos os controladores específicos
     */
    private void initializeControllers() {
        try {
            agendaController = AgendaController.getInstance();
            atendimentoController = AtendimentoController.getInstance();
            clienteController = ClienteController.getInstance();
            equinoController = EquinoController.getInstance();
            marketplaceController = MarketplaceController.getInstance();
            veterinaryController = VeterinaryController.getInstance();
        } catch (Exception e) {
            System.err.println("Erro ao inicializar controladores: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Inicializa todas as views
     */
    private void initializeViews() {
        try {
            dashboardView = new DashboardView();
            meusCavalosView = new MeusCavalosView();
            marketplaceView = new MarketplaceView(marketplaceController);
            agendaView = new AgendaView(agendaController);
            historicoVeterinarioView = new HistoricoVeterinarioView(veterinaryController);
            
            // Carregar eventos iniciais na agenda
            agendaView.atualizarEventos(agendaController.getAllEvents());
            
            System.out.println("Views inicializadas com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao inicializar views: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public enum Page {
        DASHBOARD,
        MEUS_CAVALOS,
        MARKETPLACE,
        AGENDA,
        HISTORICO_VETERINARIO
    }
    
    /**
     * Retorna o painel da página solicitada
     */
    public JPanel getPage(Page page) {
        try {
            JPanel panel = null;
            
            switch (page) {
                case DASHBOARD:
                    if (dashboardView != null) {
                        panel = dashboardView.getContentPanel();
                    }
                    break;
                case MEUS_CAVALOS:
                    if (meusCavalosView != null) {
                        panel = meusCavalosView.getContentPanel();
                    }
                    break;
                case MARKETPLACE:
                    if (marketplaceView != null) {
                        panel = marketplaceView.getContentPanel();
                    }
                    break;
                case AGENDA:
                    if (agendaView != null) {
                        panel = agendaView.getContentPanel();
                    }
                    break;
                case HISTORICO_VETERINARIO:
                    if (historicoVeterinarioView != null) {
                        panel = historicoVeterinarioView.getContentPanel();
                    }
                    break;
                default:
                    if (dashboardView != null) {
                        panel = dashboardView.getContentPanel();
                    }
            }
            
            if (panel != null) {
                return panel;
            }
            
            return createErrorPanel("View não disponível: " + page.name());
        } catch (Exception e) {
            e.printStackTrace();
            return createErrorPanel("Erro ao carregar página: " + e.getMessage());
        }
    }
    
    /**
     * Cria um painel de erro em caso de falha
     */
    private JPanel createErrorPanel(String errorMessage) {
        JPanel panel = new JPanel();
        panel.setBackground(new java.awt.Color(255, 245, 245));
        javax.swing.JLabel errorLabel = new javax.swing.JLabel(errorMessage);
        errorLabel.setForeground(new java.awt.Color(200, 0, 0));
        panel.add(errorLabel);
        return panel;
    }
    
    /**
     * Retorna o título da página
     */
    public String getPageTitle(Page page) {
        switch (page) {
            case DASHBOARD:
                return "Haras Premium - Dashboard";
            case MEUS_CAVALOS:
                return "Haras Premium - Meus Cavalos";
            case MARKETPLACE:
                return "Haras Premium - Marketplace";
            case AGENDA:
                return "Haras Premium - Agenda";
            case HISTORICO_VETERINARIO:
                return "Haras Premium - Histórico Veterinário";
            default:
                return "Haras Premium";
        }
    }

    /**
     * Define o usuário logado no sistema
     */
    public void setLoggedUser(Usuario usuario) {
        this.loggedUser = usuario;
    }
    
    /**
     * Obtém o usuário atualmente logado no sistema
     */
    public Usuario getLoggedUser() {
        return loggedUser;
    }
    
    /**
     * Define a view atual sendo exibida
     */
    public void setCurrentView(BaseView view) {
        this.currentView = view;
    }
    
    /**
     * Obtém a view atual sendo exibida
     */
    public BaseView getCurrentView() {
        return currentView;
    }

    // Getters para os controladores específicos
    
    public AgendaController getAgendaController() {
        return agendaController;
    }
    
    public AtendimentoController getAtendimentoController() {
        return atendimentoController;
    }
    
    public ClienteController getClienteController() {
        return clienteController;
    }
    
    public EquinoController getEquinoController() {
        return equinoController;
    }
    
    public MarketplaceController getMarketplaceController() {
        return marketplaceController;
    }
    
    public VeterinaryController getVeterinaryController() {
        return veterinaryController;
    }

    /**
     * Método acionado pela view para trocar o perfil ativo (placeholder)
     */
    public void toggleProfile() {
        if (this.loggedUser == null) {
            // Placeholder: criar um usuário temporário se necessário
            // this.loggedUser = new Usuario();
        } else {
            this.loggedUser = null;
        }
        if (currentView != null) {
            currentView.updateProfileInfo(this.loggedUser);
        }
    }

    /**
     * Tratamento centralizado de erros de navegação (exibe diálogo)
     */
    public void handleNavigationError(Exception e) {
        javax.swing.JOptionPane.showMessageDialog(
            currentView != null ? currentView : null,
            "Erro ao carregar página: " + e.getMessage(),
            "Erro",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Define o painel principal onde as views serão exibidas
     */
    public void setMainContent(JPanel mainContent) {
        this.mainContent = mainContent;
        showDashboard();
    }

    /**
     * Navega para a página especificada
     */
    public void navigateToPage(Page page) {
        if (mainContent != null) {
            mainContent.removeAll();
            mainContent.add(getPage(page));
            mainContent.revalidate();
            mainContent.repaint();
        }
    }

    /**
     * Exibe a view Dashboard no painel principal
     */
    public void showDashboard() {
        navigateToPage(Page.DASHBOARD);
    }

    /**
     * Exibe a view "Meus Cavalos" no painel principal
     */
    public void showMeusCavalos() {
        navigateToPage(Page.MEUS_CAVALOS);
    }

    /**
     * Exibe a view Marketplace no painel principal
     */
    public void showMarketplace() {
        navigateToPage(Page.MARKETPLACE);
    }

    /**
     * Exibe a view Agenda no painel principal
     */
    public void showAgenda() {
        navigateToPage(Page.AGENDA);
    }

    /**
     * Exibe a view Histórico Veterinário no painel principal
     */
    public void showHistoricoVeterinario() {
        navigateToPage(Page.HISTORICO_VETERINARIO);
    }

    /**
     * Obtém a view de cavalos
     */
    public MeusCavalosView getMeusCavalosView() {
        return meusCavalosView;
    }
    
    /**
     * Obtém a view de dashboard
     */
    public DashboardView getDashboardView() {
        return dashboardView;
    }
    
    /**
     * Obtém a view de marketplace
     */
    public MarketplaceView getMarketplaceView() {
        return marketplaceView;
    }
    
    /**
     * Obtém a view de agenda
     */
    public AgendaView getAgendaView() {
        return agendaView;
    }
    
    /**
     * Obtém a view de histórico veterinário
     */
    public HistoricoVeterinarioView getHistoricoVeterinarioView() {
        return historicoVeterinarioView;
    }
}
