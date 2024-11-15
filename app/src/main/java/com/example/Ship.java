package com.example;

import javafx.scene.shape.Polygon;

public class Ship extends Character {

    public Ship(int x, int y) {
        super(new Polygon(-5, -5, -5, 5, 10, 0), x, y);
    }

    //the speed of ship must be slowly reduced when acceleration button is not pushed
    public void reduceSpeed() {

        double reductionX = this.movement.getX() * - 0.02;
        double reductionY = this.movement.getY() * - 0.02;

        this.movement = this.movement.add(reductionX, reductionY);

    }
}
