package GameObject.TankObjects;

import javafx.scene.image.Image;

import java.nio.file.Paths;

public class MediumBot extends Bot {
    public MediumBot(double xLoc, double yLoc) {
        super(xLoc, yLoc);
        setHealth(300);
        super.initImages();
    }

    @Override
    protected void initImages() {
        super.setRightImage( new Image(Paths.get("./MediaFiles/t1r.png").toUri().toString()));
        super.setLeftImage( new Image(Paths.get("./MediaFiles/t1l.png").toUri().toString()));
        super.setUpImage( new Image(Paths.get("./MediaFiles/t1u.png").toUri().toString()));
        super.setDownImage( new Image(Paths.get("./MediaFiles/t1d.png").toUri().toString()));
    }
}
