import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

public class SpeedBonus extends Bonus {

    private final String IMG_DIR = "./MediaFiles/image11.png";

    public SpeedBonus(double xLoc, double yLoc) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        view = new ImageView( new Image(Paths.get(IMG_DIR).toUri().toString()));
        view.setFitHeight( BONUS_VIEW_DIMENSION);
        view.setFitWidth( BONUS_VIEW_DIMENSION);
    }

    private void normalizeSpeed(Tank tank) {
        tank.setSpeed(tank.getOldSpeed());
    }
}
