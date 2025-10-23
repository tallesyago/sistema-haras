package com.haras.view;

import com.haras.controller.AgendaController;
import com.haras.model.Evento;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AgendaView {
    private JPanel contentPanel;
    private JPanel centerPanel;
    private JButton novoEventoButton;
    private AgendaController controller;

    public AgendaView(AgendaController controller) {
        this.controller = controller;
        initialize();
        // Registrar esta view no controller
        controller.setView(this);
    }

    private void initialize() {
        contentPanel = new JPanel(new BorderLayout(0, 20));
        contentPanel.setBackground(new Color(248, 250, 252));
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Header
        JPanel headerPanel = createHeaderPanel();
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Centro
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createLineBorder(new Color(229, 231, 235)));
        
        contentPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel headerContainer = new JPanel(new BorderLayout(0, 10));
        headerContainer.setBackground(new Color(248, 250, 252));
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(248, 250, 252));
        
        // Título
        JLabel titleLabel = new JLabel("Agenda de Eventos");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(31, 41, 55));
        
        // Botão Novo Evento
        novoEventoButton = new JButton("+ Novo Evento");
        novoEventoButton.setBackground(new Color(16, 185, 129));
        novoEventoButton.setForeground(Color.WHITE);
        novoEventoButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        novoEventoButton.setBorder(new EmptyBorder(12, 24, 12, 24));
        novoEventoButton.setFocusPainted(false);
        novoEventoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        novoEventoButton.addActionListener(e -> controller.createNewEvent());
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(novoEventoButton, BorderLayout.EAST);
        
        // Subtítulo
        JLabel subtitleLabel = new JLabel("Gerencie seus compromissos");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(107, 114, 128));
        
        headerContainer.add(headerPanel, BorderLayout.NORTH);
        headerContainer.add(subtitleLabel, BorderLayout.SOUTH);
        
        return headerContainer;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void atualizarEventos(List<Evento> eventos) {
        centerPanel.removeAll();
        
        if (eventos.isEmpty()) {
            centerPanel.add(getEmptyStatePanel(), BorderLayout.CENTER);
        } else {
            JPanel eventosListPanel = createEventosListPanel(eventos);
            centerPanel.add(eventosListPanel, BorderLayout.CENTER);
        }
        
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private JPanel createEventosListPanel(List<Evento> eventos) {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        for (Evento evento : eventos) {
            JPanel eventCard = createEventCard(evento);
            listPanel.add(eventCard);
            listPanel.add(Box.createVerticalStrut(15));
        }
        
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.add(scrollPane, BorderLayout.CENTER);
        
        return container;
    }

    private JPanel createEventCard(Evento evento) {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            new EmptyBorder(15, 20, 15, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Info do evento
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(evento.getTitulo());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(31, 41, 55));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataTexto = evento.getDataHoraInicio().format(formatter);
        if (evento.getDataHoraTermino() != null) {
            dataTexto += " - " + evento.getDataHoraTermino().format(formatter);
        }
        
        JLabel detailsLabel = new JLabel(evento.getTipo() + " • " + dataTexto);
        detailsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        detailsLabel.setForeground(new Color(107, 114, 128));
        
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(detailsLabel);
        
        // Botões de ação
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionsPanel.setBackground(Color.WHITE);
        
        JButton editButton = new JButton("Editar");
        editButton.setBackground(new Color(59, 130, 246));
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        editButton.setBorder(new EmptyBorder(8, 16, 8, 16));
        editButton.setFocusPainted(false);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(e -> controller.editEvent(evento.getId()));
        
        JButton deleteButton = new JButton("Excluir");
        deleteButton.setBackground(new Color(239, 68, 68));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        deleteButton.setBorder(new EmptyBorder(8, 16, 8, 16));
        deleteButton.setFocusPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(e -> controller.deleteEvent(evento.getId()));
        
        actionsPanel.add(editButton);
        actionsPanel.add(deleteButton);
        
        card.add(infoPanel, BorderLayout.CENTER);
        card.add(actionsPanel, BorderLayout.EAST);
        
        return card;
    }

    private JPanel getEmptyStatePanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
        emptyPanel.setBackground(Color.WHITE);
        emptyPanel.setBorder(new EmptyBorder(100, 40, 100, 40));
        
        // Ícone
        JLabel iconLabel = new JLabel("📅");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        iconLabel.setForeground(new Color(209, 213, 219));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Mensagem principal
        JLabel emptyLabel = new JLabel("Nenhum evento agendado");
        emptyLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        emptyLabel.setForeground(new Color(107, 114, 128));
        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Mensagem secundária
        JLabel subLabel = new JLabel("Crie seu primeiro evento para começar");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subLabel.setForeground(new Color(156, 163, 175));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Botão criar evento
        JButton createButton = new JButton("+ Criar Evento");
        createButton.setBackground(new Color(16, 185, 129));
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        createButton.setBorder(new EmptyBorder(12, 28, 12, 28));
        createButton.setFocusPainted(false);
        createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.addActionListener(e -> controller.createNewEvent());
        
        emptyPanel.add(Box.createVerticalGlue());
        emptyPanel.add(iconLabel);
        emptyPanel.add(Box.createVerticalStrut(24));
        emptyPanel.add(emptyLabel);
        emptyPanel.add(Box.createVerticalStrut(8));
        emptyPanel.add(subLabel);
        emptyPanel.add(Box.createVerticalStrut(32));
        emptyPanel.add(createButton);
        emptyPanel.add(Box.createVerticalGlue());
        
        return emptyPanel;
    }

    public void showEventDialog(Evento eventoExistente) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(contentPanel), 
            eventoExistente == null ? "Novo Evento" : "Editar Evento", true);
        dialog.setSize(700, 700);
        dialog.setLocationRelativeTo(contentPanel);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        JLabel titleLabel = new JLabel(eventoExistente == null ? "Novo Evento" : "Editar Evento");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(31, 41, 55));
        
        JButton closeButton = new JButton("✕");
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        closeButton.setForeground(new Color(107, 114, 128));
        closeButton.setBackground(Color.WHITE);
        closeButton.setBorder(null);
        closeButton.setFocusPainted(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> dialog.dispose());
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(closeButton, BorderLayout.EAST);
        
        // Form Panel Container (para adicionar scroll)
        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.setBackground(Color.WHITE);
        
        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.weightx = 1.0;
        
        // Título
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel tituloLabel = new JLabel("Título *");
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tituloLabel.setForeground(new Color(31, 41, 55));
        formPanel.add(tituloLabel, gbc);
        
        gbc.gridy = 1;
        JTextField tituloField = createStyledTextField();
        if (eventoExistente != null) tituloField.setText(eventoExistente.getTitulo());
        formPanel.add(tituloField, gbc);
        
        // Descrição
        gbc.gridy = 2;
        JLabel descLabel = new JLabel("Descrição");
        descLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        descLabel.setForeground(new Color(31, 41, 55));
        formPanel.add(descLabel, gbc);
        
        gbc.gridy = 3;
        JTextArea descArea = new JTextArea(3, 20);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            new EmptyBorder(10, 12, 10, 12)
        ));
        if (eventoExistente != null && eventoExistente.getDescricao() != null) {
            descArea.setText(eventoExistente.getDescricao());
        }
        JScrollPane descScroll = new JScrollPane(descArea);
        descScroll.setBorder(descArea.getBorder());
        formPanel.add(descScroll, gbc);
        
        // Data e Hora de Início e Término
        gbc.gridwidth = 1;
        gbc.gridy = 4;
        gbc.gridx = 0;
        JLabel dataInicioLabel = new JLabel("Data e Hora de Início *");
        dataInicioLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dataInicioLabel.setForeground(new Color(31, 41, 55));
        formPanel.add(dataInicioLabel, gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        JLabel dataTerminoLabel = new JLabel("Data e Hora de Término *");
        dataTerminoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dataTerminoLabel.setForeground(new Color(31, 41, 55));
        formPanel.add(dataTerminoLabel, gbc);
        
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        JTextField dataInicioField = createStyledTextField();
        dataInicioField.setText("dd/mm/aaaa --:--");
        dataInicioField.setForeground(new Color(156, 163, 175));
        if (eventoExistente != null && eventoExistente.getDataHoraInicio() != null) {
            dataInicioField.setText(eventoExistente.getDataHoraInicio().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            dataInicioField.setForeground(new Color(31, 41, 55));
        }
        formPanel.add(dataInicioField, gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        JTextField dataTerminoField = createStyledTextField();
        dataTerminoField.setText("dd/mm/aaaa --:--");
        dataTerminoField.setForeground(new Color(156, 163, 175));
        if (eventoExistente != null && eventoExistente.getDataHoraTermino() != null) {
            dataTerminoField.setText(eventoExistente.getDataHoraTermino().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            dataTerminoField.setForeground(new Color(31, 41, 55));
        }
        formPanel.add(dataTerminoField, gbc);
        
        // Tipo de Evento e Cavalo
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        JLabel tipoLabel = new JLabel("Tipo de Evento *");
        tipoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tipoLabel.setForeground(new Color(31, 41, 55));
        formPanel.add(tipoLabel, gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        JLabel cavaloLabel = new JLabel("Cavalo");
        cavaloLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cavaloLabel.setForeground(new Color(31, 41, 55));
        formPanel.add(cavaloLabel, gbc);
        
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        JComboBox<String> tipoCombo = createStyledComboBox(
            new String[]{"Treino", "Competição", "Consulta Veterinária", "Outro"});
        if (eventoExistente != null) tipoCombo.setSelectedItem(eventoExistente.getTipo());
        formPanel.add(tipoCombo, gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 20, 0);
        JComboBox<String> cavaloCombo = createStyledComboBox(
            new String[]{"Selecione um cavalo", "Thunder", "Bella", "Apollo"});
        formPanel.add(cavaloCombo, gbc);
        
        // Local
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        JLabel localLabel = new JLabel("Local");
        localLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        localLabel.setForeground(new Color(31, 41, 55));
        formPanel.add(localLabel, gbc);
        
        gbc.gridy = 9;
        JTextField localField = createStyledTextField();
        if (eventoExistente != null && eventoExistente.getLocal() != null) {
            localField.setText(eventoExistente.getLocal());
        }
        formPanel.add(localField, gbc);
        
        // Observações
        gbc.gridy = 10;
        JLabel obsLabel = new JLabel("Observações");
        obsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        obsLabel.setForeground(new Color(31, 41, 55));
        formPanel.add(obsLabel, gbc);
        
        gbc.gridy = 11;
        JTextArea obsArea = new JTextArea(3, 20);
        obsArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        obsArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            new EmptyBorder(10, 12, 10, 12)
        ));
        if (eventoExistente != null && eventoExistente.getObservacoes() != null) {
            obsArea.setText(eventoExistente.getObservacoes());
        }
        JScrollPane obsScroll = new JScrollPane(obsArea);
        obsScroll.setBorder(obsArea.getBorder());
        formPanel.add(obsScroll, gbc);
        
        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setForeground(new Color(107, 114, 128));
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            new EmptyBorder(10, 20, 10, 20)
        ));
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> dialog.dispose());
        
        JButton saveButton = new JButton(eventoExistente == null ? "Criar Evento" : "Salvar Alterações");
        saveButton.setBackground(new Color(16, 185, 129));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        saveButton.setFocusPainted(false);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(e -> {
            if (tituloField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "O título é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime dataInicio = LocalDateTime.parse(dataInicioField.getText(), formatter);
                LocalDateTime dataTermino = LocalDateTime.parse(dataTerminoField.getText(), formatter);
                
                Evento evento = eventoExistente != null ? eventoExistente : new Evento(null, "", "", dataInicio, "Agendado");
                evento.setTitulo(tituloField.getText().trim());
                evento.setDescricao(descArea.getText().trim());
                evento.setDataHoraInicio(dataInicio);
                evento.setDataHoraTermino(dataTermino);
                evento.setTipo((String) tipoCombo.getSelectedItem());
                evento.setLocal(localField.getText().trim());
                evento.setObservacoes(obsArea.getText().trim());
                
                controller.saveEvent(evento);
                dialog.dispose();
                
                JOptionPane.showMessageDialog(contentPanel, 
                    eventoExistente == null ? "Evento criado com sucesso!" : "Evento atualizado com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(dialog, 
                    "Formato de data/hora inválido! Use: dd/MM/yyyy HH:mm", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        
        // Adicionar scroll ao formulário
        JScrollPane formScrollPane = new JScrollPane(formPanel);
        formScrollPane.setBorder(null);
        formScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        formScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        formContainer.add(formScrollPane, BorderLayout.CENTER);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formContainer, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            new EmptyBorder(10, 12, 10, 12)
        ));
        return field;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            new EmptyBorder(10, 12, 10, 12)
        ));
        return combo;
    }
}
