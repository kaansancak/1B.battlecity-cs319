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
        super.setImage( super.getRightImage() );
        super.setView( new ImageView(super.getImage()));
        super.setVelocity( new Point2D.Double(0.1,0.1));
        health = 200;
        initImages();
        dir = 0;
    }

    public boolean isStuck(){
        return false;
    }
    /*
    This method must be modified according to description
    in the design report.
     */
    public void runBot( boolean changeDirStatus){
        if( changeDirStatus)
            super.move( dir);
        else if( !changeDirStatus) {
            getRandomDir();
        }
    }
    private void getRandomDir(){
        dir = rand.nextInt( 4);
    }

    public boolean isMovableTile(){
        return false;
    }

    @Override
    protected void initImages() {
        super.setRightImage( new Image(Paths.get("."+"/MediaFiles/enemy_right.png").toUri().toString()));
        super.setLeftImage( new Image(Paths.get("."+"/MediaFiles/resources/enemy_left.png").toUri().toString()));
        super.setUpImage( new Image(Paths.get("."+"/MediaFiles/resources/enemy_up.png").toUri().toString()));
        super.setDownImage( new Image(Paths.get("."+"/MediaFiles/resources/enemy_down.png").toUri().toString()));
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
