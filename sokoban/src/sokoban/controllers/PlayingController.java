package sokoban.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sokoban.domain.Direction;
import sokoban.Game;
import sokoban.domain.Level;
import sokoban.domain.Mode;
import sokoban.logic.PlayingBoard;

import java.io.File;
import java.io.IOException;

import static sokoban.domain.Direction.*;

/**
 * Controller for Menu game mode. It manages creating PlayingBoard and
 * handles keystrokes.
 *
 * @author Ketom
 */
public class PlayingController extends Controller {
    /**
     * Main pane of Scene in which PlayingBoard will be placed.
     */
    @FXML
    private StackPane mainPane;

    /**
     * Text label displaying how many times the player has moved.
     */
    @FXML
    private Text playerMovesCountText;

    /**
     * Text label displaying how many times a box has been moved.
     */
    @FXML
    private Text boxesMovesCountText;

    /**
     * Overlay Pane containing containing information about the end of the game.
     */
    @FXML
    private Pane overlayPane;

    /**
     * PlayingBoard representing current state of game board.
     */
    private PlayingBoard playingBoard;

    /**
     * Last loaded Level from file.
     */
    private Level lastLevel;

    /**
     * Last Directory from which a Level was loaded.
     */
    private File lastDirectory;

    /**
     * Constructs PlayerController associated with given Game. Appropriate scene
     * with given width and height is created and linked with this new
     * PlayerController.
     *
     * @param game   Game object to which PlayerController should be associated with
     * @param width  Width of the Scene
     * @param height Height of the Scene
     * @throws IOException throws IOException when unable to read FXML file
     */
    public PlayingController(Game game, int width, int height) throws IOException {
        super(game, width, height);
    }

    /**
     * Returns path to FXML file which contains Scene associated with this
     * Controller. Must be overwritten by derived class, so parent constructor
     * can work properly.
     *
     * @return path to FXML file
     */
    @Override
    protected String getFxmlPath() {
        return "/sokoban/fxml/playing.fxml";
    }

    /**
     * Method called automatically by FXMLLoader after loading FXML file
     * containing definition of Scene.
     */
    @Override
    public void initialize() {
        resetPlayingBoard();
    }

    /**
     * Replace current PlayingBoard with new PlayingBoard representing given
     * Level.
     *
     * @param newLevel Level on which new PlayingBoard should be based. Null
     *                 can be passed, indicating that default Level should be
     *                 used.
     */
    private void resetPlayingBoard(Level newLevel) {
        lastLevel = newLevel;
        resetPlayingBoard();
    }

    /**
     * Replace current PlayingBoard with new PlayingBoard representing
     * previously used Level.
     */
    private void resetPlayingBoard() {
        Level level = lastLevel;
        if (level == null) {
            level = Level.example();
        }
        mainPane.getChildren().remove(playingBoard);
        playingBoard = new PlayingBoard(level);
        mainPane.getChildren().add(0, playingBoard);
        update();
    }

    /**
     * Method set up as handler for onKeyPressed() event for scene. It is
     * called when key is pressed.
     *
     * @param e KeyEvent object from JavaFX
     */
    @Override
    public void onKeyPressed(KeyEvent e) {
        Direction direction = null;
        switch (e.getCode()) {
            case ESCAPE:
                game.changeMode(Mode.MENU);
            case UP:
            case W:
                direction = UP;
                break;
            case RIGHT:
            case D:
                direction = RIGHT;
                break;
            case DOWN:
            case S:
                direction = DOWN;
                break;
            case LEFT:
            case A:
                direction = LEFT;
                break;
            case DIGIT1:
                changeLevel();
                break;
            case DIGIT2:
            case R:
                resetPlayingBoard(null);
                break;
        }
        if (!playingBoard.isWinConditionFulfilled() && direction != null) {
            playingBoard.movePlayer(direction);
            update();
        }
    }

    /**
     * Update Scene Texts and Panes visibility accordingly to current state.
     */
    private void update() {
        playerMovesCountText.setText("Number of moves: " + playingBoard.getPlayerMovesCount());
        boxesMovesCountText.setText("Box move count: " + playingBoard.getBoxesMovesCount());
        if (playingBoard.isWinConditionFulfilled()) {
            overlayPane.setVisible(true);
        } else {
            overlayPane.setVisible(false);
        }
    }

    /**
     * Prompts user to select file containing level and replaces current
     * Level with user selected one.
     */
    private void changeLevel() {
        File file = chooseLevelFileLoad(lastDirectory);
        if (file == null) {
            return;
        }
        try {
            resetPlayingBoard(Level.fromFile(file));
            lastDirectory = file.getParentFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays window allowing to choose file from which Level should be loaded.
     *
     * @param initialDirectory directory in which browsing should start
     * @return file selected by user
     */
    private File chooseLevelFileLoad(File initialDirectory) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(initialDirectory);
        chooser.setTitle("Choose map");
        File file = chooser.showOpenDialog(new Stage());
        return file;
    }
}
