package sokoban.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sokoban.domain.Point;


/**
 * Entity that represents a Wall.
 * @author Ketom
 */
public class Wall extends Entity {
    /**
     * Constructs Wall at given position and with given grid size
     * @param position The Point on which constructed Entity is.
     * @param gridSize Grid size in pixels of board constructed Entity is on.
     */
    public Wall(Point position, int gridSize) {
        super(position, gridSize);
        double margin = 1;

        Rectangle rectangle = new Rectangle(margin, margin, gridSize - 2 * margin, gridSize - 2 * margin);
        rectangle.setFill(Color.DARKGREEN);

        getChildren().addAll(rectangle);
    }
}
