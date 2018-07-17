package sokoban.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sokoban.domain.Point;


/**
 * Entity that represents a Tile.
 * @author Ketom
 */
public class Tile extends Entity {
    /**
     * Constructs Tile at given position and with given grid size
     * @param position The Point on which constructed Entity is.
     * @param gridSize Grid size in pixels of board constructed Entity is on.
     */
    public Tile(Point position, int gridSize) {
        super(position, gridSize);
        double margin = 0;

        Rectangle rectangle = new Rectangle(margin, margin, gridSize - 2 * margin, gridSize - 2 * margin);
        rectangle.setFill(Color.DARKGREY);

        margin = 1;

        Rectangle rectangle2 = new Rectangle(margin, margin, gridSize - 2 * margin, gridSize - 2 * margin);
        rectangle2.setFill(Color.LIGHTGREY);

        getChildren().addAll(rectangle, rectangle2);
    }
}
