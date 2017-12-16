package GameObject.TankObjects;

import javafx.scene.image.Image;

import java.awt.geom.Point2D;
import java.nio.file.Paths;

public class MediumBot extends Bot {
    private final double MEDIUM_BOT_SPEED = 2.5;
    private final int MEDIUM_BOT_DIMENSION = 30;
    private final int MEDIUM_BOT_HEALTH = 400;
    public MediumBot(double xLoc, double yLoc) {
        super(xLoc, yLoc);
        view.setFitHeight(MEDIUM_BOT_DIMENSION);
        view.setFitHeight(MEDIUM_BOT_DIMENSION);
        velocity = new Point2D.Double(MEDIUM_BOT_SPEED,MEDIUM_BOT_SPEED);
        health = MEDIUM_BOT_HEALTH;
        initImages();
    }

    @Override
    protected void initImages() {
        rightImage = ( new Image(Paths.get("./MediaFiles/t1r.png").toUri().toString()));
        leftImage =( new Image(Paths.get("./MediaFiles/t1l.png").toUri().toString()));
        upImage = ( new Image(Paths.get("./MediaFiles/t1u.png").toUri().toString()));
        downImage =( new Image(Paths.get("./MediaFiles/t1d.png").toUri().toString()));
    }
}
