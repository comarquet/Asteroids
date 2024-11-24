package com.project;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.math3.random.RandomDataGenerator;

/**
 * Getter and Setter come from the lombok library to automatically generate getters and setters for the class.
 */
@Getter
@Setter
public class Asteroid extends Character {

  private double rotation;

  public Asteroid(int x, int y) {
    super(new PolygonFactory().createPolygon(), x, y);
    RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

    // Set random rotation
    this.getCharacter().setRotate(randomDataGenerator.nextUniform(0, 360));
    this.rotation = randomDataGenerator.nextUniform(-0.5, 0.5);

    // Set random speed
    int acceleration = randomDataGenerator.nextInt(1, 7);
    for (int i = 0; i < acceleration; i++) {
      this.accelerate();
    }
  }

  @Override
  public void move() {
    super.move();
    this.getCharacter().setRotate(this.getCharacter().getRotate() + this.rotation);
  }
}

