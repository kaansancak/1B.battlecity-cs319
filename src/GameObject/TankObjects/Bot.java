package GameObject.TankObjects;

import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bot extends Tank {
    private final int BOT_ID = 99;
    private Random rand = new Random();

    public Bot( double xLoc, double yLoc){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        initImages();
        this.dir = 1;
        id = BOT_ID;
        view = new ImageView( rightImage);
        view.setFitWidth( 28);
        view.setFitHeight( 28);
        super.setVelocity( new Point2D.Double(2, 2));
        health = 100;
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
