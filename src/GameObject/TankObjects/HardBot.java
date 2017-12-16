package GameObject.TankObjects;

import javafx.scene.image.Image;

import java.nio.file.Paths;

public class HardBot extends Bot{
    public HardBot(double xLoc, double yLoc) {
        super(xLoc, yLoc);
        setHealth(500);
        super.initImages();
    }

    @Override
    protected void initImages() {
        super.setRightImage( new Image(Paths.get("./MediaFiles/t2r.png").toUri().toString()));
        super.setLeftImage( new Image(Paths.get("./MediaFiles/t2l.png").toUri().toString()));
        super.setUpImage( new Image(Paths.get("./MediaFiles/t2u.png").toUri().toString()));
        super.setDownImage( new Image(Paths.get("./MediaFiles/t2d.png").toUri().toString()));
    }
}
