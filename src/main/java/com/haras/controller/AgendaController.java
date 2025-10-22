package com.haras.controller;

import com.haras.model.Evento;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controlador responsável por gerenciar os eventos da agenda
 */
public class AgendaController {
    private static AgendaController instance;
    private final List<Evento> eventos;
    private boolean initialized = false;
    
    private AgendaController() {
        this.eventos = new ArrayList<>();
    }
    
    public static AgendaController getInstance() {
        if (instance == null) {
            instance = new AgendaController();
        }
        return instance;
    }
    
    private void initializeIfNeeded() {
        if (!initialized) {
            initialized = true;
            loadUserEvents();
        }
    }
    
    /**
     * Carrega os eventos do usuário atual
     */
    private void loadUserEvents() {
        // TODO: Implementar carregamento dos eventos do usuário do banco de dados
        // Por enquanto, apenas criar alguns eventos de exemplo
        Evento evento1 = new Evento(
            UUID.randomUUID().toString(),
            "Consulta Veterinária - Égua Malandra",
            "Consulta Veterinária",
            LocalDateTime.now().plusDays(1),
            "Agendado"
        );
        
        Evento evento2 = new Evento(
            UUID.randomUUID().toString(),
            "Treinamento - Preparação para Competição",
            "Treinamento",
            LocalDateTime.now().plusDays(3),
            "Agendado"
        );
        
        eventos.add(evento1);
        eventos.add(evento2);
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
                Evento novoEvento = new Evento(
                    UUID.randomUUID().toString(),
                    title.trim(),
                    type,
                    LocalDateTime.now(),
                    "Agendado"
                );
                
                eventos.add(novoEvento);
                refreshAgenda();
                
                JOptionPane.showMessageDialog(null, 
                    "Evento '" + title + "' criado com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void viewTodayEvents() {
        LocalDateTime hoje = LocalDateTime.now();
        List<Evento> eventosHoje = eventos.stream()
            .filter(e -> e.getDataHora().toLocalDate().equals(hoje.toLocalDate()))
            .collect(Collectors.toList());
            
        if (eventosHoje.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Não há eventos agendados para hoje.", 
                "Agenda", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder mensagem = new StringBuilder("Eventos de Hoje:\n\n");
            for (Evento evento : eventosHoje) {
                mensagem.append(String.format("- %s (%s) - %s\n", 
                    evento.getTitulo(), 
                    evento.getTipo(), 
                    evento.getStatus()));
            }
            
            JOptionPane.showMessageDialog(null, 
                mensagem.toString(), 
                "Agenda", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void viewUpcomingEvents() {
        LocalDateTime agora = LocalDateTime.now();
        List<Evento> proximosEventos = eventos.stream()
            .filter(e -> e.getDataHora().isAfter(agora))
            .collect(Collectors.toList());
            
        if (proximosEventos.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Não há eventos futuros agendados.", 
                "Agenda", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder mensagem = new StringBuilder("Próximos Eventos:\n\n");
            for (Evento evento : proximosEventos) {
                mensagem.append(String.format("- %s (%s) - %s\n", 
                    evento.getTitulo(), 
                    evento.getTipo(), 
                    evento.getDataHora().toString()));
            }
            
            JOptionPane.showMessageDialog(null, 
                mensagem.toString(), 
                "Agenda", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void editEvent(String eventId) {
        Evento evento = eventos.stream()
            .filter(e -> e.getId().equals(eventId))
            .findFirst()
            .orElse(null);
            
        if (evento != null) {
            String novoTitulo = JOptionPane.showInputDialog(null, 
                "Novo título do evento:", 
                "Editar Evento", 
                JOptionPane.QUESTION_MESSAGE);
                
            if (novoTitulo != null && !novoTitulo.trim().isEmpty()) {
                evento.setTitulo(novoTitulo.trim());
                refreshAgenda();
                
                JOptionPane.showMessageDialog(null, 
                    "Evento atualizado com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void deleteEvent(String eventId) {
        int result = JOptionPane.showConfirmDialog(null, 
            "Tem certeza que deseja remover este evento?", 
            "Confirmar Exclusão", 
            JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            boolean removido = eventos.removeIf(e -> e.getId().equals(eventId));
            if (removido) {
                refreshAgenda();
                JOptionPane.showMessageDialog(null, 
                    "Evento removido com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void refreshAgenda() {
        // TODO: Implementar atualização da interface da agenda
    }
    
    /**
     * Retorna todos os eventos cadastrados
     */
    public List<Evento> getEventos() {
        initializeIfNeeded();
        return new ArrayList<>(eventos);
    }

    /**
     * Retorna um painel de visualização da agenda (delegado a view específica)
     */
    public javax.swing.JPanel getView() {
        // Atualmente a view da agenda é simples; mantemos separação criando/retornando um painel neutro.
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout());
        panel.setBackground(new java.awt.Color(248,250,252));
        panel.add(new javax.swing.JLabel("Agenda - Em desenvolvimento"), java.awt.BorderLayout.CENTER);
        return panel;
    }
}
