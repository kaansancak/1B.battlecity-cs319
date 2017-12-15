package GameObject.TankObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bot extends Tank {
    Random rand = new Random();

    public Bot( double xLoc, double yLoc){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        initImages();
        this.dir = 1;
        id = 99;
        view = new ImageView( rightImage);
        view.setFitWidth( 28);
        view.setFitHeight( 28);
        super.setVelocity( new Point2D.Double(2, 2));
        health = 200;

    }

    public void setRandomDir(){
        int new_dir = dir;
        do{
            new_dir = rand.nextInt(4);
        }while( new_dir == dir);
        dir = new_dir;
    }
    public boolean isStuck(){
        return false;
    }
    /*
    This method must be modified according to description
    in the design report.
     */
    public void runBot( boolean changeDirStatus){
        if( changeDirStatus) {
            super.move( dir );
        }
        else if( !changeDirStatus) {
            int random_dir = dir;
            do{
                random_dir = rand.nextInt(4);
            }while( random_dir != dir);
            dir = random_dir;
        }
    }
    private void getRandomDir(){
        dir = rand.nextInt( 4);
    }

    @Override
    protected void initImages() {
        super.setRightImage( new Image(Paths.get("./MediaFiles/enemy_right.png").toUri().toString()));
        super.setLeftImage( new Image(Paths.get("./MediaFiles/enemy_left.png").toUri().toString()));
        super.setUpImage( new Image(Paths.get("./MediaFiles/enemy_up.png").toUri().toString()));
        super.setDownImage( new Image(Paths.get("./MediaFiles/enemy_down.png").toUri().toString()));
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
