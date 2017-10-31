import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * Created by kaan on 10/30/2017.
 */
public class Map{
    private Stage gameWindow;

    public Map() {
        gameWindow = new Stage();
        TilePane gameScreen = new TilePane();
        gameScreen.setHgap(8);
        gameScreen.setPrefColumns(100);
        gameScreen.setPrefRows(100);
        Scene gameScene = new Scene(gameScreen, 200, 200);
        gameWindow.setScene(gameScene);
        gameWindow.showAndWait();
    }
}
