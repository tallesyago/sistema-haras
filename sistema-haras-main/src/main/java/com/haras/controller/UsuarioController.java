package com.haras.controller;

import com.haras.model.Usuario;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller responsável pelo gerenciamento de usuários e autenticação
 */
public class UsuarioController {
    private static UsuarioController instance;
    private final NavigationController navigationController;
    private final Map<String, Usuario> usuariosLogados;
    
    private UsuarioController() {
        this.navigationController = NavigationController.getInstance();
        this.usuariosLogados = new HashMap<>();
    }
    
    public static UsuarioController getInstance() {
        if (instance == null) {
            instance = new UsuarioController();
        }
        return instance;
    }
    
    /**
     * Realiza o login do usuário no sistema
     */
    public boolean realizarLogin(String email, String senha) {
        // TODO: Implementar validação com banco de dados
        try {
            Usuario usuario = buscarUsuario(email);
            if (usuario != null && validarSenha(usuario, senha)) {
                usuariosLogados.put(email, usuario);
                navigationController.setLoggedUser(usuario);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Realiza o logout do usuário
     */
    public void realizarLogout(String email) {
        usuariosLogados.remove(email);
        navigationController.setLoggedUser(null);
    }
    
    private Usuario buscarUsuario(String email) {
        // TODO: Implementar busca no banco de dados
        return null;
    }
    
    private boolean validarSenha(Usuario usuario, String senha) {
        // TODO: Implementar validação de senha
        return false;
    }
}