package sokoban.tests;

import org.junit.Test;
import sokoban.domain.Level;
import sokoban.logic.PlayingBoard;

import static org.junit.Assert.assertEquals;

public class PlayingBoardTest {
    @Test
    public void basicTest() {
        Level level = Level.example();
        PlayingBoard playingBoard = new PlayingBoard(level);

        assertEquals(level.getWidth(), playingBoard.getBoardWidth());
        assertEquals(level.getHeight(), playingBoard.getBoardHeight());

        Level levelFromBoard = playingBoard.toLevel();
        assertEquals(level.getWidth(), levelFromBoard.getWidth());
        assertEquals(level.getHeight(), levelFromBoard.getHeight());

        for(int y = 0; y < level.getHeight(); y++) {
            for (int x = 0; x < level.getWidth(); x++) {
                assertEquals(level.get(x, y), levelFromBoard.get(x, y));
            }
        }
    }
}