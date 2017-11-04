
/*
 @Description: this class is going to be the screen class for Options from the Menu
 the idea of the class is to have
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Settings implements EventHandler<ActionEvent> {

    /*
    List of items i need:
    1. submit button
    2. 1 song combobox (2 songs)
    3. back button to menu
    4. slider for the volume
    5. mute checkbox
    6. unmute checkbox
    7. 10 boxes for the current settings
    8. two comboboxes with 2 options in it for each
    9. add height and width later.
     */
    /*
      I need 2 VBoxes for sound management (top) and input management (bottom)
      add labels into the input management and sound management vboxes (later)
     */

    private static final int SETTINGS_WINDOW_WIDTH = 600;
    private static final int SETTINGS_WINDOWS_HEIGHT = 600;

    private Stage settingsWindow;
    private Scene settingsScene;
    private boolean returnCall  = false;

    //private JFXPanel settingsLayout;
    Menu menu;
    FileManager file;
    private VBox vBox;
    // Player 1 default controls
    Label player1;
    Label player1_up;
    Label player1_down;
    Label player1_left;
    Label player1_right;
    Label player1_fire;
    Label player1Label;

    TextField[] player1_textfields;
    HBox[] player1_boxes;
    ArrayList<String> player1_keyList;

    ComboBox player1_settings;

    // Player 2 default controls
    Label player2;
    Label player2_up;
    Label player2_down;
    Label player2_left;
    Label player2_right;
    Label player2_fire;
    Label player2Label;

    TextField[] player2_textfields;
    HBox[] player2_boxes;
    ArrayList<String> player2_keyList;

    ComboBox player2_settings;

    // Buttons
    Button submit;
    Button backToMenu;


    // sound manager part
    private int volume;
   // private int latest; // volume before unmute

    private CheckBox mute;

    private Slider volumeBar;

    private ComboBox<javafx.scene.media.Media> musicBox;
    private ArrayList<javafx.scene.media.Media> media;
    MediaPlayer player;
    private int latestVolume;


    public Settings(){
        file = new FileManager();
        media = file.getScannedAudios();

        player1_keyList = new ArrayList<String>(0);
        player1_keyList.add( "LEFT");
        player1_keyList.add( "UP");
        player1_keyList.add( "RIGHT");
        player1_keyList.add( "DOWN");
        player1_keyList.add( "SPACEBAR");
        player1Label = new Label( "Player 1 keys\n" + player1_keyList.toString());

        player2_keyList = new ArrayList<String>(0);
        player2_keyList.add( "A");
        player2_keyList.add( "W");
        player2_keyList.add( "D");
        player2_keyList.add( "S");
        player2_keyList.add( "Z");
        player2Label = new Label("Player 2 keys\n" + player2_keyList.toString());


        settingsWindow = new Stage();
        settingsWindow.setTitle( "Options");

        StackPane settingsLayout = new StackPane();
        Image im = new Image("/Images/backgroundImage.png", true);
        settingsLayout.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        initLabels(); // initializing the labels
        initCheckBoxes();
        initSlider();

        // comboboxes for each player
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

        // buttons
        submit = new Button("Submit");
        backToMenu = new Button("Back");

        submit.setOnAction(this);
        backToMenu.setOnAction( event -> {
            settingsWindow.close();
            returnCall = true;
        });

        // initialize the default volume
        volume = 100;
        latestVolume = 100;


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

        // slider
        volumeBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                int new_value = new_val.intValue();
                changeVolume( new_value);
            }
        });

        //musicbox combobox
        musicBox = new ComboBox<javafx.scene.media.Media>();
        musicBox.getItems().addAll(
             media.get(0),
             media.get(1)
        );

        // add listener
        musicBox.valueProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                selectBackGroundMusic( newValue);
            }
        });
        addSettingsComponents();
        settingsLayout.getChildren().add(vBox);
        settingsScene = new Scene( settingsLayout, SETTINGS_WINDOW_WIDTH, SETTINGS_WINDOWS_HEIGHT);
        settingsWindow.setScene( settingsScene);
    }

    public void addSettingsComponents(){
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(3.0);
        vBox.setFillWidth(true);
        vBox.getChildren().addAll( volumeBar, mute, musicBox, player1Label, player1_settings, player2Label, player2_settings, submit, backToMenu);
    }

    public void showSettings(){
        settingsWindow.showAndWait();
    }

    public void closeSettings(){
        settingsWindow.close();
    }

    private void selectBackGroundMusic( Media song) {
        player = new MediaPlayer( song);
        player.play();
    }

    private void initCheckBoxes() {
        mute = new CheckBox( "Mute");

        mute.setSelected( false);

    }

    private void initSlider() {
        volumeBar = new Slider();
        volumeBar.setMin(0);
        volumeBar.setMax(100);
        volumeBar.setValue(100);

        volumeBar.setShowTickLabels( true);
        volumeBar.setShowTickMarks( true);
        volumeBar.setMajorTickUnit( 50);
        volumeBar.setBlockIncrement( 10);
    }

    private void mute() {
        latestVolume = volume;
        volume = 0;
        changeVolume( volume); // will change the slider too
    }

    private void unmute() {
        volume = latestVolume;
        changeVolume(volume);
    }

    private void changeVolume( int volume) {
        this.volume = volume;
        volumeBar.setValue( volume);
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

    private void initLabels() {
        // player 1
        player1_textfields = new TextField[5];
        player1_boxes = new HBox[5];

        for (int i = 0; i < 5; i++) {
            player1_textfields[i] = new TextField();
            player1_boxes[i] = new HBox();
        }

        // player 2
        player2_textfields = new TextField[5];
        player2_boxes = new HBox[5];

        for (int i = 0; i < 5; i++) {
            player2_textfields[i] = new TextField();
            player2_boxes[i] = new HBox();
        }

        for (int i = 0; i < 5; i++) {
            player1_boxes[i].setSpacing(10);
            player2_boxes[i].setSpacing(10);
        }

    }

}
