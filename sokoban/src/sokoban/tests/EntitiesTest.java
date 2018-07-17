package sokoban.tests;

import org.junit.Test;
import sokoban.domain.Direction;
import sokoban.domain.Point;
import sokoban.entities.Box;
import sokoban.entities.Entity;
import sokoban.entities.Spot;

import static org.junit.Assert.assertEquals;

public class EntitiesTest {
    @Test
    public void entityTest() {
        Entity entity = new Box(new Point(10, 23), 40);

        assertEquals(10, entity.getPosition().getX());
        assertEquals(23, entity.getPosition().getY());

        entity.move(Direction.UP);
        assertEquals(10, entity.getPosition().getX());
        assertEquals(22, entity.getPosition().getY());

        entity.move(Direction.RIGHT);
        assertEquals(11, entity.getPosition().getX());
        assertEquals(22, entity.getPosition().getY());

        entity.move(Direction.DOWN);
        assertEquals(11, entity.getPosition().getX());
        assertEquals(23, entity.getPosition().getY());

        entity.move(Direction.LEFT);
        assertEquals(10, entity.getPosition().getX());
        assertEquals(23, entity.getPosition().getY());

        entity.setPosition(new Point(4, 5));
        assertEquals(4, entity.getPosition().getX());
        assertEquals(5, entity.getPosition().getY());
    }

    @Test
    public void spotTest() {
        Spot spot = new Spot(new Point(8, 1), 40);

        assertEquals(false, spot.isCorrect());

        spot.setCorrect(true);
        assertEquals(true, spot.isCorrect());

        spot.setCorrect(false);
        assertEquals(false, spot.isCorrect());
    }

    @Test
    public void boxTest() {
        Box box = new Box(new Point(2, 6), 40);

        assertEquals(false, box.isCorrect());

        box.setCorrect(true);
        assertEquals(true, box.isCorrect());

        box.setCorrect(false);
        assertEquals(false, box.isCorrect());
    }
}