import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PauseMenu {

    // variables
    private static final int SETTINGS_WINDOW_WIDTH = 600;
    private static final int SETTINGS_WINDOWS_HEIGHT = 600;

    private VBox vBox;

    private Stage pauseMenuWindow;
    private Scene pauseMenuScene;
    private boolean returnCall  = false;

    private Button returnButton;
    private Button backToMenu;

    private Slider volumeBar;

    private int volume;


    public PauseMenu() {

        pauseMenuWindow = new Stage();
        pauseMenuWindow.setTitle( "Pause Menu");
        StackPane pauseLayout = new StackPane();

        volume = 100;
        //volumeBar;


        addSettingsComponents();
        pauseLayout.getChildren().add(vBox);
        pauseMenuScene = new Scene( pauseLayout, SETTINGS_WINDOW_WIDTH, SETTINGS_WINDOWS_HEIGHT);
        pauseMenuWindow.setScene( pauseMenuScene);
    }

    public void addSettingsComponents(){
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(3.0);
        vBox.setFillWidth(true);
        vBox.getChildren().addAll( volumeBar, returnButton, backToMenu);
    }

    public void showPauseMenu() {
        pauseMenuWindow.showAndWait();
    }

    public void closeSettings(){
        pauseMenuWindow.close();
    }



}
