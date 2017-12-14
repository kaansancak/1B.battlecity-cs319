import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bush extends Undestructible {

    private final String IMG_DIR = "./MediaFiles/image2.png";

    //Constructor
    public Bush( double xLoc, double yLoc){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        view = new ImageView(new Image(Paths.get(IMG_DIR).toUri().toString()));
        view.setFitWidth(32);
        view.setFitHeight(32);
    }

    @Override
    public boolean isPassableByTanks() {
        return true;
    }

    @Override
    public boolean isPassableByBullets() {
        return true;
    }

    @Override
    public boolean isHideable() {
        return true;
    }

    public boolean isMovableTile(){
        return true;
    }
}