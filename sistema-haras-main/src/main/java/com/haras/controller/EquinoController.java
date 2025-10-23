package com.haras.controller;

import com.haras.model.Equino;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EquinoController {
    private static EquinoController instance;
    private List<Equino> equinos;
    private int proximoId;
    
    private EquinoController() {
        this.equinos = new ArrayList<>();
        this.proximoId = 1;
        // Adicionar alguns dados de exemplo
        inicializarDadosExemplo();
    }
    
    public static EquinoController getInstance() {
        if (instance == null) {
            instance = new EquinoController();
        }
        return instance;
    }
    
    private void inicializarDadosExemplo() {
        adicionarEquino(new Equino(0, "Thunder", "Puro Sangue", "Macho", "Disponível", 50000.0));
        adicionarEquino(new Equino(0, "Star", "Quarto de Milha", "Fêmea", "Treinamento", 35000.0));
        adicionarEquino(new Equino(0, "Lightning", "Árabe", "Macho", "Venda", 45000.0));
    }
    
    public boolean adicionarEquino(Equino equino) {
        if (equino != null && equino.getNome() != null && !equino.getNome().trim().isEmpty()) {
            equino.setId(proximoId++);
            equinos.add(equino);
            return true;
        }
        return false;
    }
    
    public boolean removerEquino(int id) {
        return equinos.removeIf(e -> e.getId() == id);
    }
    
    public Equino buscarEquino(int id) {
        return equinos.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
    
    public List<Equino> buscarPorNome(String nome) {
        return equinos.stream()
                .filter(e -> e.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Equino> buscarPorStatus(String status) {
        return equinos.stream()
                .filter(e -> e.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
    
    public List<Equino> listarEquinos() {
        for (Equino equino : equinos) {
            if (equino.getDataNascimento() != null) {
                long idade = ChronoUnit.YEARS.between(equino.getDataNascimento(), LocalDate.now());
                equino.setIdade((int) idade);
            } else if (equino.getIdade() == 0) {
                equino.setIdade(1); // Default age if not set
            }
        }
        return new ArrayList<>(equinos);
    }
    
    public boolean atualizarEquino(Equino equino) {
        for (int i = 0; i < equinos.size(); i++) {
            if (equinos.get(i).getId() == equino.getId()) {
                equinos.set(i, equino);
                return true;
            }
        }
        return false;
    }
    
    public int getTotalEquinos() {
        return equinos.size();
    }

    /**
     * Retorna um painel de visualização dos equinos
     */
    public javax.swing.JPanel getView() {
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout());
        panel.setBackground(new java.awt.Color(248,250,252));
        panel.add(new javax.swing.JLabel("Meus Cavalos - Em desenvolvimento"), java.awt.BorderLayout.CENTER);
        return panel;
    }

    /**
     * Abre uma sequência de diálogos para adicionar um novo equino (UI interaction via controller)
     * Mantém o padrão MVC: a view delega a interação ao controller.
     */
    public void promptAndAddEquino(java.awt.Component parent) {
        try {
            String nome = javax.swing.JOptionPane.showInputDialog(parent, "Nome do Cavalo:", "Adicionar Cavalo", javax.swing.JOptionPane.QUESTION_MESSAGE);
            if (nome == null || nome.trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(parent, "Nome é obrigatório.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            String raca = javax.swing.JOptionPane.showInputDialog(parent, "Raça:", "Adicionar Cavalo", javax.swing.JOptionPane.QUESTION_MESSAGE);
            if (raca == null) raca = "Não informada";

            String idadeStr = javax.swing.JOptionPane.showInputDialog(parent, "Idade (apenas números):", "Adicionar Cavalo", javax.swing.JOptionPane.QUESTION_MESSAGE);
            int idade = 0;
            try {
                if (idadeStr != null && !idadeStr.trim().isEmpty()) {
                    idade = Integer.parseInt(idadeStr.trim());
                    if (idade < 0 || idade > 40) {
                        throw new NumberFormatException("Idade deve estar entre 0 e 40 anos");
                    }
                }
            } catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(parent, "Idade inválida. Use apenas números entre 0 e 40.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            String[] statusOptions = {"Disponível", "Treinamento", "Venda"};
            String status = (String) javax.swing.JOptionPane.showInputDialog(
                parent,
                "Status:",
                "Adicionar Cavalo",
                javax.swing.JOptionPane.QUESTION_MESSAGE,
                null,
                statusOptions,
                statusOptions[0]
            );
            if (status == null) status = "Disponível";

            String precoStr = javax.swing.JOptionPane.showInputDialog(parent, "Preço (apenas números):", "Adicionar Cavalo", javax.swing.JOptionPane.QUESTION_MESSAGE);
            double preco = 0.0;
            if (precoStr != null && !precoStr.trim().isEmpty()) {
                try {
                    preco = Double.parseDouble(precoStr.replaceAll("[^0-9\\.]", ""));
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(parent, "Preço inválido. Cavalo não adicionado.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Equino novo = new Equino();
            novo.setNome(nome.trim());
            novo.setRaca(raca.trim());
            novo.setGenero("Desconhecido");
            novo.setStatus(status.trim());
            novo.setPreco(preco);
            novo.setIdade(idade);

            boolean ok = adicionarEquino(novo);
            if (ok) {
                javax.swing.JOptionPane.showMessageDialog(parent, "Cavalo '" + nome + "' adicionado com sucesso!", "Sucesso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                try {
                    com.haras.controller.NavigationController nav = com.haras.controller.NavigationController.getInstance();
                    if (nav.getCurrentView() != null) {
                        nav.getCurrentView().showMeusCavalos();
                    }
                } catch (Exception ex) {
                    // não bloquear fluxo principal por falha ao atualizar UI
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(parent, "Falha ao adicionar cavalo.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(parent, "Erro ao adicionar cavalo: " + e.getMessage(), "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
