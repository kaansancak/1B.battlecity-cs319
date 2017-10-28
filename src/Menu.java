import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by kaan on 10/28/2017.
 */
public class Menu extends Application {
    private static final int MENU_BUTTON_COUNT = 6;
    private static final int MENU_WINDOW_WIDTH = 600;
    private static final int MENU_WINDOWS_HEIGHT = 600;
    private int playerCount;
    private int menuTypeId;
    private JFXPanel menuLayout;
    Button[] menuButtons;

    public static void main( String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set the title of the stage
        primaryStage.setTitle( "Battle City");

        //Inıtialize menu Buttons
        menuButtons = new Button[MENU_BUTTON_COUNT];


        initMenuButtons( menuButtons);
        StackPane menuLayout = new StackPane();
        VBox mBBox = new VBox();
        mBBox.setSpacing(10);
        mBBox.setPadding( new Insets(0, 20, 10, 20));
        mBBox.setAlignment( Pos.CENTER);
        mBBox.setFillWidth(true);

        for ( Button menuButton : menuButtons){
            mBBox.getChildren().add( menuButton);
        }
        menuLayout.getChildren().add( mBBox);
        Scene menuScene = new Scene( menuLayout, MENU_WINDOW_WIDTH, MENU_WINDOWS_HEIGHT);
        primaryStage.setScene(menuScene);
        primaryStage.show();

        /*
        button = new Button();
        button.setText( "Click me");
        StackPane layout = new StackPane();
        layout.getChildren().add( button);

        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }

    private void initMenuButtons(Button[] menuButtons) {
        for ( int i = 0 ; i < MENU_BUTTON_COUNT ; i++)
            menuButtons[i] = new Button();

        //Set the titles of Menu Buttons
        menuButtons[0].setText("Single Player");
        menuButtons[1].setText("Multiplayer");
        menuButtons[2].setText("Settings");
        menuButtons[3].setText("How to Play");
        menuButtons[4].setText("Credits");
        menuButtons[5].setText("Exit");
    }
    /*
    private settingsFrame settingLayout;
    private ViewFrame creditsLayout;
    private ViewFrame howToPlayLayout;
    private GameManager gameManager;
    private FileManager menuFileManager;
     */
}
 