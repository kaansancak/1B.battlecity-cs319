package GameObject.MapPackage.BonusPackage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

public class ArmorBonus extends Bonus{

    private final String IMG_DIR = "./MediaFiles/armor.png";

    public ArmorBonus(double xLoc, double yLoc) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        view = new ImageView( new Image(Paths.get(IMG_DIR).toUri().toString()));
        view.setFitHeight( BONUS_VIEW_DIMENSION);
        view.setFitWidth( BONUS_VIEW_DIMENSION);
    }
}
