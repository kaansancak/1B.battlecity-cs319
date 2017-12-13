import javafx.scene.image.Image;

import java.nio.file.Paths;

/**
 * Created by kaan on 10/28/2017.
 */
public class IronWall extends Undestructible{

    private final String IMG_DIR = "./MediaFiles/image3.png";

    //Constructor
    public IronWall( int xLoc, int yLoc){
        super(xLoc, yLoc);
        super.setImage( new Image(Paths.get(IMG_DIR).toUri().toString()));
        super.initView();
    }

    @Override
    public boolean isPassableByBullets() {
        return false;
    }

    public boolean isMovableTile(){
        return false;
    }
}
//