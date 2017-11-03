import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bush extends Undestructible {

    //Constructor
    public Bush( int xLoc, int yLoc){
        super.setxLoc( xLoc);
        super.setyLoc( yLoc);
    }
}