package com.pong;

import javax.swing.Timer;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private final Timer timer;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.view.setModel(model);

        // El Timer reemplaza a los hilos, ejecutándose a ~60 FPS de forma segura en Swing
        this.timer = new Timer(16, e -> {
            model.update();
            view.repaint();
        });

        setupKeyBindings();
    }

    public void start() {
        model.start();
        timer.start();
    }

    private void setupKeyBindings() {
        // Pausa con espacio
        view.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pause");
        view.getActionMap().put("pause", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { model.togglePause(); }
        });

        // Mover a la izquierda
        view.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        view.getActionMap().put("moveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { model.movePaddle(-30); }
        });

        // Mover a la derecha
        view.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        view.getActionMap().put("moveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { model.movePaddle(30); }
        });
    }
}