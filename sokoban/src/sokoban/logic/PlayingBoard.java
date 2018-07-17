package sokoban.logic;

import sokoban.domain.Direction;
import sokoban.domain.Level;
import sokoban.domain.Point;
import sokoban.entities.Box;
import sokoban.entities.Entity;
import sokoban.entities.Player;
import sokoban.entities.Spot;


/**
 * A game board suited for playing levels. Contains Player which can be moved
 * according to rules of the game, and can push boxes.
 * @author Ketom
 */
public class PlayingBoard extends Board {
    /**
     * Counter how many times the player has moved.
     */
    private int playerMovesCount;

    /**
     * Counter how many times a box has been moved.
     */
    private int boxesMovesCount;

    /**
     * Is win condition fulfilled
     */
    private boolean isWinConditionFulfilled;

    /**
     * Constructs PlayerBoard based on given Level.
     * @param level Level from which board will be created.
     */
    public PlayingBoard(Level level) {
        super(level);

        playerMovesCount = 0;
        boxesMovesCount = 0;

        updateWinCondition();
    }

    /**
     * Moves Player in given direction according to rules in the game.
     * @param direction The direction in which Player will be moved.
     */
    public void movePlayer(Direction direction) {
        Player player = playerLayer.getPlayer();

        Point currentPlayerPosition = player.getPosition();
        Point newPlayerPosition = new Point(currentPlayerPosition);
        newPlayerPosition.translate(direction);

        player.setDirection(direction);

        if(!isPointValid(newPlayerPosition)) {
            return;
        }

        Entity entity = playerLayer.get(newPlayerPosition);

        if(entity == null) {
            playerLayer.clearPlayer();
            player.move(direction);
            playerLayer.put(player);
        } else {
            if(entity instanceof Box) {
                Box box = (Box) entity;
                Point newBoxPosition = new Point(newPlayerPosition);
                newBoxPosition.translate(direction);

                if(!isPointValid(newBoxPosition) || playerLayer.get(newBoxPosition) != null) {
                    return;
                }

                playerLayer.clearPlayer();
                playerLayer.clear(newPlayerPosition);

                player.move(direction);
                box.move(direction);

                playerLayer.put(player);
                playerLayer.put(box);

                Spot spot = (Spot) spotsLayer.get(newBoxPosition);
                if(spot != null) {
                    box.setCorrect(true);
                    spot.setCorrect(true);
                } else {
                    box.setCorrect(false);
                }

                spot = (Spot) spotsLayer.get(newPlayerPosition);
                if(spot != null) {
                    spot.setCorrect(false);
                }
                boxesMovesCount++;
                updateWinCondition();
            } else {
                return;
            }
        }
        playerMovesCount++;
    }

    /**
     * Returns if is win condition fulfilled
     * @return true if is win condition fulfilled; false otherwise.
     */
    public boolean isWinConditionFulfilled() {
        return isWinConditionFulfilled;
    }

    /**
     * Recalculates if is win condition fulfilled
     */
    private void updateWinCondition() {
        Spot spot;
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                spot = (Spot) spotsLayer.get(new Point(x, y));
                if(spot != null && !spot.isCorrect()) {
                    isWinConditionFulfilled = false;
                    return;
                }
            }
        }
        isWinConditionFulfilled = true;
    }

    /**
     * Returns value of counter how many times the player has moved.
     * @return how many times the player has moved.
     */
    public int getPlayerMovesCount() {
        return playerMovesCount;
    }

    /**
     * Returns value of counter how many times a box has been moved.
     * @return how many times a box has been moved.
     */
    public int getBoxesMovesCount() {
        return boxesMovesCount;
    }
}
