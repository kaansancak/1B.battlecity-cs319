package GameObject.TankObjects;

import GameObject.GameObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;
import java.nio.file.Paths;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bullet extends GameObject {

    //ImageDirs
    private final String CRUSHED_IMAGE_0 = "./MediaFiles/bullet_d_0.png";
    private final String CRUSHED_IMAGE_1 = "./MediaFiles/bullet_d_1.png";
    private final String CRUSHED_IMAGE_2 = "./MediaFiles/bullet_d_3.png";
    private final int BULLET_VIEW_DIMENSION = 10;
    private final double BULLET_VELOCITY = 4;
    //Variables
    //direction of the bullet
    private Image crushedImages[] = new Image[3];
    private boolean isCrushed = false;
    private int dir;
    private int id;


    //Constructor
    public Bullet( int id, double xLoc, double yLoc, int dir) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.id = id;
        this.dir = dir;
        initImages();
        setDirImage();
        view = new ImageView( image);
        view.setFitHeight(BULLET_VIEW_DIMENSION);
        view.setFitWidth(BULLET_VIEW_DIMENSION);
        super.draw();
        super.setVelocity( new Point2D.Double(BULLET_VELOCITY,BULLET_VELOCITY));
    }

    private void initImages() {
        crushedImages[0] = new Image(Paths.get(CRUSHED_IMAGE_0).toUri().toString());
        crushedImages[1] = new Image(Paths.get(CRUSHED_IMAGE_1).toUri().toString());
        crushedImages[2] = new Image(Paths.get(CRUSHED_IMAGE_2).toUri().toString());
    }

    public void iterateCrushedAnimation(){
        for( int i = 0 ; i < crushedImages.length ; i++){
            if( i == 0){
                setViewBounds( crushedImages[0], 11,11);
            }else if( i == 1){
                setViewBounds( crushedImages[1], 14,14);
            }else
                setViewBounds( crushedImages[2],16,16);
        }
    }

    private void setDirImage() {
        switch (dir){
            case 0: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_right.png").toUri().toString()));
                    break;
            case 1: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_left.png").toUri().toString()));
                    break;
            case 2: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_down.png").toUri().toString()));
                    break;
            case 3: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_up.png").toUri().toString()));
                    break;
            default: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_right.png").toUri().toString()));
                    break;
        }
    }


    //This method moves the bullet through the map
    //in given direction
    public void move() {
        if (!isCrushed()) {
            if (dir == 0) {
                xLoc = ( xLoc + super.getVelocity().getX());
            } else if(dir == 1) {
                xLoc = (xLoc - super.getVelocity().getX());
            }else if(dir == 2) {
                yLoc = (yLoc + super.getVelocity().getY());
            }else if(dir == 3) {
                yLoc = (yLoc - super.getVelocity().getY());
            }
            super.draw();
        }
    }


    //Setters and Getters
    public int getDir() {
        return dir;
    }

    public int getId() {
        return id;
    }

    public boolean isCrushed() {
        return isCrushed;
    }
    public void setCrushed(boolean crushed) {
        isCrushed = crushed;
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
