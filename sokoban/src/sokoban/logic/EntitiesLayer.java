package sokoban.logic;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sokoban.domain.Point;
import sokoban.entities.Entity;
import sokoban.entities.Player;

/**
 * EntitiesLayer representing grid with given width and height which may
 * contain an entity on each tile. On one tile can be at most one entity.
 * Player entity is treated in special way, and can be only one on the whole
 * grid.
 *
 * WARNING: EntitiesLayer DOES NOT track entities it contains, and DOES NOT
 * update it's inner lookup table when entity position changes. It's user
 * responsibility to remove entity and add it again when its position changes.
 * @author Ketom
 */
public class EntitiesLayer extends Pane {
    /**
     * The width on this EntitiesLayer
     */
    private int width;

    /**
     * The height on this EntitiesLayer
     */
    private int height;

    /**
     * Lookup table containing references to entities with rows being their X
     * coordinates, and columns being Y coordinates.
     */
    private Entity[][] entitiesLut;

    /**
     * Reference to Player entity present on the Layer. Can be null if there
     * is not Player.
     */
    private Player player;

    /**
     * Constructs new EntitiesLayer with given size
     * @param width width of new EntitiesLayer
     * @param height height of new EntitiesLayer
     */
    public EntitiesLayer(int width, int height){
        this.width = width;
        this.height = height;

        entitiesLut = new Entity[width][height];

        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Returns Layer width
     * @return Layer width.
     */
    public int getLayerWidth() {
        return width;
    }

    /**
     * Returns Layer height
     * @return Layer height.
     */
    public int getLayerHeight() {
        return height;
    }


    /**
     * Removes entity present at given Point. If there is no entity, does
     * nothing.
     * @param point Point from which entity should be removed.
     */
    public void clear(Point point) {
        Entity entity = get(point);
        if(entity == null) {
            return;
        }
        if(entity instanceof Player) {
            player = null;
        }
        entitiesLut[point.getX()][point.getY()] = null;
        getChildren().remove(entity);
    }

    /**
     * Put entity into Layer using its position. If a other entity is already
     * present at that position, it will be replaced with the new one.
     * @param entity Entity to be placed into layer
     */
    public void put(Entity entity) {
        Point point = entity.getPosition();
        if(entity instanceof Player) {
            if(player != null) {
                throw new IllegalArgumentException("Can't add Player on " + point + " because Player already exists on " + player.getPosition());
            }
            player = (Player) entity;
        }
        clear(point);
        entitiesLut[point.getX()][point.getY()] = entity;
        getChildren().add(entity);
    }

    /**
     * Returns entity present at given position
     * @param point Point representing position from which Entity should be fetched
     * @return entity present at given position
     */
    public Entity get(Point point) {
        return entitiesLut[point.getX()][point.getY()];
    }

    /**
     * Returns reference to Player present at layer. Can be null.
     * @return reference to Player if there is Player on Layer; null otherwise
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Removes Player present on Layer. If there is no Player, does nothing.
     */
    public void clearPlayer() {
        Player player = getPlayer();
        if (player != null) {
            clear(player.getPosition());
        }
    }
}
