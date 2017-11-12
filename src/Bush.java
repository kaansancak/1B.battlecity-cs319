import javafx.scene.image.Image;

import java.nio.file.Paths;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bush extends Undestructible {

    private final String IMG_DIR = "./MediaFiles/image2.png";

    //Constructor
    public Bush( int xLoc, int yLoc){
        super( xLoc, yLoc);
        super.setImage( new Image(Paths.get(IMG_DIR).toUri().toString()));
        super.initView();
    }

    public boolean isMovableTile(){
        return true;
    }
}