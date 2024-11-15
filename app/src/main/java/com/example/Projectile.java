package com.example;

import javafx.scene.shape.Polygon;

public class Projectile extends Character {

    private int lifeSpan;

    //projectile is created in the location of the ship
    public Projectile(Ship ship) {
        super(new Polygon(-2, -2, -2, 2, 2, 2, 2, -2), (int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());

        //rotate the ship according to a ships direction
        this.getCharacter().setRotate(ship.getCharacter().getRotate());

        //giving projectile a speed and direction
        for (int i = 0; i < 100; i++) {
            this.accelerate();
        }

        //LifeSpan at birth equals 0;
        this.lifeSpan = 0;
    }

    public int getLifeSpan() {
        return this.lifeSpan;
    }

    public void addToLifeSpan() {
        this.lifeSpan = this.lifeSpan + 1;
    }

}
