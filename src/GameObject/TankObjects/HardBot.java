package GameObject.TankObjects;

import javafx.scene.image.Image;

import java.awt.geom.Point2D;
import java.nio.file.Paths;

public class HardBot extends Bot{
    private final double HARD_BOT_SPEED = 2.5;
    private final int HARD_BOT_DIMENSION = 30;
    private final int HARD_BOT_HEALTH = 600;
    public HardBot(double xLoc, double yLoc) {
        super(xLoc, yLoc);
        view.setFitHeight(HARD_BOT_DIMENSION);
        view.setFitWidth(HARD_BOT_DIMENSION);
        health = HARD_BOT_HEALTH;
        velocity = new Point2D.Double( HARD_BOT_SPEED, HARD_BOT_SPEED);
        initImages();
    }

    @Override
    protected void initImages() {
        super.setRightImage( new Image(Paths.get("./MediaFiles/t2r.png").toUri().toString()));
        super.setLeftImage( new Image(Paths.get("./MediaFiles/t2l.png").toUri().toString()));
        super.setUpImage( new Image(Paths.get("./MediaFiles/t2u.png").toUri().toString()));
        super.setDownImage( new Image(Paths.get("./MediaFiles/t2d.png").toUri().toString()));
    }
}
