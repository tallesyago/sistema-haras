package com.haras;

import com.haras.view.BaseView;
import com.haras.controller.NavigationController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Configurar look and feel do sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                
                // Criar e mostrar a view principal
                BaseView baseView = new BaseView();
                
                // Inicializar o NavigationController
                NavigationController nav = NavigationController.getInstance();
                nav.setCurrentView(baseView);
                
                // Exibir a janela
                baseView.setVisible(true);
                
                // Mostrar a tela inicial de cavalos
                nav.showMeusCavalos();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}