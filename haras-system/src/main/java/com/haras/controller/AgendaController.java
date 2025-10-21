package com.haras.controller;

import com.haras.view.pages.AgendaView;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendaController {
    private AgendaView view;
    private List<Evento> eventos;
    
    public AgendaController() {
        this.eventos = new ArrayList<>();
        this.view = new AgendaView(this);
        initializeActions();
    }
    
    private void initializeActions() {
        // Implementar ações da agenda
    }
    
    public JPanel getView() {
        return view.getContentPanel();
    }
    
    public void createNewEvent() {
        String[] eventTypes = {"Consulta Veterinária", "Treinamento", "Competição", "Outros"};
        String type = (String) JOptionPane.showInputDialog(null, 
            "Tipo de Evento:", "Novo Evento", 
            JOptionPane.QUESTION_MESSAGE, null, eventTypes, eventTypes[0]);
        
        if (type != null) {
            String title = JOptionPane.showInputDialog(null, 
                "Título do Evento:", "Novo Evento", JOptionPane.QUESTION_MESSAGE);
            
            if (title != null && !title.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "Evento '" + title + "' será criado!\nFuncionalidade em desenvolvimento.", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void viewTodayEvents() {
        JOptionPane.showMessageDialog(null, 
            "Eventos de Hoje - Funcionalidade em desenvolvimento!", 
            "Agenda", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void viewUpcomingEvents() {
        JOptionPane.showMessageDialog(null, 
            "Próximos Eventos - Funcionalidade em desenvolvimento!", 
            "Agenda", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void editEvent(String eventId) {
        JOptionPane.showMessageDialog(null, 
            "Editar Evento - Funcionalidade em desenvolvimento!", 
            "Editar", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void deleteEvent(String eventId) {
        int result = JOptionPane.showConfirmDialog(null, 
            "Tem certeza que deseja remover este evento?", 
            "Confirmar Exclusão", 
            JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, 
                "Evento removido com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void refreshAgenda() {
        // Atualizar agenda
    }
    
    // Classe interna temporária para representar um evento
    private static class Evento {
        private String id;
        private String titulo;
        private String tipo;
        private LocalDateTime dataHora;
        private String status;
        
        public Evento(String id, String titulo, String tipo, LocalDateTime dataHora, String status) {
            this.id = id;
            this.titulo = titulo;
            this.tipo = tipo;
            this.dataHora = dataHora;
            this.status = status;
        }
        
        // Getters
        public String getId() { return id; }
        public String getTitulo() { return titulo; }
        public String getTipo() { return tipo; }
        public LocalDateTime getDataHora() { return dataHora; }
        public String getStatus() { return status; }
    }
}
