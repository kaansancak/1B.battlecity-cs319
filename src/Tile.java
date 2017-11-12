import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

/**
 * Created by kaan on 11/12/2017.
 */
public class Tile extends GameObject {

    public Tile( int xLoc, int yLoc ) {
        super( xLoc, yLoc);
        super.setImage(new Image(Paths.get("."+"/MediaFiles/void.png").toUri().toString()));
        super.initView();
    }

    @Override
    public void draw() {
        super.getView().setFitHeight(32);
        super.getView().setFitWidth(32);
        super.getView().setTranslateX( super.getxLoc()*32);
        super.getView().setTranslateY( super.getyLoc()*32);
    }
}
