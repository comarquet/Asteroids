package com.project;

import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a projectile fired by the ship.
 * <p>
 * Projectiles move in the direction the ship was facing when fired.
 * They have a limited lifespan and are removed from the game after a certain time.
 * </p>
 */
@Getter
@Setter
public class Projectile extends Character {
    
    /**
     * Lifespan of the projectile.
     * <p>
     * Tracks the duration the projectile has been active. Once the lifespan
     * exceeds a predefined threshold, the projectile is removed from the game.
     * </p>
     */
    private int lifeSpan;
    
    /**
     * Creates a projectile based on the ship's current position and rotation.
     * <p>
     * The projectile inherits the ship's rotation and is initialized with acceleration
     * to simulate firing speed. Its lifespan starts at 0 and increases over time.
     * </p>
     *
     * @param ship The ship that fired the projectile.
     */
    public Projectile(Ship ship) {
        super(new Polygon(-2, -2, -2, 2, 2, 2, 2, -2),
          (int) ship.getCharacter().getTranslateX(),
          (int) ship.getCharacter().getTranslateY());
        this.getCharacter().setRotate(ship.getCharacter().getRotate());
        
        // Simulate initial acceleration
        for (int i = 0; i < 100; i++) {
            this.accelerate();
        }
        
        this.lifeSpan = 0;
    }
    
    /**
     * Increases the lifespan of the projectile.
     * <p>
     * This method should be called at regular intervals to track how long
     * the projectile has been active. When the lifespan exceeds a certain threshold,
     * the projectile is removed from the game.
     * </p>
     */
    public void addToLifeSpan() {
        this.lifeSpan++;
    }
}
