package com.project;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Abstract class representing a character in the game.
 * <p>
 * Defines common properties and methods for all game objects, such as movement,
 * rotation, and collision detection.
 * </p>
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class Character {
    
    /**
     * The graphical representation of the character.
     */
    private Polygon character;
    
    /**
     * The movement vector of the character.
     */
    protected Vector2D movement;
    
    /**
     * Creates a character with a specific shape and position.
     *
     * @param polygon The shape of the character.
     * @param x       Initial X-coordinate.
     * @param y       Initial Y-coordinate.
     */
    public Character(Polygon polygon, int x, int y) {
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.movement = new Vector2D(0, 0);
    }
    
    /**
     * Rotates the character to the left by 1 degree.
     */
    public void turnLeft() {
        this.character.setRotate(this.character.getRotate() - 1);
    }
    
    /**
     * Rotates the character to the right by 1 degree.
     */
    public void turnRight() {
        this.character.setRotate(this.character.getRotate() + 1);
    }
    
    /**
     * Accelerates the character in the direction it is currently facing.
     * <p>
     * The acceleration modifies the character's movement vector based on its
     * current rotation.
     * </p>
     */
    public void accelerate() {
        // Create a vector in the direction of the character's rotation
        double angleInRadians = Math.toRadians(this.character.getRotate());
        Vector2D direction = new Vector2D(Math.cos(angleInRadians), Math.sin(angleInRadians)).scalarMultiply(0.005);
        this.movement = this.movement.add(direction);
    }
    
    /**
     * Moves the character according to its current direction and speed.
     * <p>
     * If the character moves out of the screen bounds, it reappears on the opposite side.
     * </p>
     */
    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
        
        // Wrap around if the object moves out of bounds
        if (this.character.getTranslateX() < 0) {
            this.character.setTranslateX(this.character.getTranslateX() + AsteroidsApplication.WIDTH);
        }
        if (this.character.getTranslateX() > AsteroidsApplication.WIDTH) {
            this.character.setTranslateX(this.character.getTranslateX() - AsteroidsApplication.WIDTH);
        }
        if (this.character.getTranslateY() < 0) {
            this.character.setTranslateY(this.character.getTranslateY() + AsteroidsApplication.HEIGHT);
        }
        if (this.character.getTranslateY() > AsteroidsApplication.HEIGHT) {
            this.character.setTranslateY(this.character.getTranslateY() - AsteroidsApplication.HEIGHT);
        }
    }
    
    /**
     * Checks if this character collides with another character.
     *
     * @param object The other character to check for a collision.
     * @return {@code true} if the characters collide; {@code false} otherwise.
     */
    public boolean collide(Character object) {
        Shape collisionArea = Shape.intersect(this.character, object.character);
        return !(collisionArea.getBoundsInLocal().isEmpty());
    }
}
