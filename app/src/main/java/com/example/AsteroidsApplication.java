package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AsteroidsApplication extends Application {

    private static final Logger logger = LogManager.getLogger(AsteroidsApplication.class);

    public static int WIDTH = 600;
    public static int HEIGHT = 400;

    @Override
    public void start(Stage window) {

        logger.info("Application started");

        // Creating a new layout for the game
        Pane layout = new Pane();
        layout.setPrefSize(WIDTH, HEIGHT);

        // Creating AtomicInteger object to count points
        AtomicInteger points = new AtomicInteger();

        // Text object to depict points
        Text score = new Text(10, 20, "Points: 0");
        layout.getChildren().add(score);

        // Creating the object that gives random values
        RandomDataGenerator randomer = new RandomDataGenerator();

        // Creating ship
        Ship ship = new Ship((WIDTH / 2), (HEIGHT / 2));
        layout.getChildren().add(ship.getCharacter());

        logger.debug("Ship created at position: ({}, {})", WIDTH / 2, HEIGHT / 2);

        // Creating list and adding new asteroids to it
        List<Asteroid> asteroids = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int x = randomer.nextInt(0, WIDTH - 1);
            int y = randomer.nextInt(0, HEIGHT - 1);
            Asteroid newAsteroid = new Asteroid(x, y);
            if (newAsteroid.collide(ship)) {
                i = i - 1;
                logger.warn("Collision detected between new asteroid and ship at initialization. Retrying asteroid placement.");
            } else {
                asteroids.add(newAsteroid);
                logger.debug("Asteroid created at position: ({}, {})", x, y);
            }
        }

        // Adding each asteroid to a layout
        asteroids.forEach((asteroid) -> layout.getChildren().add(asteroid.getCharacter()));

        // Creating list for projectiles
        List<Projectile> projectiles = new ArrayList<>();

        // Creating new scene
        Scene scene = new Scene(layout);

        // Creating a HashMap that contains info about the state of the buttons
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

        // Changing the status of the button with press and release actions
        scene.setOnKeyPressed((event) -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
            if (!pressedKeys.containsKey(event.getCode())) {
                logger.warn("Unhandled key pressed: " + event.getCode());
            }
        });
        scene.setOnKeyReleased((event) -> pressedKeys.put(event.getCode(), Boolean.FALSE));

        // New Animation functionality to move objects
        new AnimationTimer() {

            int bulletTimer = 30;
            int speedEnhancer = 1;

            @Override
            public void handle(long now) {
                bulletTimer++;

                // Animating ship's rotation according to button state
                try {
                    if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                        ship.turnLeft();
                    }
                    if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                        ship.turnRight();
                    }
                    if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
                        ship.accelerate();
                    }
                } catch (Exception e) {
                    logger.error("Error during ship movement: ", e);
                }
                if (pressedKeys.getOrDefault(KeyCode.SPACE, false)) {
                    if (bulletTimer > 30) {
                        Projectile bullet = new Projectile(ship);
                        bulletTimer = 0;
                        bullet.getCharacter().setRotate(ship.getCharacter().getRotate());
                        projectiles.add(bullet);
                        layout.getChildren().add(bullet.getCharacter());
                        logger.info("Projectile fired");
                    }
                }

                // Movement and collision detection
                ship.reduceSpeed();
                ship.move();

                asteroids.forEach(asteroid -> {
                    asteroid.move();
                    if (ship.collide(asteroid)) {
                        stop();
                    }
                });

                projectiles.forEach(projectile -> {
                    projectile.move();
                    asteroids.forEach(asteroid -> {
                        if (asteroid.collide(projectile)) {
                            layout.getChildren().remove(asteroid.getCharacter());
                            asteroids.remove(asteroid);
                            layout.getChildren().remove(projectile.getCharacter());
                            projectiles.remove(projectile);
                            score.setText("Points: " + points.addAndGet(1000));
                            logger.info("Asteroid destroyed. Points awarded: 1000");
                        }
                    });
                });

                // Adding new random asteroid occasionally
                if (randomer.nextUniform(0, 1) < 0.005) {
                    int locationX = randomer.nextInt(0, 1) * WIDTH;
                    int locationY = randomer.nextInt(0, 1) * HEIGHT;
                    Asteroid asteroid = new Asteroid(locationX, locationY);
                    for (int i = 0; i < speedEnhancer; i++) {
                        asteroid.accelerate();
                    }
                    asteroids.add(asteroid);
                    layout.getChildren().add(asteroid.getCharacter());
                    speedEnhancer++;
                    logger.debug("New asteroid added at ({}, {}) with enhanced speed", locationX, locationY);
                }
            }
        }.start();

        // Adding scene to window
        window.setTitle("Asteroids!");
        window.setScene(scene);
        window.show();

        logger.info("Game window displayed");
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            logger.fatal("Fatal error occurred during application launch: ", e);
            throw e;
        }
    }
}
