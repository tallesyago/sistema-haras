package com.haras.controller;

import javax.swing.JPanel;
import com.haras.view.pages.*;
import com.haras.model.Usuario;
import com.haras.view.BaseView;

/**
 * Controller responsável pela navegação entre páginas e gerenciamento dos controladores específicos
 */
public class NavigationController {
    private static NavigationController instance;
    private Usuario loggedUser;
    private BaseView currentView;
    
    // Controladores específicos
    private AgendaController agendaController;
    private AtendimentoController atendimentoController;
    private ClienteController clienteController;
    private EquinoController equinoController;
    private MarketplaceController marketplaceController;
    private VeterinaryController veterinaryController;
    
    private NavigationController() {
        initializeControllers();
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
        agendaController = AgendaController.getInstance();
        atendimentoController = AtendimentoController.getInstance();
        clienteController = ClienteController.getInstance();
        equinoController = EquinoController.getInstance();
        marketplaceController = MarketplaceController.getInstance();
        veterinaryController = VeterinaryController.getInstance();
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
            switch (page) {
                case DASHBOARD:
                    return new DashboardView().getContentPanel();
                case MEUS_CAVALOS:
                    // Preferir painel fornecido pelo controller
                    if (equinoController != null) return equinoController.getView();
                    return new MeusCavalosView().getContentPanel();
                case MARKETPLACE:
                    if (marketplaceController != null) return marketplaceController.getView();
                    return new MarketplaceView().getContentPanel();
                case AGENDA:
                    if (agendaController != null) return agendaController.getView();
                    return new AgendaView().getContentPanel();
                case HISTORICO_VETERINARIO:
                    return new HistoricoVeterinarioView().getContentPanel();
                default:
                    return new DashboardView().getContentPanel();
            }
        } catch (Exception e) {
            return createErrorPanel("Erro ao carregar página: " + e.getMessage());
        }
    }
    
    /**
     * Cria um painel de erro em caso de falha
     */
    private JPanel createErrorPanel(String errorMessage) {
        JPanel panel = new JPanel();
        panel.setBackground(new java.awt.Color(255, 245, 245));
        // Implementação básica de erro mantendo o estilo
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
        // Implementação simples: alternar usuário logado para null ou manter (pode ser aprimorada)
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
}
