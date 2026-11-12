package com.pong;

import java.awt.Rectangle;

public class GameModel {
    public enum GameState { RUNNING, PAUSED, STOPPED }

    private GameState state = GameState.STOPPED;
    private int ballX = 400, ballY = 300;
    private int ballVelocityX = 5, ballVelocityY = 5;
    private final int BALL_SIZE = 20;

    private int paddleX = 350;
    private final int PADDLE_Y = 550, PADDLE_WIDTH = 100, PADDLE_HEIGHT = 15;
    private final int BOARD_WIDTH = 800, BOARD_HEIGHT = 600;

    public void update() {
        if (state != GameState.RUNNING) return;

        ballX += ballVelocityX;
        ballY += ballVelocityY;

        // Rebotes en las paredes (izquierda y derecha)
        if (ballX <= 0 || ballX >= BOARD_WIDTH - BALL_SIZE) {
            ballVelocityX = -ballVelocityX;
        }

        // Rebote en el techo
        if (ballY <= 0) {
            ballVelocityY = -ballVelocityY;
        }

        // Rebote con la raqueta usando dimensiones reales para evitar que la atraviese
        Rectangle ballRect = new Rectangle(ballX, ballY, BALL_SIZE, BALL_SIZE);
        Rectangle paddleRect = new Rectangle(paddleX, PADDLE_Y, PADDLE_WIDTH, PADDLE_HEIGHT);

        if (ballRect.intersects(paddleRect)) {
            ballVelocityY = -Math.abs(ballVelocityY);
            ballY = PADDLE_Y - BALL_SIZE; // Evita rebotes múltiples
        }

        // Si cae al fondo, se detiene el juego
        if (ballY > BOARD_HEIGHT) {
            state = GameState.STOPPED;
        }
    }

    public void movePaddle(int dx) {
        if (state != GameState.RUNNING) return;
        paddleX += dx;
        // Evita que la raqueta se salga de la pantalla
        if (paddleX < 0) paddleX = 0;
        if (paddleX > BOARD_WIDTH - PADDLE_WIDTH) paddleX = BOARD_WIDTH - PADDLE_WIDTH;
    }

    // Métodos para controlar el estado
    public void start() { state = GameState.RUNNING; }
    public void togglePause() {
        if (state == GameState.RUNNING) {
            state = GameState.PAUSED;
        } else if (state == GameState.PAUSED) state = GameState.RUNNING;
    }

    // Getters para que la Vista pueda dibujar
    public GameState getState() { return state; }
    public int getBallX() { return ballX; }
    public int getBallY() { return ballY; }
    public int getBallSize() { return BALL_SIZE; }
    public int getPaddleX() { return paddleX; }
    public int getPaddleY() { return PADDLE_Y; }
    public int getPaddleWidth() { return PADDLE_WIDTH; }
    public int getPaddleHeight() { return PADDLE_HEIGHT; }
}