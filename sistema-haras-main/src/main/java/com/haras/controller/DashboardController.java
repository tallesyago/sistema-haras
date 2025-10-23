package com.haras.controller;

import com.haras.model.Equino;
import com.haras.model.Evento;
import com.haras.view.DashboardView;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public class DashboardController {
    private static DashboardController instance;
    private DashboardView dashboardView;
    
    private DashboardController() {
    }
    
    public static DashboardController getInstance() {
        if (instance == null) {
            instance = new DashboardController();
        }
        return instance;
    }
    
    public void setView(DashboardView view) {
        this.dashboardView = view;
        refreshDashboard();
    }
    
    public void refreshDashboard() {
        if (dashboardView != null) {
            SwingUtilities.invokeLater(() -> {
                try {
                    // Obter dados dos outros controllers
                    int totalCavalos = getTotalCavalos();
                    int cavalosAtivos = getCavalosAtivos();
                    int pendencias = getPendencias();
                    int eventosHoje = getEventosHoje();
                    int emTratamento = getCavalosEmTratamento();
                    int consultasRecentes = getConsultasRecentes();
                    
                    // Atualizar a view
                    dashboardView.atualizarDados(
                        totalCavalos,
                        cavalosAtivos,
                        pendencias,
                        eventosHoje,
                        emTratamento,
                        consultasRecentes
                    );
                } catch (Exception e) {
                    System.err.println("Erro ao atualizar dashboard: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }
    
    private int getTotalCavalos() {
        try {
            // Obter do MarketplaceController ou de um EquinoController
            List<Equino> equinos = MarketplaceController.getInstance().getEquinos();
            return equinos.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    private int getCavalosAtivos() {
        try {
            List<Equino> equinos = MarketplaceController.getInstance().getEquinos();
            // Considerar todos como ativos por enquanto
            // Pode adicionar uma propriedade "ativo" na classe Equino depois
            return equinos.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    private int getPendencias() {
        try {
            // Pode verificar aprovações pendentes, documentos, etc
            // Por enquanto retorna 0
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }
    
    private int getEventosHoje() {
        try {
            List<Evento> eventos = AgendaController.getInstance().getAllEvents();
            LocalDateTime hoje = LocalDateTime.now();
            
            return (int) eventos.stream()
                .filter(e -> e.getDataHoraInicio().toLocalDate().equals(hoje.toLocalDate()))
                .count();
        } catch (Exception e) {
            return 0;
        }
    }
    
    private int getCavalosEmTratamento() {
        try {
            // Por enquanto retorna 0
            // Pode integrar com um módulo de saúde/veterinário depois
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }
    
    private int getConsultasRecentes() {
        try {
            List<Evento> eventos = AgendaController.getInstance().getAllEvents();
            LocalDateTime umaSemanaAtras = LocalDateTime.now().minusDays(7);
            
            return (int) eventos.stream()
                .filter(e -> "Consulta Veterinária".equals(e.getTipo()))
                .filter(e -> e.getDataHoraInicio().isAfter(umaSemanaAtras))
                .count();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public JPanel getView() {
        if (dashboardView == null) {
            dashboardView = new DashboardView(this);
            refreshDashboard();
        }
        return dashboardView.getContentPanel();
    }
}
