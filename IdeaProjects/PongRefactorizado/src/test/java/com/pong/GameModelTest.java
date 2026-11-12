package com.pong;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
    private GameModel model;

    @BeforeEach
    void setUp() {
        model = new GameModel();
    }

    // CP-01: Iniciar el juego
    @Test
    void testStartChangesStateToRunning() {
        model.start();
        assertEquals(GameModel.GameState.RUNNING, model.getState(), "El estado debería ser RUNNING al iniciar.");
    }

    // CP-02 y CP-11: Pausar el juego detiene el movimiento
    @Test
    void testPauseStopsBallMovement() {
        model.start();
        model.togglePause();

        int initialX = model.getBallX();
        int initialY = model.getBallY();

        model.update(); // Simulamos un "tick" del reloj

        assertEquals(initialX, model.getBallX(), "La pelota no debe moverse en pausa.");
        assertEquals(initialY, model.getBallY(), "La pelota no debe moverse en pausa.");
    }

    // CP-05: Colisión con la pared izquierda
    @Test
    void testWallCollisionInvertsVelocity() {
        model.start();
        model.setBallX(0); // Forzamos la posición contra la pared izquierda
        model.setBallVelocityX(-5); // Moviéndose hacia la izquierda

        model.update(); // Este tick causa el rebote

        assertTrue(model.getBallVelocityX() > 0, "La velocidad horizontal debe invertirse a positiva.");
    }

    // CP-09: La raqueta no sale del tablero
    @Test
    void testPaddleStaysWithinBounds() {
        model.start();

        model.movePaddle(-5000); // Intento exagerado hacia la izquierda
        assertEquals(0, model.getPaddleX(), "La raqueta no debe tener posición negativa.");

        model.movePaddle(5000); // Intento exagerado hacia la derecha
        assertEquals(800 - model.getPaddleWidth(), model.getPaddleX(), "La raqueta no debe superar el límite derecho.");
    }
}