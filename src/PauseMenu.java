import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class PauseMenu implements EventHandler<ActionEvent> {

    // variables
    private static final int SETTINGS_WINDOW_WIDTH = 400;
    private static final int SETTINGS_WINDOWS_HEIGHT = 400;

    private VBox vBox;
    private boolean answer;
    private Label pauseMenuLabel;

    private Stage pauseMenuWindow;
    private Scene pauseMenuScene;
    private boolean returnCall  = false;

    private Button returnButton;
    private Button backToMenu;

    private Slider volumeBar;
    private Menu menu;
    private int volume;


    public PauseMenu() {
        Image im = new Image(Paths.get("."+"/MediaFiles/metal.png").toUri().toString(), true);
        menu = new Menu();
        pauseMenuWindow = new Stage();
        pauseMenuWindow.setTitle( "Pause Menu");
        pauseMenuLabel = new Label("Pause Menu");
        pauseMenuLabel.setId("welcome-text");
        StackPane pauseLayout = new StackPane();

        volume = 100;
        initSlider();
        initButtons();

        addSettingsComponents();
        pauseLayout.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        pauseLayout.getChildren().add(vBox);
        pauseMenuScene = new Scene( pauseLayout, SETTINGS_WINDOW_WIDTH, SETTINGS_WINDOWS_HEIGHT);
        String  style = getClass().getResource("style.css").toExternalForm();
        pauseMenuScene.getStylesheets().add(style);
        pauseMenuWindow.setScene( pauseMenuScene);
    }

    public boolean isReturnCall() {
        return returnCall;
    }

    public void initButtons() {
        // buttons
        returnButton = new Button("Return");
        returnButton.setOnAction(this);
        returnButton.setId("glass-grey");
        returnButton.setPrefSize(100, 10);
        returnButton.setOnMouseEntered(new EventHandler<MouseEvent>
                () {
            @Override
            public void handle(MouseEvent t) {
                returnButton.setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        returnButton.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                returnButton.setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });
        backToMenu = new Button("Back");
        backToMenu.setOnAction( event -> {
            pauseMenuWindow.close();
            returnCall = true;
        });
        backToMenu.setId("glass-grey");
        backToMenu.setPrefSize(100, 10);
        backToMenu.setOnMouseEntered(new EventHandler<MouseEvent>
                () {
            @Override
            public void handle(MouseEvent t) {
                backToMenu.setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        backToMenu.setOnMouseExited(new EventHandler<MouseEvent>
                () {
            @Override
            public void handle(MouseEvent t) {
                backToMenu.setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });
    }


    public void showMenu() {
        pauseMenuWindow.show();
    }

    private void changeVolume( int volume) {
        this.volume = volume;
        volumeBar.setValue( volume);
    }

    @Override
    public void handle(ActionEvent event) {
        if( event.getSource() == returnButton) {
            pauseMenuWindow.close();
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

        volumeBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                int new_value = new_val.intValue();
                changeVolume( new_value);
            }
        });

    }

    public void addSettingsComponents(){
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(3.0);
        vBox.setFillWidth(true);
        vBox.getChildren().addAll( pauseMenuLabel, volumeBar, returnButton, backToMenu);
    }

    public void showPauseMenu() {
        pauseMenuWindow.showAndWait();
    }
    public void closeSettings(){
        pauseMenuWindow.close();
    }

}
