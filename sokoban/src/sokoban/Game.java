package sokoban;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import sokoban.controllers.EditorController;
import sokoban.controllers.HelpController;
import sokoban.controllers.MenuController;
import sokoban.controllers.PlayingController;
import sokoban.domain.Mode;

/**
 * Main class of the game. It manages the stage and changes scenes.
 *
 * @author Ketom
 */
public class Game extends Application {
    /**
     * Width of the Game window in pixels.
     */
    private static final int WIDTH = 800;

    /**
     * Height of the Game window in pixels.
     */
    private static final int HEIGHT = 600;

    /**
     * Stage of the Game being window in all scenes are displayed.
     */
    private Stage stage;

    /**
     * Controller of the menu scene.
     */
    private MenuController menuController;

    /**
     * Controller of the editor scene.
     */
    private EditorController editorController;

    /**
     * Controller of the playing scene.
     */
    private PlayingController playingController;

    /**
     * Controller of the help scene.
     */
    private HelpController helpController;

    /**
     * Main method of class.
     * @param args arguments passed to application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Constructs Game object. Called automatically by JavaFX.
     */
    public Game() {
        super();
    }

    /**
     * Entry point for JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Sokoban");

        // needs to be actually bigger than desired, looks like bug
        stage.setMinWidth(WIDTH + 10);
        stage.setMinHeight(HEIGHT + 30);

        menuController = new MenuController(this, WIDTH, HEIGHT);
        editorController = new EditorController(this, WIDTH, HEIGHT);
        playingController = new PlayingController(this, WIDTH, HEIGHT);
        helpController = new HelpController(this, WIDTH, HEIGHT);

        changeMode(Mode.MENU);
        stage.show();
    }

    /**
     * Changes current mode in which game is and switches scene accordingly.
     * @param mode mode to which game should switch
     */
    public void changeMode(Mode mode) {
        switch (mode) {
            case MENU:
                stage.setScene(menuController.getScene());
                break;
            case PLAYING:
                stage.setScene(playingController.getScene());
                break;
            case EDITOR:
                stage.setScene(editorController.getScene());
                break;
            case HELP:
                stage.setScene(helpController.getScene());
                break;
        }
    }

    /**
     * Terminates application.
     */
    public void exit() {
        Platform.exit();
        System.exit(0);
    }
}
