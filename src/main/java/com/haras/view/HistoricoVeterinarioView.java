package com.haras.view;

import com.haras.controller.VeterinaryController;
import com.haras.model.ConsultaVeterinaria;
import com.haras.model.Equino;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoricoVeterinarioView {
    private JPanel contentPanel;
    private JPanel centerPanel;
    private VeterinaryController controller;
    private JDialog dialogNovaConsulta;

    public HistoricoVeterinarioView(VeterinaryController controller) {
        this.controller = controller;
        controller.setView(this);
        initialize();
    }

    public HistoricoVeterinarioView() {
        this.controller = VeterinaryController.getInstance();
        controller.setView(this);
        initialize();
    }

    private void initialize() {
        contentPanel = new JPanel(new BorderLayout(0, 20));
        contentPanel.setBackground(new Color(248, 250, 252));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Header
        JPanel headerPanel = createHeaderPanel();
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // Centro
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        contentPanel.add(centerPanel, BorderLayout.CENTER);

        atualizarConsultas(controller.getConsultas());
    }

    private JPanel createHeaderPanel() {
        JPanel headerContainer = new JPanel(new BorderLayout(0, 10));
        headerContainer.setBackground(new Color(248, 250, 252));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(248, 250, 252));

        // Título
        JLabel titleLabel = new JLabel("Histórico Veterinário");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(31, 41, 55));

        // Botão Nova Consulta
        JButton novaConsultaButton = new JButton("+ Nova Consulta");
        novaConsultaButton.setBackground(new Color(37, 99, 235));
        novaConsultaButton.setForeground(Color.WHITE);
        novaConsultaButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        novaConsultaButton.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        novaConsultaButton.setFocusPainted(false);
        novaConsultaButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        novaConsultaButton.addActionListener(e -> controller.novaConsulta());

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(novaConsultaButton, BorderLayout.EAST);

        // Subtítulo
        JLabel subtitleLabel = new JLabel("Consultas e tratamentos dos pacientes");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(107, 114, 128));

        headerContainer.add(headerPanel, BorderLayout.NORTH);
        headerContainer.add(subtitleLabel, BorderLayout.SOUTH);

        return headerContainer;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void atualizarConsultas(List<ConsultaVeterinaria> consultas) {
        centerPanel.removeAll();
        
        if (consultas.isEmpty()) {
            centerPanel.add(getEmptyStatePanel(), BorderLayout.CENTER);
        } else {
            centerPanel.add(createConsultasListPanel(consultas), BorderLayout.CENTER);
        }
        
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private JPanel getEmptyStatePanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
        emptyPanel.setBackground(Color.WHITE);
        emptyPanel.setBorder(BorderFactory.createEmptyBorder(80, 20, 80, 20));

        // Ícone
        JLabel iconLabel = new JLabel("🩺");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mensagem principal
        JLabel emptyLabel = new JLabel("Nenhuma consulta registrada");
        emptyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        emptyLabel.setForeground(new Color(107, 114, 128));
        emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mensagem secundária
        JLabel subLabel = new JLabel("Registre a primeira consulta para começar o histórico");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subLabel.setForeground(new Color(156, 163, 175));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão criar consulta
        JButton createButton = new JButton("+ Registrar Primeira Consulta");
        createButton.setBackground(new Color(37, 99, 235));
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        createButton.setBorder(BorderFactory.createEmptyBorder(12, 28, 12, 28));
        createButton.setFocusPainted(false);
        createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.addActionListener(e -> controller.novaConsulta());

        emptyPanel.add(Box.createVerticalGlue());
        emptyPanel.add(iconLabel);
        emptyPanel.add(Box.createVerticalStrut(20));
        emptyPanel.add(emptyLabel);
        emptyPanel.add(Box.createVerticalStrut(8));
        emptyPanel.add(subLabel);
        emptyPanel.add(Box.createVerticalStrut(24));
        emptyPanel.add(createButton);
        emptyPanel.add(Box.createVerticalGlue());

        return emptyPanel;
    }

    private JScrollPane createConsultasListPanel(List<ConsultaVeterinaria> consultas) {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (ConsultaVeterinaria consulta : consultas) {
            listPanel.add(createConsultaCard(consulta));
            listPanel.add(Box.createVerticalStrut(12));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        return scrollPane;
    }

    private JPanel createConsultaCard(ConsultaVeterinaria consulta) {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Info principal
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nomeLabel = new JLabel(consulta.getCavalo() != null ? consulta.getCavalo().getNome() : "Cavalo");
        nomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nomeLabel.setForeground(new Color(31, 41, 55));

        JLabel tipoLabel = new JLabel(consulta.getTipoConsulta() + " • " + 
            consulta.getDataConsulta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tipoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tipoLabel.setForeground(new Color(107, 114, 128));

        infoPanel.add(nomeLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(tipoLabel);

        // Botões de ação
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        actionsPanel.setBackground(Color.WHITE);

        JButton viewButton = new JButton("Ver Detalhes");
        viewButton.setBackground(new Color(37, 99, 235));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        viewButton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        viewButton.setFocusPainted(false);
        viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewButton.addActionListener(e -> controller.visualizarConsulta(consulta));

        actionsPanel.add(viewButton);

        card.add(infoPanel, BorderLayout.CENTER);
        card.add(actionsPanel, BorderLayout.EAST);

        return card;
    }

    public void mostrarFormularioNovaConsulta() {
        dialogNovaConsulta = new JDialog((Frame) null, "Nova Consulta Veterinária", true);
        dialogNovaConsulta.setSize(800, 700);
        dialogNovaConsulta.setLocationRelativeTo(contentPanel);

        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Scroll para o formulário
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setBackground(Color.WHITE);

        // Campos do formulário
        JComboBox<String> cavaloCombo = createComboBox("Cavalo *");
        List<Equino> cavalos = controller.getCavalos();
        cavaloCombo.addItem("Selecione um cavalo");
        for (Equino cavalo : cavalos) {
            cavaloCombo.addItem(cavalo.getNome());
        }

        JTextField dataField = createDateField("Data da Consulta *");
        JComboBox<String> tipoCombo = createComboBox("Tipo de Consulta *");
        tipoCombo.addItem("Rotina");
        tipoCombo.addItem("Emergência");
        tipoCombo.addItem("Acompanhamento");
        tipoCombo.addItem("Vacinação");

        JTextField pesoField = createTextField("Peso Atual (kg)");
        JTextField temperaturaField = createTextField("Temperatura (°C)");
        JTextField pressaoField = createTextField("Pressão Arterial", "Ex: 120/80");
        JTextArea sintomasArea = createTextArea("Sintomas Relatados");
        JTextArea diagnosticoArea = createTextArea("Diagnóstico");
        JTextArea tratamentoArea = createTextArea("Tratamento Prescrito");
        JTextArea observacoesArea = createTextArea("Observações Adicionais");
        JTextField proximaConsultaField = createDateField("Próxima Consulta");

        fieldsPanel.add(createFieldPanel("Cavalo *", cavaloCombo));
        fieldsPanel.add(createFieldPanel("Data da Consulta *", dataField));
        fieldsPanel.add(createFieldPanel("Tipo de Consulta *", tipoCombo));
        fieldsPanel.add(createFieldPanel("Peso Atual (kg)", pesoField));
        fieldsPanel.add(createFieldPanel("Temperatura (°C)", temperaturaField));
        fieldsPanel.add(createFieldPanel("Pressão Arterial", pressaoField));
        
        JScrollPane sintomasScroll = new JScrollPane(sintomasArea);
        sintomasScroll.setPreferredSize(new Dimension(0, 100));
        fieldsPanel.add(createFieldPanel("Sintomas Relatados", sintomasScroll));
        
        JScrollPane diagnosticoScroll = new JScrollPane(diagnosticoArea);
        diagnosticoScroll.setPreferredSize(new Dimension(0, 100));
        fieldsPanel.add(createFieldPanel("Diagnóstico", diagnosticoScroll));
        
        JScrollPane tratamentoScroll = new JScrollPane(tratamentoArea);
        tratamentoScroll.setPreferredSize(new Dimension(0, 100));
        fieldsPanel.add(createFieldPanel("Tratamento Prescrito", tratamentoScroll));
        
        JScrollPane observacoesScroll = new JScrollPane(observacoesArea);
        observacoesScroll.setPreferredSize(new Dimension(0, 100));
        fieldsPanel.add(createFieldPanel("Observações Adicionais", observacoesScroll));
        
        fieldsPanel.add(createFieldPanel("Próxima Consulta", proximaConsultaField));

        JScrollPane scrollPane = new JScrollPane(fieldsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Botões
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
        buttonsPanel.setBackground(Color.WHITE);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(243, 244, 246));
        cancelButton.setForeground(new Color(75, 85, 99));
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> dialogNovaConsulta.dispose());

        JButton salvarButton = new JButton("Salvar Consulta");
        salvarButton.setBackground(new Color(37, 99, 235));
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        salvarButton.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        salvarButton.setFocusPainted(false);
        salvarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salvarButton.addActionListener(e -> {
            if (validarFormulario(cavaloCombo, dataField, tipoCombo)) {
                ConsultaVeterinaria consulta = criarConsultaFromForm(
                    cavalos, cavaloCombo, dataField, tipoCombo, pesoField,
                    temperaturaField, pressaoField, sintomasArea, diagnosticoArea,
                    tratamentoArea, observacoesArea, proximaConsultaField
                );
                controller.salvarConsulta(consulta);
                dialogNovaConsulta.dispose();
            }
        });

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(salvarButton);

        formPanel.add(scrollPane, BorderLayout.CENTER);
        formPanel.add(buttonsPanel, BorderLayout.SOUTH);

        dialogNovaConsulta.add(formPanel);
        dialogNovaConsulta.setVisible(true);
    }

    private JPanel createFieldPanel(String label, Component field) {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jLabel.setForeground(new Color(55, 65, 81));

        panel.add(jLabel, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private JTextField createTextField(String placeholder) {
        return createTextField(placeholder, placeholder);
    }

    private JTextField createTextField(String placeholder, String hint) {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JTextField createDateField(String placeholder) {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JComboBox<String> createComboBox(String placeholder) {
        JComboBox<String> combo = new JComboBox<>();
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        return combo;
    }

    private JTextArea createTextArea(String placeholder) {
        JTextArea area = new JTextArea(4, 20);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return area;
    }

    private boolean validarFormulario(JComboBox<String> cavaloCombo, JTextField dataField, JComboBox<String> tipoCombo) {
        if (cavaloCombo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(dialogNovaConsulta, "Selecione um cavalo", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (dataField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(dialogNovaConsulta, "Informe a data da consulta", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private ConsultaVeterinaria criarConsultaFromForm(List<Equino> cavalos, JComboBox<String> cavaloCombo,
            JTextField dataField, JComboBox<String> tipoCombo, JTextField pesoField,
            JTextField temperaturaField, JTextField pressaoField, JTextArea sintomasArea,
            JTextArea diagnosticoArea, JTextArea tratamentoArea, JTextArea observacoesArea,
            JTextField proximaConsultaField) {
        
        ConsultaVeterinaria consulta = new ConsultaVeterinaria();
        consulta.setCavalo(cavalos.get(cavaloCombo.getSelectedIndex() - 1));
        consulta.setDataConsulta(parseDate(dataField.getText()));
        consulta.setTipoConsulta((String) tipoCombo.getSelectedItem());
        
        try {
            if (!pesoField.getText().trim().isEmpty()) {
                consulta.setPesoAtual(Double.parseDouble(pesoField.getText()));
            }
        } catch (NumberFormatException e) {}
        
        consulta.setTemperatura(temperaturaField.getText());
        consulta.setPressaoArterial(pressaoField.getText());
        consulta.setSintomasRelatados(sintomasArea.getText());
        consulta.setDiagnostico(diagnosticoArea.getText());
        consulta.setTratamentoPrescrito(tratamentoArea.getText());
        consulta.setObservacoesAdicionais(observacoesArea.getText());
        
        if (!proximaConsultaField.getText().trim().isEmpty()) {
            consulta.setProximaConsulta(parseDate(proximaConsultaField.getText()));
        }
        
        return consulta;
    }

    private LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    public void mostrarDetalhesConsulta(ConsultaVeterinaria consulta) {
        JDialog dialog = new JDialog((Frame) null, "Detalhes da Consulta", true);
        dialog.setSize(700, 600);
        dialog.setLocationRelativeTo(contentPanel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Adicionar detalhes
        panel.add(createDetailLabel("Cavalo:", consulta.getCavalo() != null ? consulta.getCavalo().getNome() : "-"));
        panel.add(createDetailLabel("Data:", consulta.getDataConsulta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        panel.add(createDetailLabel("Tipo:", consulta.getTipoConsulta()));
        
        if (consulta.getPesoAtual() > 0) {
            panel.add(createDetailLabel("Peso:", consulta.getPesoAtual() + " kg"));
        }
        if (consulta.getTemperatura() != null && !consulta.getTemperatura().isEmpty()) {
            panel.add(createDetailLabel("Temperatura:", consulta.getTemperatura() + " °C"));
        }
        if (consulta.getPressaoArterial() != null && !consulta.getPressaoArterial().isEmpty()) {
            panel.add(createDetailLabel("Pressão:", consulta.getPressaoArterial()));
        }
        if (consulta.getSintomasRelatados() != null && !consulta.getSintomasRelatados().isEmpty()) {
            panel.add(createDetailLabel("Sintomas:", consulta.getSintomasRelatados()));
        }
        if (consulta.getDiagnostico() != null && !consulta.getDiagnostico().isEmpty()) {
            panel.add(createDetailLabel("Diagnóstico:", consulta.getDiagnostico()));
        }
        if (consulta.getTratamentoPrescrito() != null && !consulta.getTratamentoPrescrito().isEmpty()) {
            panel.add(createDetailLabel("Tratamento:", consulta.getTratamentoPrescrito()));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JButton fecharButton = new JButton("Fechar");
        fecharButton.setBackground(new Color(37, 99, 235));
        fecharButton.setForeground(Color.WHITE);
        fecharButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        fecharButton.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        fecharButton.setFocusPainted(false);
        fecharButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(fecharButton);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private JPanel createDetailLabel(String label, String value) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelComp.setForeground(new Color(55, 65, 81));

        JLabel valueComp = new JLabel(value);
        valueComp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        valueComp.setForeground(new Color(107, 114, 128));

        panel.add(labelComp, BorderLayout.NORTH);
        panel.add(valueComp, BorderLayout.CENTER);

        return panel;
    }
}
