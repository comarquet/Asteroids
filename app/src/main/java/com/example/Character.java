package com.example;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public abstract class Character {

    private Polygon character;
    protected Vector2D movement;


    public Character(Polygon polygon, int x, int y) {
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        this.movement = new Vector2D(0, 0);
    }

    //rotates object to the left by 5 degrees
    public void turnLeft() {
        this.character.setRotate(this.character.getRotate() - 1);
    }

    //rotates object to the right by 5 degrees
    public void turnRight() {
        this.character.setRotate(this.character.getRotate() + 1);
    }

    //returns the figure representing ship to move it on a scene
    public Polygon returnCharacter() {
        return this.character;
    }

    //function describes the direction, acceleration and the movement. Movement variable is used to define direction.
    public void accelerate() {

        // Create a vector in the direction of the character's rotation
        double angleInRadians = Math.toRadians(this.character.getRotate());
        Vector2D direction = new Vector2D(Math.cos(angleInRadians), Math.sin(angleInRadians)).scalarMultiply(0.005);
        this.movement = this.movement.add(direction);
    }

    //function moves the ship according to its direction
    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());

        //in case the object moves out of the window it's moved on the opposite side
        if (this.character.getTranslateX() < 0 ) {
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


    //returns Polygon of the object.
    public Polygon getCharacter() {
        return this.character;
    }

    //function checks the colision of two Character objects
    public boolean collide(Character object) {
        Shape collisionArea = Shape.intersect(this.character, object.character);
        return !(collisionArea.getBoundsInLocal().isEmpty());
    }

}
