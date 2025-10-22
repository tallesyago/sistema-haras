package com.haras.controller;

import com.haras.model.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller responsável pela geração de relatórios do sistema
 */
public class RelatorioController {
    private static RelatorioController instance;
    private final VendaController vendaController;
    private final PagamentoController pagamentoController;
    
    private RelatorioController() {
        this.vendaController = VendaController.getInstance();
        this.pagamentoController = PagamentoController.getInstance();
    }
    
    public static RelatorioController getInstance() {
        if (instance == null) {
            instance = new RelatorioController();
        }
        return instance;
    }
    
    /**
     * Gera relatório de vendas por período
     */
    public String gerarRelatorioVendas(LocalDate inicio, LocalDate fim) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Vendas - Período: ").append(inicio).append(" a ").append(fim).append("\n\n");
        
        List<Venda> vendas = vendaController.listarVendas();
        double totalVendas = 0;
        
        for (Venda venda : vendas) {
            if (venda.getData().isAfter(inicio.minusDays(1)) && venda.getData().isBefore(fim.plusDays(1))) {
                relatorio.append(String.format("Venda #%d - %s - R$ %.2f - %s\n", 
                    venda.getId(), 
                    venda.getData(), 
                    venda.getValor(),
                    venda.getStatus()));
                totalVendas += venda.getValor();
            }
        }
        
        relatorio.append("\nTotal de vendas no período: R$ ").append(String.format("%.2f", totalVendas));
        return relatorio.toString();
    }
    
    /**
     * Gera relatório de atendimentos por veterinário
     */
    public String gerarRelatorioAtendimentos(Veterinario veterinario, LocalDate inicio, LocalDate fim) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Atendimentos - ").append(veterinario.getNome())
                .append("\nPeríodo: ").append(inicio).append(" a ").append(fim).append("\n\n");
        
        // TODO: Implementar busca de atendimentos por veterinário
        
        return relatorio.toString();
    }
    
    /**
     * Gera relatório financeiro
     */
    public String gerarRelatorioFinanceiro(LocalDate inicio, LocalDate fim) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório Financeiro - Período: ").append(inicio).append(" a ").append(fim).append("\n\n");
        
        List<Pagamento> pagamentos = pagamentoController.listarPagamentos();
        double totalReceitas = 0;
        
        relatorio.append("RECEITAS:\n");
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getDataHora().toLocalDate().isAfter(inicio.minusDays(1)) 
                && pagamento.getDataHora().toLocalDate().isBefore(fim.plusDays(1))) {
                
                relatorio.append(String.format("%s - R$ %.2f - %s - %s\n",
                    pagamento.getDataHora().toLocalDate(),
                    pagamento.getValor(),
                    pagamento.getMetodo(),
                    pagamento.getStatus()));
                    
                if ("Aprovado".equals(pagamento.getStatus())) {
                    totalReceitas += pagamento.getValor();
                }
            }
        }
        
        relatorio.append("\nTotal de receitas no período: R$ ").append(String.format("%.2f", totalReceitas));
        return relatorio.toString();
    }
}