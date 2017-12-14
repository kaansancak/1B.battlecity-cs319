import javafx.scene.image.Image;

import java.nio.file.Paths;

public class LifeBonus extends Bonus {

    private final String IMG_DIR = "./MediaFiles/image10.png";

    public LifeBonus(int xLoc, int yLoc) {
        super(xLoc, yLoc);
        super.setImage( new Image(Paths.get(IMG_DIR).toUri().toString()));
        super.initView();
    }
}
