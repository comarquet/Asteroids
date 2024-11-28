package com.project;

import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the player's ship.
 * <p>
 * Handles movement, acceleration, and speed reduction. The ship is controlled
 * by the player's inputs and interacts with other game objects, such as
 * asteroids and projectiles.
 * </p>
 */
@Getter
@Setter
public class Ship extends Character {
    
    /**
     * Creates a ship at a specified position.
     * <p>
     * The ship is represented by a triangular polygon, initialized at the
     * specified position.
     * </p>
     *
     * @param x X-coordinate of the ship.
     * @param y Y-coordinate of the ship.
     */
    public Ship(int x, int y) {
        super(new Polygon(-5, -5, -5, 5, 10, 0), x, y);
    }
    
    /**
     * Gradually reduces the ship's speed when acceleration is not applied.
     * <p>
     * This method simulates friction by reducing the ship's movement speed
     * by 2% each frame. It is typically called during game updates when
     * the player is not actively accelerating.
     * </p>
     */
    public void reduceSpeed() {
        this.movement = this.movement.scalarMultiply(0.98); // Reduces speed by 2%
    }
}
