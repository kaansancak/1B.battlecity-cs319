import javafx.stage.Stage;

/**
 * Created by kaan on 10/28/2017.
 */
public class IronWall extends Undestructible{

    public void start(Stage primaryStage) {

    }

    //Constructor
    public IronWall( int xLoc, int yLoc){
        super.setxLoc( xLoc);
        super.setyLoc( yLoc);
    }
}
