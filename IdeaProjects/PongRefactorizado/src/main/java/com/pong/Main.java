package com.pong;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pong Refactorizado");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 630);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            // Se conectan las tres partes
            GameModel model = new GameModel();
            GameView view = new GameView();
            GameController controller = new GameController(model, view);

            frame.add(view);
            frame.setVisible(true);

            // Inicia la acción
            controller.start();
        });
    }
}