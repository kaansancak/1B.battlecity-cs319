package UserInterface.SettingsPackage;
/*
 @Description: this class is going to be the screen class for Options from the UserInterface.MenuPackage.Menu
 the idea of the class is to have
 */

import Management.FileManager;
import Management.InputController;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Settings implements EventHandler<ActionEvent> {

    private static final int SETTINGS_WINDOW_WIDTH = 600;
    private static final int SETTINGS_WINDOWS_HEIGHT = 600;
    FileManager file;
    BorderPane border;
    UserInterface.MenuPackage.Menu menu;
    // GameObject.GameObject.TankObjects.Player 1 Items
    Text player1Label;
    ArrayList<String> player1_keyList;
    ComboBox player1_settings;
    Text player1l;
    Text player2l;
    // GameObject.GameObject.TankObjects.Player 2 Items
    Text player2Label;
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
    private InputController controller;

    public Settings(){
        menu = new UserInterface.MenuPackage.Menu();
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
        settingsWindow.setResizable(false);
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
        String  style = getClass().getResource("../../style.css").toExternalForm();
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
        mute.setSelected( false);
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
        player1_settings.setEditable(false);
        player1_settings.getItems().addAll(
                "4, 8, 6, 5, ENTER",
                "1, 5, 3, 2, SHIFT"
        );

        player1_settings.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                changeKeyList( newValue, 0);
            }
        });

        //add style

        // player 2 combobox
        player2_settings = new ComboBox();
        player2_settings.setPromptText("Player 2");
        player2_settings.setEditable(false);
        player2_settings.getItems().addAll(
                "S, E, F, D, SPACE",
                "D, R, G, F, CNTRL"
        );
        player2_settings.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                changeKeyList( newValue, 1);
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
        player1_keyList.add( "A");
        player1_keyList.add( "W");
        player1_keyList.add( "D");
        player1_keyList.add( "S");
        player1_keyList.add( "SPACE");
        player1Label = new Text( "Default Player 1 keys\n");
        player1l = new Text(player1_keyList.toString());
        player1Label.setId("texts");
        player1l.setId("texts");

        // player 2 items
        player2_keyList.add( "LEFT");
        player2_keyList.add( "UP");
        player2_keyList.add( "RIGHT");
        player2_keyList.add( "DOWN");
        player2_keyList.add( "ENTER");
        player2Label = new Text("Default Player 2 keys\n");
        player2Label.setId("texts");
        player2l = new Text(player2_keyList.toString());
        player2l.setId("texts");
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
        UserInterface.MenuPackage.Menu.player.setVolume(volume);
    }
    private int getVolume() {
        return volume;
    }
    private void changeKeyList( String newKeyList, int type){

        /*if( type == 0) {
            if( newKeyList.charAt(0) == '4') {
                controller.setLeft(KeyCode.NUMPAD4);
                controller.setUp(KeyCode.NUMPAD8);
                controller.setRight(KeyCode.NUMPAD6);
                controller.setDown(KeyCode.NUMPAD5);
                controller.setFire(KeyCode.ENTER);
            }
            else if( newKeyList.charAt(0) == '1') {
                controller.setLeft(KeyCode.NUMPAD1);
                controller.setUp(KeyCode.NUMPAD5);
                controller.setRight(KeyCode.NUMPAD3);
                controller.setDown(KeyCode.NUMPAD2);
                controller.setFire(KeyCode.SHIFT);
            }
        }
        else if( type == 1) {
            if( newKeyList.charAt(0) == 'S') {
                controller.setLeft(KeyCode.S);
                controller.setUp(KeyCode.E);
                controller.setRight(KeyCode.F);
                controller.setDown(KeyCode.D);
                controller.setFire(KeyCode.SPACE);
            }
            else if( newKeyList.charAt(0) == 'D') {
                controller.setLeft(KeyCode.D);
                controller.setUp(KeyCode.R);
                controller.setRight(KeyCode.G);
                controller.setDown(KeyCode.F);
                controller.setFire(KeyCode.CONTROL);
            }
        }*/

        int player_id;
        if( newKeyList.charAt(0) == '4' || newKeyList.charAt(0) == '1')
        {
            player_id = 1;
            player1l.setText(newKeyList.toString());
           /* player1_keyList.set(0, Character.toString(newKeyList.charAt(0)));
            player1_keyList.set(1, Character.toString(newKeyList.charAt(0)));
            player1_keyList.set(2, Character.toString(newKeyList.charAt(3)));
            player1_keyList.set(3, Character.toString(newKeyList.charAt(6)));
            player1_keyList.set(4, Character.toString(newKeyList.charAt(9)));
            player1_keyList.set(5, newKeyList.substring( 12, 16));*/
        }
        else
        {
            player_id = 2;
            player2l.setText(newKeyList.toString());
            player2_keyList.set(0, Character.toString(newKeyList.charAt(0)));
            player2_keyList.set(1, Character.toString(newKeyList.charAt(0)));
            player2_keyList.set(2, Character.toString(newKeyList.charAt(3)));
            player2_keyList.set(3, Character.toString(newKeyList.charAt(6)));
            player2_keyList.set(4, Character.toString(newKeyList.charAt(9)));
            player2_keyList.set(5, newKeyList.substring( 12, 16));
        }
    }

    private void updateUI( int player_id) {

        if( player_id == 1)
            player1Label = new Text( "Player 1 keys\n" + player1_keyList.toString());
        else
            player2Label = new Text( "Player 1 keys\n" + player2_keyList.toString());
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
        player1Label = new Text( "Player 1 keys\n" + player1_keyList.toString());
        player2Label = new Text( "Player 1 keys\n" + player2_keyList.toString());
    }
}