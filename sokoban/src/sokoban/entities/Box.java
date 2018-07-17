package sokoban.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sokoban.domain.Point;

/**
 * Entity that represents a Box.
 * @author Ketom
 */
public class Box extends Entity {
    /**
     * Is the Box on the Spot.
     */
    private boolean isCorrect;

    /**
     * Constructs Box at given position and with given grid size
     * @param position The Point on which constructed Entity is.
     * @param gridSize Grid size in pixels of board constructed Entity is on.
     */
    public Box(Point position, int gridSize) {
        super(position, gridSize);
        isCorrect = false;

        double margin = 5;

        Rectangle rectangle = new Rectangle(margin, margin, gridSize - 2 * margin, gridSize - 2 * margin);
        rectangle.setFill(Color.BLUE);

        getChildren().addAll(rectangle);
    }

    /**
     * Sets if the Box is on the Spot.
     * @param correct is this box on spot?
     */
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    /**
     * Returns if this Box is on a Spot.
     * @return true if this Box is on a Spot; false otherwise.
     */
    public boolean isCorrect() {
        return isCorrect;
    }

}
