package com.haras;

import javax.swing.SwingUtilities;
import com.haras.view.BaseView;

public class Main {
    public static void main(String[] args) {
        // Executar na thread da interface gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BaseView baseView = new BaseView();
                baseView.setVisible(true);
            }
        });
    }
}