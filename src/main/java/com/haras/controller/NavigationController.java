package com.haras.controller;

import javax.swing.JPanel;
import com.haras.view.pages.*;

/**
 * Controller responsável pela navegação entre páginas
 */
public class NavigationController {
    
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
        switch (page) {
            case DASHBOARD:
                return new DashboardView().getContentPanel();
            case MEUS_CAVALOS:
                return new MeusCavalosView().getContentPanel();
            case MARKETPLACE:
                return new MarketplaceView().getContentPanel();
            case AGENDA:
                return new AgendaView().getContentPanel();
            case HISTORICO_VETERINARIO:
                return new HistoricoVeterinarioView().getContentPanel();
            default:
                return new DashboardView().getContentPanel();
        }
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
}
