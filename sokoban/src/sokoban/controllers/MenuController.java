package sokoban.controllers;

import javafx.scene.input.KeyEvent;
import sokoban.Game;
import sokoban.domain.Mode;

import java.io.IOException;

/**
 * Controller for Playing game mode. It handles keystrokes.
 *
 * @author Ketom
 */
public class MenuController extends Controller {
    /**
     * Constructs MenuController associated with given Game. Appropriate scene
     * with given width and height is created and linked with this new
     * MenuController.
     * @param game Game object to which MenuController should be associated with
     * @param width Width of the Scene
     * @param height Height of the Scene
     * @throws IOException throws IOException when unable to read FXML file
     */
    public MenuController(Game game, int width, int height) throws IOException {
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
        return "/sokoban/fxml/menu.fxml";
    };

    /**
     * Method set up as handler for onKeyPressed() event for scene. It is
     * called when key is pressed.
     * @param e KeyEvent object from JavaFX
     */
    @Override
    public void onKeyPressed(KeyEvent e) {
        switch(e.getCode()) {
            case DIGIT1:
                game.changeMode(Mode.PLAYING);
                break;
            case DIGIT2:
                game.changeMode(Mode.EDITOR);
                break;
            case DIGIT3:
                game.changeMode(Mode.HELP);
                break;
            case ESCAPE:
                game.exit();
        }
    }
}
