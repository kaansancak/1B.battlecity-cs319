package GameObject.MapPackage.ObstaclesObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

public class Statue extends Destructible {

    private final String IMG_DIR = "./MediaFiles/image14.png";
    //Constructor
    public Statue( double xLoc, double yLoc){
        health = 1000;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        view = new ImageView(new Image(Paths.get(IMG_DIR).toUri().toString()));
        view.setFitWidth(32);
        view.setFitHeight(32);
    }

    public boolean isDestructed(){
        return health <= 0;
    }

    public void getDamaged(){
        health = health - 200;
    }

    @Override
    public boolean isPassableByTanks() {
        return false;
    }

    @Override
    public boolean isPassableByBullets() {
        return false;
    }

    @Override
    public boolean isHideable() {
        return false;
    }
}
