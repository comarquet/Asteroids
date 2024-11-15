package com.example;

import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship extends Character {

    public Ship(int x, int y) {
        super(new Polygon(-5, -5, -5, 5, 10, 0), x, y);
    }

    //the speed of ship must be slowly reduced when acceleration button is not pushed
    public void reduceSpeed() {
        this.movement = this.movement.scalarMultiply(0.98); // Reduces speed by 2%
    }
}
