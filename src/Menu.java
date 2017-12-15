import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by kaan on 10/28/2017.
 */
public class Menu extends Application implements EventHandler<ActionEvent>{
    private static final int MENU_BUTTON_COUNT = 6;
    private static final int MENU_WINDOW_WIDTH = 600;
    private static final int MENU_WINDOWS_HEIGHT = 600;
    public static MediaPlayer player;
    Label battleCity;
    private Stage menuWindow;
    private Scene menuScene;
    private VBox mBBox;
    private ViewFrame creditsFrame;
    private ViewFrame howToPlayFrame;
    private Settings settings;
    private int playerCount;
    private int menuTypeId;
    private JFXPanel menuLayout;
    private Button[] menuButtons;
    private FileManager f;

    public static void main( String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        settings = new Settings();

        //Set the title of the stage
        f = new FileManager();
        menuWindow = primaryStage;
        menuWindow.setResizable(false);
        menuWindow.setTitle( "Battle City");
        menuWindow.setOnCloseRequest(e-> {
            e.consume();
            exitBattleCity();
        });

        player = new MediaPlayer( f.getOpeningSong());
        //player.play();
        player.setOnEndOfMedia( new Runnable() {
            public void run() {
                player.seek(Duration.ZERO);
            }
        });
        //player.play();

        battleCity = new Label("Battle City");
        battleCity.setId("welcome-text");
        //viewframePlayer;

        //Inıtialize menu Buttons
        menuButtons = new Button[MENU_BUTTON_COUNT];


        //Give name and set listener to the menu buttons
        initMenuButtons( menuButtons);

        StackPane menuLayout = new StackPane();

        Image im = new Image(Paths.get("."+"/MediaFiles/backgroundImage.png").toUri().toString(), true);
        menuLayout.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        //Add Boxes to the VBoX
        mBBox = new VBox();
        mBBox.setSpacing(10);
        mBBox.setPadding( new Insets(0, 20, 10, 20));
        mBBox.setAlignment( Pos.CENTER);
        mBBox.setFillWidth(true);

        mBBox.getChildren().add(battleCity);

        for ( Button menuButton : menuButtons){
            mBBox.getChildren().add( menuButton);
        }


        //Add VBox to the Menu Layout
        menuLayout.getChildren().add( mBBox);

        menuScene = new Scene( menuLayout, MENU_WINDOW_WIDTH, MENU_WINDOWS_HEIGHT);

        String  style = getClass().getResource("style.css").toExternalForm();
        menuScene.getStylesheets().add(style);
        menuWindow.setScene(menuScene);
        menuWindow.show();
    }


    @Override
    public void handle(ActionEvent event) {
        if( event.getSource() == menuButtons[0]){
            setPlayerCount(1);
            startGame();
        }else if( event.getSource() == menuButtons[1]){
            setPlayerCount(2);
            startGame();
        }else if( event.getSource() == menuButtons[2]){
            startSettings();
        }else if( event.getSource() == menuButtons[3]){
            showHowToPlay();
        }else if( event.getSource() == menuButtons[4]){
            showCredits();
        }else if( event.getSource() == menuButtons[5]){
            exitBattleCity();
        }
    }

    public void showMenu() {
        menuWindow.showAndWait();
    }

    private void startSettings() {
        menuWindow.close();
        settings.showSettings();
        if ( settings.isReturnCall())
            menuWindow.show();
    }

    private void showHowToPlay() {
        menuWindow.close();
        ArrayList<String> message = new ArrayList<>();
        try {
            message = f.getHowToPlayDoc();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        howToPlayFrame = new ViewFrame( "HOW TO PLAY", message);
        if ( howToPlayFrame.isReturnCall()){
            menuWindow.show();
        }
    }
    private void showCredits() {
        menuWindow.close();
        ArrayList<String> s = new ArrayList<>();
        try {
            s = f.getCreditsDoc();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        creditsFrame = new ViewFrame( "CREDITS", s);
        if(creditsFrame.isReturnCall()){
            menuWindow.show();
        }
    }
    public void changeTheSong(String song) {
        Media newSong = new Media(Paths.get("MediaFiles/" + song + ".mp3").toUri().toString());
        player.stop();
        player = new MediaPlayer(newSong);
        player.setOnEndOfMedia( new Runnable() {
            public void run() {
                player.seek(Duration.ZERO);
            }
        });
        player.play();
    }
    private void startGame() {
        menuWindow.close();
        GameManager gameManager = new GameManager(playerCount);
    }

    private void exitBattleCity() {
        ConfirmBox confirmBox = new ConfirmBox();
        boolean answer = confirmBox.display( "Close Request", "Are you sure that you want to exit Battle City?");
        if(answer)
            menuWindow.close();
    }

    public void stopSong(){
        player.setMute(true);
    }
    public void playSong(){
        player.setMute(false);
    }

    private void initMenuButtons(Button[] menuButtons) {
        for ( int i = 0 ; i < MENU_BUTTON_COUNT ; i++) {
            menuButtons[i] = new Button();
            menuButtons[i].setOnAction(this);
            menuButtons[i].setId("glass-grey");
            menuButtons[i].setPrefSize(150, 20);
        }

        menuButtons[0].setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[0].setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        menuButtons[0].setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[0].setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });

        menuButtons[1].setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[1].setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        menuButtons[1].setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[1].setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });

        menuButtons[2].setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[2].setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        menuButtons[2].setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[2].setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });

        menuButtons[3].setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[3].setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        menuButtons[3].setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[3].setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });

        menuButtons[4].setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[4].setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        menuButtons[4].setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[4].setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });

        menuButtons[5].setOnMouseEntered(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[5].setStyle("-fx-background-color:#c3c4c4;");
            }
        });
        menuButtons[5].setOnMouseExited(new EventHandler<MouseEvent>
                () {

            @Override
            public void handle(MouseEvent t) {
                menuButtons[5].setStyle("-fx-background-color:\n" +
                        "        #dae7f3,\n" +
                        "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                        "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);");
            }
        });
        //Set the titles of Menu Buttons
        menuButtons[0].setText("Single Player");
        menuButtons[1].setText("Multiplayer");
        menuButtons[2].setText("Settings");
        menuButtons[3].setText("How to Play");
        menuButtons[4].setText("Credits");
        menuButtons[5].setText("Exit");
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
 