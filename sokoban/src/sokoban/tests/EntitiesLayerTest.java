package sokoban.tests;

import org.junit.Test;
import sokoban.domain.Point;
import sokoban.entities.Box;
import sokoban.entities.Player;
import sokoban.entities.Wall;
import sokoban.logic.EntitiesLayer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class EntitiesLayerTest {
    @Test
    public void basicTest() {
        EntitiesLayer entitiesLayer = new EntitiesLayer(19, 35);

        assertEquals(19, entitiesLayer.getLayerWidth());
        assertEquals(35, entitiesLayer.getLayerHeight());
    }

    @Test
    public void getTest() {
        EntitiesLayer entitiesLayer = new EntitiesLayer(36, 31);

        assertEquals(null, entitiesLayer.get(new Point(3, 6)));
        entitiesLayer.put(new Wall(new Point(3, 6), 40));
        assertNotEquals(null, entitiesLayer.get(new Point(3, 6)));
        assertThat(entitiesLayer.get(new Point(3, 6)), instanceOf(Wall.class));
    }

    @Test
    public void putTest() {
        EntitiesLayer entitiesLayer = new EntitiesLayer(36, 31);

        assertEquals(null, entitiesLayer.get(new Point(3, 6)));
        entitiesLayer.put(new Wall(new Point(3, 6), 40));
        assertNotEquals(null, entitiesLayer.get(new Point(3, 6)));
        assertThat(entitiesLayer.get(new Point(3, 6)), instanceOf(Wall.class));

        // replace entity with other one
        entitiesLayer.put(new Box(new Point(3, 6), 40));
        assertNotEquals(null, entitiesLayer.get(new Point(3, 6)));
        assertThat(entitiesLayer.get(new Point(3, 6)), instanceOf(Box.class));
    }

    @Test
    public void clearTest() {
        EntitiesLayer entitiesLayer = new EntitiesLayer(36, 31);

        assertEquals(null, entitiesLayer.get(new Point(3, 6)));
        entitiesLayer.put(new Wall(new Point(3, 6), 40));
        assertNotEquals(null, entitiesLayer.get(new Point(3, 6)));
        assertThat(entitiesLayer.get(new Point(3, 6)), instanceOf(Wall.class));
        entitiesLayer.clear(new Point(3, 6));
        assertEquals(null, entitiesLayer.get(new Point(3, 6)));
    }

    @Test
    public void playerTest() {
        EntitiesLayer entitiesLayer = new EntitiesLayer(25, 21);

        assertEquals(null, entitiesLayer.getPlayer());
        assertEquals(null, entitiesLayer.get(new Point(12, 8)));

        entitiesLayer.put(new Player(new Point(12, 8), 40));

        assertEquals(12, entitiesLayer.getPlayer().getPosition().getX());
        assertEquals(8, entitiesLayer.getPlayer().getPosition().getY());
        assertNotEquals(null, entitiesLayer.get(new Point(12, 8)));
        assertThat(entitiesLayer.get(new Point(12, 8)), instanceOf(Player.class));
        assertThat(entitiesLayer.getPlayer(), instanceOf(Player.class));

        try {
            entitiesLayer.put(new Player(new Point(0, 3), 40));
            fail( "EntitiesLayer should throws exception when adding Player when there already is one" );
        } catch (IllegalArgumentException expectedException) {
        }

        entitiesLayer.clearPlayer();
        entitiesLayer.put(new Player(new Point(0, 3), 40));

        assertEquals(0, entitiesLayer.getPlayer().getPosition().getX());
        assertEquals(3, entitiesLayer.getPlayer().getPosition().getY());
        assertNotEquals(null, entitiesLayer.get(new Point(0, 3)));
        assertThat(entitiesLayer.get(new Point(0, 3)), instanceOf(Player.class));
        assertThat(entitiesLayer.getPlayer(), instanceOf(Player.class));

        assertEquals(entitiesLayer.get(new Point(12, 8)), null);
    }
}