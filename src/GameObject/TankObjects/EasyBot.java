package GameObject.TankObjects;

import javafx.scene.image.Image;

import java.nio.file.Paths;

public class EasyBot extends Bot {
    public EasyBot(double xLoc, double yLoc) {
        super(xLoc, yLoc);
        initImages();
    }

    @Override
    protected void initImages() {
        super.setRightImage( new Image(Paths.get("./MediaFiles/enemy_right.png").toUri().toString()));
        super.setLeftImage( new Image(Paths.get("./MediaFiles/enemy_left.png").toUri().toString()));
        super.setUpImage( new Image(Paths.get("./MediaFiles/enemy_up.png").toUri().toString()));
        super.setDownImage( new Image(Paths.get("./MediaFiles/enemy_down.png").toUri().toString()));
    }
}
