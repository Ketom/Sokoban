package sokoban.tests;

import org.junit.Test;
import sokoban.domain.Direction;
import sokoban.domain.Point;

import static org.junit.Assert.assertEquals;

public class PointTest {
    @Test
    public void getTest() {
        Point point;
        point = new Point(5, 3);
        assertEquals(5, point.getX());
        assertEquals(3, point.getY());

        point = new Point(-4, 4);
        assertEquals(-4, point.getX());
        assertEquals(4, point.getY());

        point = new Point(9, -88);
        assertEquals(9, point.getX());
        assertEquals(-88, point.getY());
    }

    @Test
    public void setTest() {
        Point point = new Point(1, 9);
        point.setX(30);
        assertEquals(30, point.getX());

        point.setX(-5);
        assertEquals(-5, point.getX());

        point.setX(-5);
        assertEquals(-5, point.getX());

        point.setY(-21);
        assertEquals(-21, point.getY());

        point.setY(8);
        assertEquals(8, point.getY());

        point.setX(17);
        point.setY(-1);
        assertEquals(17, point.getX());
        assertEquals(-1, point.getY());

        point.setX(43);
        point.setY(21);
        assertEquals(43, point.getX());
        assertEquals(21, point.getY());
    }

    @Test
    public void moveTest() {
        Point point = new Point(4, 2);

        point.move(61, 23);
        assertEquals(61, point.getX());
        assertEquals(23, point.getY());

        point.move(-6, 4);
        assertEquals(-6, point.getX());
        assertEquals(4, point.getY());

        point.move(0, 0);
        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
    }

    @Test
    public void translateXYTest() {
        Point point = new Point(-5, 7);

        point.translate(4, -1);
        assertEquals(-1, point.getX());
        assertEquals(6, point.getY());

        point.translate(0, 0);
        assertEquals(-1, point.getX());
        assertEquals(6, point.getY());

        point.translate(-7, -40);
        assertEquals(-8, point.getX());
        assertEquals(-34, point.getY());

        point.translate(100, 200);
        assertEquals(92, point.getX());
        assertEquals(166, point.getY());
    }

    @Test
    public void translateDirectionTest() {
        Point point = new Point(9, -4);

        point.translate(Direction.UP);
        assertEquals(9, point.getX());
        assertEquals(-5, point.getY());

        point.translate(Direction.LEFT);
        assertEquals(8, point.getX());
        assertEquals(-5, point.getY());

        point.translate(Direction.DOWN);
        assertEquals(8, point.getX());
        assertEquals(-4, point.getY());

        point.translate(Direction.RIGHT);
        assertEquals(9, point.getX());
        assertEquals(-4, point.getY());
    }

}