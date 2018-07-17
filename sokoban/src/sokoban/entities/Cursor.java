package sokoban.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sokoban.domain.Point;

/**
 * Entity that represents a Cursor.
 * @author Ketom
 */
public class Cursor extends Entity {
    /**
     * Constructs Cursor at given position and with given grid size
     * @param position The Point on which constructed Entity is.
     * @param gridSize Grid size in pixels of board constructed Entity is on.
     */
    public Cursor(Point position, int gridSize) {
        super(position, gridSize);
        double margin = 2;

        Rectangle rectangle = new Rectangle(margin, margin, gridSize - 2 * margin, gridSize - 2 * margin);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeWidth(4);
        rectangle.setStroke(Color.RED);

        getChildren().addAll(rectangle);
    }
}
