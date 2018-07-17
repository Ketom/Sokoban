package sokoban.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import sokoban.Game;

import java.io.IOException;

/**
 * Generic Controller for game mode. It sets up scene and provides some
 * methods which can be overwritten by derived classes.
 * @author Ketom
 */
abstract public class Controller {
    /**
     * Game object associated with this Controller. Used to changing game mode.
     */
    protected Game game;

    /**
     * Scene associated with this controller.
     */
    protected Scene scene;

    /**
     * Constructs Controller associated with given Game. Appropriate scene
     * with given width and height is created and linked with this new
     * Controller.
     * @param game Game object to which Controller should be associated with
     * @param width Width of the Scene
     * @param height Height of the Scene
     * @throws IOException throws IOException when unable to read FXML file
     */
    public Controller(Game game, int width, int height) throws IOException {
        this.game = game;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(getFxmlPath()));
        loader.setController(this);
        Parent root = loader.load();
        scene = new Scene(root, width, height);
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    /**
     * Returns path to FXML file which contains Scene associated with this
     * Controller. Must be overwritten by derived class, so parent constructor
     * can work properly.
     * @return path to FXML file
     */
    abstract protected String getFxmlPath();

    /**
     * Returns Scene associated with this Controller.
     * @return Scene associated with this Controller.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Method called automatically by FXMLLoader after loading FXML file
     * containing definition of Scene.
     */
    public void initialize() {
    }

    /**
     * Method set up as handler for onKeyPressed() event for scene. It is
     * called when key is pressed.
     * @param e KeyEvent object from JavaFX
     */
    public void onKeyPressed(KeyEvent e) {
    }
}
