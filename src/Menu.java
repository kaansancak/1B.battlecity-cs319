import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class Menu extends Application implements EventHandler<ActionEvent>{
    private static final int MENU_BUTTON_COUNT = 6;
    private static final int MENU_WINDOW_WIDTH = 600;
    private static final int MENU_WINDOWS_HEIGHT = 600;
    private Stage menuWindow;
    private Scene menuScene;
    private VBox mBBox;
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
        menuWindow = primaryStage;
        menuWindow.setTitle( "Battle City");
        menuWindow.setOnCloseRequest(e-> {
            e.consume();
            exitBattleCity();
        });

        //Inıtialize menu Buttons
        menuButtons = new Button[MENU_BUTTON_COUNT];

        //Give name and set listener to the menu buttons
        initMenuButtons( menuButtons);
        StackPane menuLayout = new StackPane();

        //Add Boxes to the VBoX
        mBBox = new VBox();
        mBBox.setSpacing(10);
        mBBox.setPadding( new Insets(0, 20, 10, 20));
        mBBox.setAlignment( Pos.CENTER);
        mBBox.setFillWidth(true);

        for ( Button menuButton : menuButtons){
            mBBox.getChildren().add( menuButton);
        }

        //Add VBox to the Menu Layout
        menuLayout.getChildren().add( mBBox);

        //Add Menu layout the the Scene
        menuScene = new Scene( menuLayout, MENU_WINDOW_WIDTH, MENU_WINDOWS_HEIGHT);
        menuWindow.setScene(menuScene);
        menuWindow.show();

    }

    @Override
    public void handle(ActionEvent event) {
        if( event.getSource() == menuButtons[0]){

        }else if( event.getSource() == menuButtons[1]){

        }else if( event.getSource() == menuButtons[2]){

        }else if( event.getSource() == menuButtons[3]){

        }else if( event.getSource() == menuButtons[4]){

        }else if( event.getSource() == menuButtons[5]){
            exitBattleCity();
        }
    }

    private void exitBattleCity() {
        boolean answer = ConfirmBox.display( "Close Request", "Are you sure that you want to exit Battle City?");
        if(answer)
        menuWindow.close();
    }

    private void initMenuButtons(Button[] menuButtons) {
        for ( int i = 0 ; i < MENU_BUTTON_COUNT ; i++) {
            menuButtons[i] = new Button();
            menuButtons[i].setOnAction(this);
        }

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
 