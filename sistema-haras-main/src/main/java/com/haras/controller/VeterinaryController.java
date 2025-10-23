package com.haras.controller;

import com.haras.model.ConsultaVeterinaria;
import com.haras.model.Equino;
import com.haras.view.HistoricoVeterinarioView;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinaryController {
    private static VeterinaryController instance;
    private final List<ConsultaVeterinaria> consultas;
    private HistoricoVeterinarioView view;

    private VeterinaryController() {
        this.consultas = new ArrayList<>();
    }

    public static VeterinaryController getInstance() {
        if (instance == null) {
            instance = new VeterinaryController();
        }
        return instance;
    }

    public void setView(HistoricoVeterinarioView view) {
        this.view = view;
    }

    public JPanel getView() {
        if (view == null) {
            view = new HistoricoVeterinarioView(this);
        }
        view.atualizarConsultas(consultas);
        return view.getContentPanel();
    }

    public List<ConsultaVeterinaria> getConsultas() {
        return new ArrayList<>(consultas);
    }

    public List<Equino> getCavalos() {
        // Obter cavalos do EquinoController
        return EquinoController.getInstance().listarEquinos();
    }

    public void novaConsulta() {
        if (view != null) {
            view.mostrarFormularioNovaConsulta();
        }
    }

    public void createNewConsultationDialog(Component parent) {
        if (view != null) {
            view.mostrarFormularioNovaConsulta();
        } else {
            // Criar view temporária se não existir
            HistoricoVeterinarioView tempView = new HistoricoVeterinarioView(this);
            tempView.mostrarFormularioNovaConsulta();
        }
    }

    public void salvarConsulta(ConsultaVeterinaria consulta) {
        if (consulta != null) {
            consultas.add(consulta);
            if (view != null) {
                view.atualizarConsultas(consultas);
            }
            JOptionPane.showMessageDialog(null,
                "Consulta registrada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void visualizarConsulta(ConsultaVeterinaria consulta) {
        if (consulta != null && view != null) {
            view.mostrarDetalhesConsulta(consulta);
        }
    }

    public void excluirConsulta(String consultaId) {
        int confirm = JOptionPane.showConfirmDialog(null,
            "Deseja realmente excluir esta consulta?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            consultas.removeIf(c -> c.getId().equals(consultaId));
            if (view != null) {
                view.atualizarConsultas(consultas);
            }
            JOptionPane.showMessageDialog(null,
                "Consulta excluída com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}