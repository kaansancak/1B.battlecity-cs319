import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

/**
 * Created by kaan on 11/12/2017.
 */
public class Tile extends GameObject {

    private final String IMG_DIR =  "./MediaFiles/small_void.png";
    public Tile( double xLoc, double yLoc ) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        view = new ImageView(new Image(Paths.get(IMG_DIR).toUri().toString()));
        view.setFitHeight(32);
        view.setFitWidth(32);
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
        return false;
    }

    @Override
    public void draw() {
        super.getView().setTranslateX( xLoc);
        super.getView().setTranslateY( yLoc);
    }
}
