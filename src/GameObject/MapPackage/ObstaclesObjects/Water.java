package GameObject.MapPackage.ObstaclesObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

/**
 * Created by kaan on 10/28/2017.
 */
public class Water extends Undestructible {

    private final int DIMENSION = 32;
    private final String IMG_DIR = "./MediaFiles/image0.png";
    //Constructor
    public Water( double xLoc, double yLoc){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        view = new ImageView(new Image(Paths.get(IMG_DIR).toUri().toString()));
        view.setFitWidth( DIMENSION);
        view.setFitHeight( DIMENSION);
}
    @Override
    public boolean isPassableByBullets() {
        return true;
    }

    @Override
    public boolean isHideable() {
        return false;
    }

    @Override
    public boolean isPassableByTanks() {
        return false;
    }
}
//