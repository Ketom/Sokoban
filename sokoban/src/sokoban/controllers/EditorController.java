package sokoban.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sokoban.domain.Direction;
import sokoban.Game;
import sokoban.domain.Level;
import sokoban.domain.Mode;
import sokoban.logic.EditorBoard;

import java.io.File;
import java.io.IOException;

import static sokoban.domain.Direction.*;

/**
 * Controller for Editor game mode. It manages creating EditorBoard and
 * handles keystrokes.
 *
 * @author Ketom
 */
public class EditorController extends Controller {
    /**
     * Main pane of Scene in which EditorBoard will be placed.
     */
    @FXML
    private StackPane mainPane;

    /**
     * Text label displaying current level size.
     */
    @FXML
    private Text currentMapSizeText;

    /**
     * Text label displaying new level size.
     */
    @FXML
    private Text newMapSizeText;

    /**
     * EditorBoard representing current state of game board.
     */
    private EditorBoard editorBoard;

    /**
     * Last loaded Level from file.
     */
    private Level lastLevel;

    /**
     * Last Directory from which a Level was loaded.
     */
    private File lastDirectory;


    /**
     * The width that a New EditorBoard will have when it will be created.
     */
    private int newBoardWidth;

    /**
     * The height that a New EditorBoard will have when it will be created.
     */
    private int newBoardHeight;

    /**
     * Constructs EditorController associated with given Game. Appropriate scene
     * with given width and height is created and linked with this new
     * EditorController.
     * @param game Game object to which EditorController should be associated with
     * @param width Width of the Scene
     * @param height Height of the Scene
     * @throws IOException throws IOException when unable to read FXML file
     */
    public EditorController(Game game, int width, int height) throws IOException {
        super(game, width, height);
    }

    /**
     * Returns path to FXML file which contains Scene associated with this
     * Controller. Must be overwritten by derived class, so parent constructor
     * can work properly.
     * @return path to FXML file
     */
    @Override
    protected String getFxmlPath() {
        return "/sokoban/fxml/editor.fxml";
    }

    /**
     * Method set up as handler for onKeyPressed() event for scene. It is
     * called when key is pressed.
     * @param e KeyEvent object from JavaFX
     */
    public void onKeyPressed(KeyEvent e) {
        Direction direction = null;
        switch(e.getCode()) {
            case ESCAPE:
                game.changeMode(Mode.MENU);
                break;
            case UP:
                direction = UP;
                break;
            case RIGHT:
                direction = RIGHT;
                break;
            case DOWN:
                direction = DOWN;
                break;
            case LEFT:
                direction = LEFT;
                break;
            case BACK_SLASH:
                resetEditorBoard();
                break;
            case P:
                resetEditorBoard(null);
                break;
            case Q:
                editorBoard.placeEmpty();
                break;
            case W:
                editorBoard.placeWall();
                break;
            case A:
                editorBoard.placeBox();
                break;
            case D:
                editorBoard.placeSpot();
                break;
            case S:
                editorBoard.placePlayer();
                break;
            case O:
                saveLevel();
                break;
            case L:
                changeLevel();
                break;
            case DIGIT9:
                newBoardWidth--;
                if(newBoardWidth < 1) {
                    newBoardWidth = 1;
                }
                update();
                break;
            case DIGIT0:
                newBoardWidth++;
                update();
                break;
            case MINUS:
                newBoardHeight--;
                if(newBoardHeight < 1) {
                    newBoardHeight = 1;
                }
                update();
                break;
            case EQUALS:
            case PLUS:
                newBoardHeight++;
                update();
                break;
        }
        if (direction != null) {
            editorBoard.moveCursor(direction);
        }
    }

    /**
     * Method called automatically by FXMLLoader after loading FXML file
     * containing definition of Scene.
     */
    @Override
    public void initialize() {
        newBoardHeight = 10;
        newBoardWidth = 10;
        resetEditorBoard();
    }

    /**
     * Replace current PlayingBoard with new PlayingBoard representing given
     * Level.
     * @param newLevel Level on which new PlayingBoard should be based. Null
     *                 can be passed, indicating that default Level should be
     *                 used.
     */
    private void resetEditorBoard(Level newLevel) {
        lastLevel = newLevel;
        resetEditorBoard();
    }

    /**
     * Replace current PlayingBoard with new PlayingBoard representing
     * previously used Level.
     */
    private void resetEditorBoard() {
        Level level = lastLevel;
        if(level == null) {
            level = new Level(newBoardWidth, newBoardHeight);
        }
        mainPane.getChildren().remove(editorBoard);
        editorBoard = new EditorBoard(level);
        mainPane.getChildren().add(0, editorBoard);
        update();
    }

    /**
     * Update Scene Texts and Panes visibility accordingly to current state.
     */
    private void update() {
        currentMapSizeText.setText("Size of current map: " + editorBoard.getBoardWidth() + "x" + editorBoard.getBoardHeight());
        newMapSizeText.setText("Size of new map: " + newBoardWidth + "x" + newBoardHeight);
    }

    /**
     * Prompts user to select file containing level and replaces current
     * Level with user selected one.
     */
    private void changeLevel() {
        File file = chooseLevelFileLoad(lastDirectory);
        if(file == null) {
            return;
        }
        try {
            resetEditorBoard(Level.fromFile(file));
            lastDirectory = file.getParentFile();
            if(editorBoard != null) {
                newBoardWidth = editorBoard.getBoardWidth();
                newBoardHeight = editorBoard.getBoardHeight();
            }
            update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prompts user to select file to which Level should be saved, and
     * saves current state of EditorBoard as a level.
     */
    private void saveLevel() {
        lastLevel = editorBoard.toLevel();
        File file = chooseLevelFileSave(lastDirectory);
        if(file == null) {
            return;
        }
        try {
            lastLevel.toFile(file);
            lastDirectory = file.getParentFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays window allowing to choose file from which Level should be loaded.
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

    /**
     * Displays window allowing to choose file to which Level should be saved.
     * @param initialDirectory directory in which browsing should start
     * @return file selected by user
     */
    private File chooseLevelFileSave(File initialDirectory) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(initialDirectory);
        chooser.setTitle("Choose file");
        File file = chooser.showSaveDialog(new Stage());
        return file;
    }
}
