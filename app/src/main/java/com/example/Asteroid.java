package com.example;

import org.apache.commons.math3.random.RandomDataGenerator;

public class Asteroid extends Character {

    private double rotation;

    //randomly shaped Asteroid is created using PolygonFactory class
    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);

        // Initialize RandomDataGenerator for randomness
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

        // Asteroid is turned into a random direction between 0 and 360 degrees
        this.returnCharacter().setRotate(randomDataGenerator.nextUniform(0, 360));

        // Random slight rotation to the movement of the Asteroid
        this.rotation = randomDataGenerator.nextUniform(-0.5, 0.5);

        // Random speed of each asteroid defined by acceleration steps
        int acceleration = randomDataGenerator.nextInt(1, 7);
        for (int i = 0; i < acceleration; i++) {
            this.accelerate();
        }
    }

    // Each move includes a slight rotation as well
    @Override
    public void move() {
        super.move();
        this.getCharacter().setRotate(this.getCharacter().getRotate() + this.rotation);
    }
}
