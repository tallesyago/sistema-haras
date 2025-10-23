package com.haras.controller;

import com.haras.model.Evento;
import com.haras.view.AgendaView;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controlador responsável por gerenciar os eventos da agenda
 */
public class AgendaController {
    private static AgendaController instance;
    private AgendaView view;
    private List<Evento> eventos;

    private AgendaController() {
        this.eventos = new ArrayList<>();
    }

    public static AgendaController getInstance() {
        if (instance == null) {
            instance = new AgendaController();
        }
        return instance;
    }

    public void setView(AgendaView view) {
        this.view = view;
        updateView();
    }

    public void createNewEvent() {
        if (view != null) {
            view.showEventDialog(null);
        }
    }

    public void editEvent(String eventId) {
        Evento evento = findEventById(eventId);
        if (evento != null && view != null) {
            view.showEventDialog(evento);
        }
    }

    public void deleteEvent(String eventId) {
        int result = JOptionPane.showConfirmDialog(null,
            "Tem certeza que deseja remover este evento?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            eventos.removeIf(e -> e.getId() != null && e.getId().equals(eventId));
            updateView();
            JOptionPane.showMessageDialog(null,
                "Evento removido com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void saveEvent(Evento evento) {
        if (evento.getId() == null || evento.getId().isEmpty()) {
            // Novo evento
            evento.setId(UUID.randomUUID().toString());
            eventos.add(evento);
        } else {
            // Atualizar evento existente
            for (int i = 0; i < eventos.size(); i++) {
                if (eventos.get(i).getId().equals(evento.getId())) {
                    eventos.set(i, evento);
                    break;
                }
            }
        }
        updateView();
    }

    private Evento findEventById(String id) {
        return eventos.stream()
            .filter(e -> e.getId() != null && e.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public List<Evento> getAllEvents() {
        return new ArrayList<>(eventos);
    }

    private void updateView() {
        if (view != null) {
            view.atualizarEventos(eventos);
        }
    }

    public JPanel getView() {
        return view != null ? view.getContentPanel() : null;
    }
}
