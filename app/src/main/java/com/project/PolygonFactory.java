package com.project;

import org.apache.commons.math3.random.RandomDataGenerator;
import javafx.scene.shape.Polygon;

/**
 * Factory class for generating polygons with random shapes.
 * <p>
 * This class is used to create polygons that simulate the appearance of asteroids
 * by generating random angles and distances for the vertices. The resulting shapes
 * are used in the game as visual representations of asteroids.
 * </p>
 */
public class PolygonFactory {
    
    /**
     * Creates a random polygon resembling an asteroid.
     * <p>
     * This method generates a polygon with five vertices. The vertices are defined
     * by random angles and distances from the origin, ensuring variability in the
     * asteroid's shape. A random scaling factor is applied to adjust the size of the polygon.
     * </p>
     *
     * @return A new {@link Polygon} object representing the randomly generated shape.
     */
    public Polygon createPolygon() {
        // Create a RandomDataGenerator object
        RandomDataGenerator generator = new RandomDataGenerator();
        
        // Generate five random angles to define the vertices of the polygon
        int angle1 = generator.nextInt(21, 50);
        int angle2 = generator.nextInt(92, 121);
        int angle3 = generator.nextInt(165, 194);
        int angle4 = generator.nextInt(237, 266);
        int angle5 = generator.nextInt(309, 338);
        
        // Generate a random size adjustment factor for scaling the polygon
        int size = generator.nextInt(0, 14);
        
        // Generate random distances for each vertex from the origin
        int distance1 = generator.nextInt(15, 19) + size;
        int distance2 = generator.nextInt(15, 19) + size;
        int distance3 = generator.nextInt(15, 19) + size;
        int distance4 = generator.nextInt(15, 19) + size;
        int distance5 = generator.nextInt(15, 19) + size;
        
        // Calculate X and Y coordinates for each vertex using trigonometric functions
        double x1 = Math.cos(Math.toRadians(angle1)) * distance1;
        double y1 = Math.sin(Math.toRadians(angle1)) * distance1;
        
        double x2 = Math.cos(Math.toRadians(angle2)) * distance2;
        double y2 = Math.sin(Math.toRadians(angle2)) * distance2;
        
        double x3 = Math.cos(Math.toRadians(angle3)) * distance3;
        double y3 = Math.sin(Math.toRadians(angle3)) * distance3;
        
        double x4 = Math.cos(Math.toRadians(angle4)) * distance4;
        double y4 = Math.sin(Math.toRadians(angle4)) * distance4;
        
        double x5 = Math.cos(Math.toRadians(angle5)) * distance5;
        double y5 = Math.sin(Math.toRadians(angle5)) * distance5;
        
        // Return the generated polygon
        return new Polygon(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5);
    }
}
