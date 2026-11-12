package com.pong;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameView extends JPanel {
    private GameModel model;

    public GameView() {
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    public void setModel(GameModel model) {
        this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (model == null) return;

        // Dibujar pelota
        g.setColor(Color.WHITE);
        g.fillOval(model.getBallX(), model.getBallY(), model.getBallSize(), model.getBallSize());

        // Dibujar raqueta
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(model.getPaddleX(), model.getPaddleY(), model.getPaddleWidth(), model.getPaddleHeight());

        // Textos en pantalla
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        if (model.getState() == GameModel.GameState.PAUSED) {
            g.drawString("PAUSADO", 340, 300);
        } else if (model.getState() == GameModel.GameState.STOPPED && model.getBallY() > 500) {
            g.drawString("FIN DEL JUEGO", 320, 300);
        }
    }
}
