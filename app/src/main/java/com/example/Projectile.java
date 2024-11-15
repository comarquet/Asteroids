package com.example;

import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Projectile extends Character {

    private int lifeSpan;

    public Projectile(Ship ship) {
        super(new Polygon(-2, -2, -2, 2, 2, 2, 2, -2), (int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());
        this.getCharacter().setRotate(ship.getCharacter().getRotate());

        for (int i = 0; i < 100; i++) {
            this.accelerate();
        }

        this.lifeSpan = 0;
    }

    public void addToLifeSpan() {
        this.lifeSpan++;
    }
}