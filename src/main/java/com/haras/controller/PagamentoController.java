package com.haras.controller;

import com.haras.model.Venda;
import com.haras.model.Pagamento;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsável pelo gerenciamento de pagamentos
 */
public class PagamentoController {
    private static PagamentoController instance;
    private final List<Pagamento> pagamentos;
    
    private PagamentoController() {
        this.pagamentos = new ArrayList<>();
    }
    
    public static PagamentoController getInstance() {
        if (instance == null) {
            instance = new PagamentoController();
        }
        return instance;
    }
    
    /**
     * Realiza o pagamento de uma venda com cartão
     */
    public boolean realizarPagamentoCartao(Venda venda, String numeroCartao, String titular) {
        if (venda != null && "Confirmada".equals(venda.getStatus())) {
            Pagamento pagamento = new Pagamento();
            pagamento.setVenda(venda);
            pagamento.setValor(venda.getValor());
            pagamento.setMetodo("Cartão");
            pagamento.setStatus("Processando");
            
            // TODO: Implementar integração com gateway de pagamento
            
            pagamento.setStatus("Aprovado");
            pagamentos.add(pagamento);
            return true;
        }
        return false;
    }
    
    /**
     * Realiza o pagamento de uma venda com PIX
     */
    public boolean realizarPagamentoPix(Venda venda) {
        if (venda != null && "Confirmada".equals(venda.getStatus())) {
            Pagamento pagamento = new Pagamento();
            pagamento.setVenda(venda);
            pagamento.setValor(venda.getValor());
            pagamento.setMetodo("PIX");
            pagamento.setStatus("Aguardando");
            
            // TODO: Implementar geração de QR Code PIX
            
            pagamentos.add(pagamento);
            return true;
        }
        return false;
    }
    
    /**
     * Verifica o status de um pagamento
     */
    public String verificarStatusPagamento(Venda venda) {
        return pagamentos.stream()
            .filter(p -> p.getVenda().equals(venda))
            .findFirst()
            .map(Pagamento::getStatus)
            .orElse("Não encontrado");
    }
    
    /**
     * Lista todos os pagamentos
     */
    public List<Pagamento> listarPagamentos() {
        return new ArrayList<>(pagamentos);
    }
}