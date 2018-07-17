package sokoban.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import sokoban.domain.Point;


/**
 * Entity that represents a Player.
 * @author Ketom
 */
public class Player extends Entity {
    /**
     * Constructs Player at given position and with given grid size
     * @param position The Point on which constructed Entity is.
     * @param gridSize Grid size in pixels of board constructed Entity is on.
     */
    public Player(Point position, int gridSize) {
        super(position, gridSize);
        double margin = 5;

        Arc arc = new Arc(gridSize / 2, gridSize / 2, gridSize / 2 - margin, gridSize / 2 - margin, 0.0, 180.0);
        arc.setFill(Color.RED);

        Rectangle rectangle = new Rectangle(margin, gridSize / 2, gridSize - 2 * margin, gridSize / 2 - margin);
        rectangle.setFill(Color.RED);

        getChildren().addAll(rectangle, arc);
    }
}
