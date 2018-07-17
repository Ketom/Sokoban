package sokoban.tests;

import org.junit.Test;
import sokoban.domain.Level;
import sokoban.logic.EditorBoard;

import static org.junit.Assert.assertEquals;

public class EditorBoardTest {
    @Test
    public void basicTest() {
        Level level = Level.example();
        EditorBoard editorBoard = new EditorBoard(level);

        assertEquals(level.getWidth(), editorBoard.getBoardWidth());
        assertEquals(level.getHeight(), editorBoard.getBoardHeight());

        Level levelFromBoard = editorBoard.toLevel();
        assertEquals(level.getWidth(), levelFromBoard.getWidth());
        assertEquals(level.getHeight(), levelFromBoard.getHeight());

        for(int y = 0; y < level.getHeight(); y++) {
            for (int x = 0; x < level.getWidth(); x++) {
                assertEquals(level.get(x, y), levelFromBoard.get(x, y));
            }
        }
    }
}