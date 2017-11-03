import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PauseMenu implements EventHandler<ActionEvent> {

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
        initSlider();
        volumeBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                int new_value = new_val.intValue();
                changeVolume( new_value);
            }
        });

        returnButton = new Button("Return");
        backToMenu = new Button("Back to Menu");
        returnButton.setOnAction(this);
        backToMenu.setOnAction( event -> {
            pauseMenuWindow.close();
            returnCall = true;
        });

        addSettingsComponents();
        pauseLayout.getChildren().add(vBox);
        pauseMenuScene = new Scene( pauseLayout, SETTINGS_WINDOW_WIDTH, SETTINGS_WINDOWS_HEIGHT);
        pauseMenuWindow.setScene( pauseMenuScene);
    }

    private void changeVolume( int volume) {
        this.volume = volume;
        volumeBar.setValue( volume);
    }

    @Override
    public void handle(ActionEvent event) {
        if( event.getSource() == returnButton) {
            // should return to the game screen
        }
    }
//
    private void initSlider() {
        volumeBar = new Slider();
        volumeBar.setMin(0);
        volumeBar.setMax(100);
        volumeBar.setValue(volume);

        volumeBar.setShowTickLabels( true);
        volumeBar.setShowTickMarks( true);
        volumeBar.setMajorTickUnit( 50);
        volumeBar.setBlockIncrement( 10);
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
