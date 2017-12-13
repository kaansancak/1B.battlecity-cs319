import javafx.scene.image.Image;

import java.nio.file.Paths;

public class SpeedBonus extends Bonus {

    private final String IMG_DIR = "./MediaFiles/image11.png";

    public SpeedBonus(int xLoc, int yLoc) {
        super(xLoc, yLoc);
        super.setImage( new Image(Paths.get(IMG_DIR).toUri().toString()));
        super.initView();
    }

    private void normalizeSpeed(Player player) {
        player.setSpeed(player.getOldSpeed());
    }
}
