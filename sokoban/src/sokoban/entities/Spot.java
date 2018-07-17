package sokoban.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sokoban.domain.Point;

/**
 * Entity that represents a Spot on which Box should be placed to win game.
 * @author Ketom
 */
public class Spot extends Entity {
    /**
     * Is the Box on the Spot.
     */
    private boolean isCorrect;

    /**
     * Rectangle this Spot is displayed as.
     */
    private Rectangle rectangle;

    /**
     * Constructs Spot at given position and with given grid size
     * @param position The Point on which constructed Entity is.
     * @param gridSize Grid size in pixels of board constructed Entity is on.
     */
    public Spot(Point position, int gridSize) {
        super(position, gridSize);
        double margin = 1;

        rectangle = new Rectangle(margin, margin, gridSize - 2 * margin, gridSize - 2 * margin);
        rectangle.setFill(Color.ORANGE);

        getChildren().addAll(rectangle);
    }

    /**
     * Sets if a Box is on the Spot.
     * @param correct is a box on this spot?
     */
    public void setCorrect(boolean correct) {
        isCorrect = correct;
        update();
    }

    /**
     * Returns if a Box is on this Spot.
     * @return true if a Box is on this Spot; false otherwise.
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    /**
     * Changes graphics related properties to reflect changes to Spot state.
     */
    protected void update() {
        super.update();
        if(rectangle == null) {
            return;
        }
        if(isCorrect) {
            rectangle.setFill(Color.YELLOW);
        } else {
            rectangle.setFill(Color.ORANGE);
        }
    }
}
