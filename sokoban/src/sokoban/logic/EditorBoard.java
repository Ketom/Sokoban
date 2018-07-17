package sokoban.logic;

import sokoban.domain.Direction;
import sokoban.domain.Level;
import sokoban.domain.Point;
import sokoban.entities.*;

/**
 * A game board suited for editing levels. Contains Cursor which can be used
 * to specify Tile, then various entities can be added at specified Point.
 * @author Ketom
 */
public class EditorBoard extends Board {
    /**
     * Cursor representing which tile is being modified.
     */
    private Cursor cursor;

    /**
     * Constructs EditorBoard based on given Level.
     * @param level Level from which board will be created.
     */
    public EditorBoard(Level level) {
        super(level);
        cursor = new Cursor(new Point((width - 1) / 2, (height - 1) / 2), gridSize);
        getChildren().add(cursor);
    }

    /**
     * Creates Player on Point indicated by current position of Cursor.
     */
    public void placePlayer() {
        playerLayer.clearPlayer();
        playerLayer.put(new Player(cursor.getPosition(), gridSize));
    }

    /**
     * Creates Wall on Point indicated by current position of Cursor.
     */
    public void placeWall() {
        spotsLayer.clear(cursor.getPosition());
        playerLayer.put(new Wall(cursor.getPosition(), gridSize));
    }

    /**
     * Creates Box on Point indicated by current position of Cursor.
     */
    public void placeBox() {
        playerLayer.put(new Box(cursor.getPosition(), gridSize));
    }

    /**
     * Creates Spot on Point indicated by current position of Cursor.
     */
    public void placeSpot() {
        playerLayer.clear(cursor.getPosition());
        spotsLayer.put(new Spot(cursor.getPosition(), gridSize));
    }

    /**
     * Removes entities from Point indicated by current position of Cursor.
     */
    public void placeEmpty() {
        playerLayer.clear(cursor.getPosition());
        spotsLayer.clear(cursor.getPosition());
    }

    /**
     * Moves Cursor in given direction.
     * @param direction The direction in which Cursor will be moved.
     */
    public void moveCursor(Direction direction) {
        Point currentPosition = cursor.getPosition();
        Point newPosition = new Point(currentPosition);
        newPosition.translate(direction);
        if(!isPointValid(newPosition)) {
            return;
        }
        cursor.move(direction);
    }
}
