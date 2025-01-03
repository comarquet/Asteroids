package com.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.shape.Polygon;

@ExtendWith(ApplicationExtension.class)
class AsteroidsApplicationTest {

  private Pane layout;
  private Node shipNode;
  private Polygon asteroid;

  @Start
  private void start(Stage stage) {
    // Initialize the application
    AsteroidsApplication app = new AsteroidsApplication();
    app.start(stage);

    // Access the layout for further verification in tests
    layout = (Pane) stage.getScene().getRoot();

    // Locate the ship node in the layout based on its unique dimensions
    shipNode = layout.getChildren().stream()
      .filter(node -> node instanceof Polygon)
      .filter(node -> {
        Polygon polygon = (Polygon) node;
        return polygon.getPoints().equals(java.util.List.of(-5.0, -5.0, -5.0, 5.0, 10.0, 0.0));
      })
      .findFirst()
      .orElseThrow(() -> new AssertionError("Ship not found in the layout"));
  }

  @Test
  void should_turn_left(FxRobot robot) {
    // Initial rotation of the ship
    double initialRotation = shipNode.getRotate();

    // Rotate ship to the left
    robot.press(KeyCode.LEFT);
    robot.sleep(500);
    robot.release(KeyCode.LEFT);

    // Check if the ship's rotation has decreased
    double afterLeftRotation = shipNode.getRotate();
    Assertions.assertThat(afterLeftRotation).isLessThan(initialRotation);
  }

  @Test
  void should_turn_right(FxRobot robot) {
    // Initial rotation of the ship
    double initialRotation = shipNode.getRotate();

    // Rotate ship to the right
    robot.press(KeyCode.RIGHT);
    robot.sleep(500);
    robot.release(KeyCode.RIGHT);

    // Check if the ship's rotation has increased
    double afterRightRotation = shipNode.getRotate();
    Assertions.assertThat(afterRightRotation).isGreaterThan(initialRotation);
  }

  @Test
  void should_move_forward(FxRobot robot) {
    // Initial position of the ship
    double initialTranslateX = shipNode.getTranslateX();

    // Accelerate ship forward
    robot.press(KeyCode.UP);
    robot.sleep(500);  // Increase sleep to allow more movement
    robot.release(KeyCode.UP);

    // Check if the ship's position has changed beyond a small threshold
    double afterTranslateX = shipNode.getTranslateX();
    double threshold = 0.1;  // A small value to account for movement

    Assertions.assertThat(Math.abs(afterTranslateX - initialTranslateX)).isGreaterThan(threshold);
  }

  @Test
  void should_fire_projectile(FxRobot robot) {
    // Count the number of nodes before firing
    long initialProjectileCount = layout.getChildren().stream()
      .filter(node -> node instanceof Polygon && ((Polygon) node).getPoints().equals(java.util.List.of(-2.0, -2.0, -2.0, 2.0, 2.0, 2.0, 2.0, -2.0)))
      .count();

    // Fire a projectile by pressing space
    robot.press(KeyCode.SPACE);
    robot.sleep(500);
    robot.release(KeyCode.SPACE);

    // Count the number of nodes after firing
    long afterProjectileCount = layout.getChildren().stream()
      .filter(node -> node instanceof Polygon && ((Polygon) node).getPoints().equals(java.util.List.of(-2.0, -2.0, -2.0, 2.0, 2.0, 2.0, 2.0, -2.0)))
      .count();

    // Verify that a new projectile has been added
    Assertions.assertThat(afterProjectileCount).isGreaterThan(initialProjectileCount);
  }
}
