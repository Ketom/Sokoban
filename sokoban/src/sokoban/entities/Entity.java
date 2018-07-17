package sokoban.entities;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sokoban.domain.Direction;
import sokoban.domain.Point;

import static sokoban.domain.Direction.UP;

/**
 * A entity with position and graphical representation. It extends Pane, so
 * it can be added to JavaFX Parent as child.
 * @author Ketom
 */
public abstract class Entity extends Pane {
    /**
     * The position on which this Entity is.
     */
    private Point position;

    /**
     * Direction in which this Entity is turned.
     */
    private Direction direction;

    /**
     * Grid size in pixels of board this Entity is on.
     */
    private int gridSize;

    /**
     * Constructs Entity at given position and with given grid size
     * @param position The Point on which constructed Entity is.
     * @param gridSize Grid size in pixels of board constructed Entity is on.
     */
    public Entity(Point position, int gridSize) {
        this.position = new Point(position);
        this.gridSize = gridSize;
        direction = UP;

        // without this dummy node, rotation does not work properly
        getChildren().add(new Rectangle(gridSize, gridSize, Color.TRANSPARENT));

        update();
    }

    /**
     * Move this Entity in given direction.
     * @param direction The direction in which Entity will be moved.
     */
    public void move(Direction direction) {
        position.translate(direction);
        update();
    }

    /**
     * Sets position of this Entity to specific point.
     * @param position Point specifying position of this Entity.
     */
    public void setPosition(Point position) {
        this.position = new Point(position);
        update();
    }

    /**
     * Returns the position this Entity is on.
     * @return Returns Point specifying position of this Entity.
     */
    public Point getPosition() {
        return new Point(position);
    }

    /**
     * Sets direction in which this Entity is turned.
     * @param direction The direction in which Entity will be turned.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
        update();
    }

    /**
     * Changes graphics related properties to reflect changes to Entity state.
     */
    protected void update() {
        setWidth(gridSize);
        setHeight(gridSize);

        switch(direction) {
            case UP:
                setRotate(0.0);
                break;
            case RIGHT:
                setRotate(90.0);
                break;
            case DOWN:
                setRotate(180.0);
                break;
            case LEFT:
                setRotate(270.0);
                break;
        }

        setLayoutX(position.getX() * gridSize);
        setLayoutY(position.getY() * gridSize);
    }
}
