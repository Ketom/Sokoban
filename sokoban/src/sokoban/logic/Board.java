package sokoban.logic;

import javafx.scene.layout.Pane;
import sokoban.domain.Level;
import sokoban.domain.Point;
import sokoban.entities.*;


/**
 * A game board looking like grid with entities. It extends Pane, so it can
 * be added to JavaFX Parent as child.
 * @author Ketom
 */
public abstract class Board extends Pane {
    protected int width;
    protected int height;
    protected int gridSize;
    protected EntitiesLayer playerLayer;
    protected EntitiesLayer spotsLayer;
    protected EntitiesLayer tilesLayer;

    /**
     * Constructs Board based on given Level.
     * @param level Level from which board will be created.
     */
    public Board(Level level) {
        this.width = level.getWidth();
        this.height = level.getHeight();
        gridSize = 40;

        playerLayer = new EntitiesLayer(width, height);
        spotsLayer = new EntitiesLayer(width, height);
        tilesLayer = new EntitiesLayer(width, height);

        setSize(width * gridSize, height * gridSize);

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                switch (level.get(x, y)) {
                    case Level.WALL:
                        playerLayer.put(new Wall(new Point(x, y), gridSize));
                        break;
                    case Level.PLAYER:
                        playerLayer.put(new Player(new Point(x, y), gridSize));
                        break;
                    case Level.PLAYER_ON_SPOT:
                        playerLayer.put(new Player(new Point(x, y), gridSize));
                        spotsLayer.put(new Spot(new Point(x, y), gridSize));
                        break;
                    case Level.BOX:
                        playerLayer.put(new Box(new Point(x, y), gridSize));
                        break;
                    case Level.BOX_ON_SPOT:
                        Box box = new Box(new Point(x, y), gridSize);
                        Spot spot = new Spot(new Point(x, y), gridSize);
                        box.setCorrect(true);
                        spot.setCorrect(true);
                        playerLayer.put(box);
                        spotsLayer.put(spot);
                        break;
                    case Level.SPOT:
                        spotsLayer.put(new Spot(new Point(x, y), gridSize));
                        break;
                }
            }
        }

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                tilesLayer.put(new Tile(new Point(x, y), gridSize));
            }
        }

        getChildren().add(tilesLayer);
        getChildren().add(spotsLayer);
        getChildren().add(playerLayer);
    }

    /**
     * Sets pane size to be exactly given width and height in pixels.
     * @param width
     * @param height
     */
    private void setSize(int width, int height) {
        // unfortunately it seems that we need to set all of this to make sure pane is indeed correct size...
        setMinWidth(width);
        setMinHeight(height);
        setPrefWidth(width);
        setPrefHeight(height);
        setMaxWidth(width);
        setMaxHeight(height);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Checks if given Point is within a bounds of this Board.
     * @param point Point which will be cheked
     * @return true if given Point is within a bounds of this Board; false otherwise.
     */
    protected boolean isPointValid(Point point) {
        return ((point.getX() >= 0) && (point.getX() < width) && (point.getY() >= 0) && (point.getY() < height));
    }

    /**
     * Returns Board width
     * @return Board width.
     */
    public int getBoardWidth() {
        return width;
    }

    /**
     * Returns Board height
     * @return Board height.
     */
    public int getBoardHeight() {
        return height;
    }

    /**
     * Constructs Level based on current state of Board
     * @return Level representing current state of Board.
     */
    public Level toLevel() {
        byte[][] bytes = new byte[width][height];

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Entity playerLayerEntity = playerLayer.get(new Point(x, y));
                Entity spotsLayerEntity = spotsLayer.get(new Point(x, y));
                byte b = Level.TILE;

                if(spotsLayerEntity != null) {
                    if(playerLayerEntity != null) {
                        if(playerLayerEntity instanceof Box) {
                            b = Level.BOX_ON_SPOT;
                        } else if(playerLayerEntity instanceof Player) {
                            b = Level.PLAYER_ON_SPOT;
                        }
                    } else {
                        b = Level.SPOT;
                    }
                } else {
                    if(playerLayerEntity != null) {
                        if(playerLayerEntity instanceof Player) {
                            b = Level.PLAYER;
                        } else if(playerLayerEntity instanceof Wall) {
                            b = Level.WALL;
                        } else if(playerLayerEntity instanceof Box) {
                            b = Level.BOX;
                        }
                    }
                }
                bytes[x][y] = b;
            }
        }
        return new Level(width, height, bytes);
    }
}
