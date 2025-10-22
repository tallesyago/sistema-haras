package com.haras.controller;

import com.haras.view.pages.VeterinaryDashboardView;
import javax.swing.*;

public class VeterinaryController {
    private static VeterinaryController instance;
    private VeterinaryDashboardView view;
    
    private VeterinaryController() {
        this.view = new VeterinaryDashboardView();
        initializeActions();
    }
    
    public static VeterinaryController getInstance() {
        if (instance == null) {
            instance = new VeterinaryController();
        }
        return instance;
    }
    
    private void initializeActions() {
        // Implementar ações específicas do veterinário
    }
    
    public JPanel getView() {
        return view.getContentPanel();
    }
    
    public void createNewConsultation() {
        JOptionPane.showMessageDialog(null, 
            "Nova Consulta - Funcionalidade em desenvolvimento!", 
            "Sistema Veterinário", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Controller-owned dialog to create a new consultation (keeps MVC)
     */
    public void createNewConsultationDialog(java.awt.Component parent) {
        try {
            String descricao = javax.swing.JOptionPane.showInputDialog(parent, "Descrição da consulta:", "Nova Consulta", javax.swing.JOptionPane.QUESTION_MESSAGE);
            if (descricao == null || descricao.trim().isEmpty()) return;

            // Hoje simulamos criação — registrar lógica aqui
            javax.swing.JOptionPane.showMessageDialog(parent, "Consulta criada: " + descricao, "Sucesso", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            // Após criar, solicitar refresh da view
            try {
                com.haras.controller.NavigationController nav = com.haras.controller.NavigationController.getInstance();
                if (nav.getCurrentView() != null) {
                    nav.getCurrentView().showDashboard();
                }
            } catch (Exception ex) {
                // ignore
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(parent, "Erro: " + e.getMessage(), "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void viewTodayConsultations() {
        JOptionPane.showMessageDialog(null, 
            "Consultas de Hoje - Funcionalidade em desenvolvimento!", 
            "Sistema Veterinário", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void viewPatients() {
        JOptionPane.showMessageDialog(null, 
            "Lista de Pacientes - Funcionalidade em desenvolvimento!", 
            "Sistema Veterinário", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void refreshVeterinaryStats() {
        // Atualizar estatísticas veterinárias
    }
}