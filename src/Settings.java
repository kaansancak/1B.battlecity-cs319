
/*
 @Description: this class is going to be the screen class for Options from the Menu
 the idea of the class is to have
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Settings implements EventHandler<ActionEvent> {

    private static final int SETTINGS_WINDOW_WIDTH = 600;
    private static final int SETTINGS_WINDOWS_HEIGHT = 600;
    FileManager file;
    BorderPane border;
    Menu menu;
    // Player 1 Items
    Label player1Label;
    ArrayList<String> player1_keyList;
    ComboBox player1_settings;
    Label player1l;
    Label player2l;
    // Player 2 Items
    Label player2Label;
    ArrayList<String> player2_keyList;
    ComboBox player2_settings;
    // Buttons
    Button submit;
    Button backToMenu;
    MediaPlayer player;
    private Stage settingsWindow;
    private Scene settingsScene;
    private boolean returnCall  = false;
    private VBox player1Box;
    private VBox player2Box;
    private HBox soundBox;
    private HBox buttonBox;
    private Label settings;
    // Sound part
    private int volume;
    private CheckBox mute;
    private Slider volumeBar;
    private ComboBox<String> musicBox;
    private ArrayList<Media> media;
    private int latestVolume;


    public Settings(){
        menu = new Menu();
        border = new BorderPane();
        player1Box = new VBox();
        player2Box = new VBox();
        soundBox = new HBox();
        buttonBox = new HBox();
        border.setTop(soundBox);
        border.setLeft(player1Box);
        border.setRight(player2Box);
        border.setBottom(buttonBox);

        file = new FileManager();
        media = file.getScannedAudios();
        settingsWindow = new Stage();
        settingsWindow.setTitle( "Settings");
        settings = new Label("Settings");
        settings.setId("welcome-text");

        StackPane settingsLayout = new StackPane();

        Image im = new Image(Paths.get("."+"/MediaFiles/metal.png").toUri().toString(), true);
        settingsLayout.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        player1_keyList = new ArrayList<String>(0);
        player2_keyList = new ArrayList<String>(0);

        // initialize the default volume
        volume = 100;
        latestVolume = 100;

        initPlayerLabels();
        initCheckBox();
        initSlider();
        initComboboxes();
        initButtons();
        addSettingsComponents();

        settingsLayout.getChildren().addAll(settings);
        settingsLayout.setAlignment(Pos.TOP_CENTER);
        settingsLayout.setPadding( new Insets(50, 150, 50, 150));
        settingsLayout.getChildren().add(border);
        settingsScene = new Scene( settingsLayout, SETTINGS_WINDOW_WIDTH, SETTINGS_WINDOWS_HEIGHT);
        String  style = getClass().getResource("style.css").toExternalForm();
        settingsScene.getStylesheets().add(style);
        settingsWindow.setScene( settingsScene);
    }

    public void addSettingsComponents(){
        soundBox.setPadding(new Insets(200, 100, 50, 120));
        soundBox.setSpacing(40);
        soundBox.getChildren().addAll( volumeBar, mute, musicBox);

        player1Box.setPadding(new Insets(50, 30, 50, 100));
        player1Box.setSpacing(10);
        player1Box.getChildren().addAll( player1Label, player1l,  player1_settings);

        player2Box.setPadding(new Insets(50, 100, 50, 30));
        player2Box.setSpacing(10);
        player2Box.getChildren().addAll( player2Label, player2l, player2_settings);

        buttonBox.setPadding(new Insets(30, 200, 50, 200));
        buttonBox.setSpacing(30);
        buttonBox.getChildren().addAll( submit, backToMenu);
    }

    public void showSettings(){
        settingsWindow.showAndWait();
    }

    public void closeSettings(){
        settingsWindow.close();
    }
    private void selectBackGroundMusic( String song) {
        if (song.equals("Song 1"))
            menu.changeTheSong("song1");
        else if (song.equals("Song 2"))
            menu.changeTheSong("song2");
        else if (song.equals("Song 3"))
            menu.changeTheSong("song3");
        else
            menu.changeTheSong("song4");
    }

    private void initCheckBox() {
        mute = new CheckBox( "Mute");
        mute.setSelected( true);
        mute.setId("labels");

        // setting listener for the checkboxes
        mute.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                if( mute.isSelected()) {
                    System.out.println(mute.isSelected());
                    mute();
                }
                else {
                    unmute();
                }
            }
        });
    }

    private void initSlider() {
        volumeBar = new Slider();
        volumeBar.setMin(0);
        volumeBar.setMax(100);
        volumeBar.setValue(100);
        volumeBar.setMaxWidth(350);
        volumeBar.setMajorTickUnit( 50);
        volumeBar.setBlockIncrement( 10);

        // slider
        volumeBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                int new_value = new_val.intValue();
                changeVolume( new_value);
            }
        });
    }

    private void initButtons() {
        // buttons
        submit = new Button("Submit");
        submit.setOnAction(this);
        submit.setId("glass-grey");
        submit.setPrefSize(100, 10);
        submit.setOnMouseEntered(new EventHandler<MouseEvent>
                () {
            @Override
            public void handle(MouseEvent t) {
                submit.setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        submit.setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                submit.setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });
        backToMenu = new Button("Back");
        backToMenu.setOnAction( event -> {
            settingsWindow.close();
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

    private void initComboboxes() {
        // player 1 combobox//
        player1_settings = new ComboBox();
        player1_settings.setPromptText("Player 1");
        player1_settings.setEditable(true);
        player1_settings.getItems().addAll(
                "4, 8, 6, 5, ENTER",
                "1, 5, 3, 2, SHIFT"
        );

        player1_settings.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                changeKeyList( newValue);
            }
        });

        //add style

        // player 2 combobox
        player2_settings = new ComboBox();
        player2_settings.setPromptText("Player 1");
        player2_settings.setEditable(true);
        player2_settings.getItems().addAll(
                "S, E, F, D, SPACE",
                "D, R, G, F, CNTRL"
        );
        player2_settings.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                changeKeyList( newValue);
            }
        });
        // add style

        //musicbox combobox
        musicBox = new ComboBox<String>();
        musicBox.getItems().addAll(
                "Song 1",
                "Song 2",
                "Song 3",
                "Song 4"
        );

        // add listener
        musicBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectBackGroundMusic( newValue);
            }
        });
        // add style
    }

    private void initPlayerLabels() {
        // player 1 items
        player1_keyList.add( "LEFT");
        player1_keyList.add( "UP");
        player1_keyList.add( "RIGHT");
        player1_keyList.add( "DOWN");
        player1_keyList.add( "SPACEBAR");
        player1Label = new Label( "Player 1 keys\n");
        player1l = new Label(player1_keyList.toString());
        player1Label.setId("labels");
        player1l.setId("labels");

        // player 2 items
        player2_keyList.add( "A");
        player2_keyList.add( "W");
        player2_keyList.add( "D");
        player2_keyList.add( "S");
        player2_keyList.add( "Z");
        player2Label = new Label("Player 2 keys\n");
        player2Label.setId("labels");
        player2l = new Label(player2_keyList.toString());
        player2l.setId("labels");
    }

    private void mute() {
        latestVolume = volume;
        volume = 0;
        changeVolume( volume); // will change the slider too
        menu.stopSong();
    }

    private void unmute() {
        volume = latestVolume;
        changeVolume(volume);
        menu.playSong();
    }
    private void changeVolume( int volume) {
        this.volume = volume;
        volumeBar.setValue( volume);
        Menu.player.setVolume(volume);
    }
    private int getVolume() {
        return volume;
    }
    private void changeKeyList( String newKeyList){

        int player_id;
        if( newKeyList.charAt(0) == '4' || newKeyList.charAt(0) == '1')
        {
            player_id = 1;
            player1_keyList.set(0, Character.toString(newKeyList.charAt(0)));
            player1_keyList.set(1, Character.toString(newKeyList.charAt(0)));
            player1_keyList.set(2, Character.toString(newKeyList.charAt(3)));
            player1_keyList.set(3, Character.toString(newKeyList.charAt(6)));
            player1_keyList.set(4, Character.toString(newKeyList.charAt(9)));
            player1_keyList.set(5, newKeyList.substring( 12, 16));
        }
        else
        {
            player_id = 2;
            player2_keyList.set(0, Character.toString(newKeyList.charAt(0)));
            player2_keyList.set(1, Character.toString(newKeyList.charAt(0)));
            player2_keyList.set(2, Character.toString(newKeyList.charAt(3)));
            player2_keyList.set(3, Character.toString(newKeyList.charAt(6)));
            player2_keyList.set(4, Character.toString(newKeyList.charAt(9)));
            player2_keyList.set(5, newKeyList.substring( 12, 16));
        }
        updateUI( player_id);
    }

    private void updateUI( int player_id) {

        if( player_id == 1)
            player1Label = new Label( "Player 1 keys\n" + player1_keyList.toString());
        else
            player2Label = new Label( "Player 1 keys\n" + player2_keyList.toString());
    }

    private ArrayList<String> getTheKeyList( int player_id) {

        if( player_id == 1)
            return player1_keyList;
        else
            return player2_keyList;
    }

    public boolean isReturnCall() {
        return returnCall;
    }

    @Override
    public void handle(ActionEvent event) {
        if( event.getSource() == submit) {
            update();
        }
    }

    private void update() {
        player1Label = new Label( "Player 1 keys\n" + player1_keyList.toString());
        player2Label = new Label( "Player 1 keys\n" + player2_keyList.toString());
    }
}
