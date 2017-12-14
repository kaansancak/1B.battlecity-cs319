import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

/**
 * Created by kaan on 10/28/2017.
 */
public class IronWall extends Undestructible{

    private final String IMG_DIR = "./MediaFiles/image3.png";

    //Constructor
    public IronWall( double xLoc, double yLoc){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        view = new ImageView(new Image(Paths.get(IMG_DIR).toUri().toString()));
        view.setFitWidth( 32);
        view.setFitHeight( 32);
    }

    @Override
    public boolean isPassableByTanks() {
        return false;
    }

    @Override
    public boolean isPassableByBullets() {
        return false;
    }

    @Override
    public boolean isHideable() {
        return false;
    }

    public boolean isMovableTile(){
        return false;
    }
}
//