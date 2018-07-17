package sokoban.controllers;

import javafx.scene.input.KeyEvent;
import sokoban.Game;
import sokoban.domain.Mode;

import java.io.IOException;

/**
 * Controller for Help game mode. It handles keystrokes.
 *
 * @author Ketom
 */
public class HelpController extends Controller {
    /**
     * Constructs HelpController associated with given Game. Appropriate scene
     * with given width and height is created and linked with this new
     * HelpController.
     * @param game Game object to which HelpController should be associated with
     * @param width Width of the Scene
     * @param height Height of the Scene
     * @throws IOException throws IOException when unable to read FXML file
     */
    public HelpController(Game game, int width, int height) throws IOException {
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
        return "/sokoban/fxml/help.fxml";
    };

    /**
     * Method set up as handler for onKeyPressed() event for scene. It is
     * called when key is pressed.
     * @param e KeyEvent object from JavaFX
     */
    @Override
    public void onKeyPressed(KeyEvent e) {
        switch(e.getCode()) {
            case ESCAPE:
                game.changeMode(Mode.MENU);
        }
    }
}
