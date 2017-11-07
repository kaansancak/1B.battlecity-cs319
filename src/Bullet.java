import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;
import java.nio.file.Paths;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bullet extends GameObject {

    //Variables
    //direction of the bullet
    private int dir;
    private int id;
    private boolean isCrushed;

    //Constructor
    public Bullet( int id, int xLoc, int yLoc, int dir) {
        super.setxLoc(xLoc);
        super.setyLoc(yLoc);
        this.id = id;
        this.dir = dir;
        isCrushed = false;
        setDirImage();
        super.setView( new ImageView(super.getImage()));
        super.setVelocity( new Point2D.Double(4,4));
    }

    private void setDirImage() {
        switch (dir){
            case 0: setImage(new Image(Paths.get("."+"/MediaFiles/bullet_right.png").toUri().toString()));
            case 1: setImage(new Image(Paths.get("."+"/MediaFiles/bullet_left.png").toUri().toString()));
            case 2: setImage(new Image(Paths.get("."+"/MediaFiles/bullet_up.png").toUri().toString()));
            case 3: setImage(new Image(Paths.get("."+"/MediaFiles/bullet_down.png").toUri().toString()));
        }
    }

    //methods
    /*Destruct method destroys the current bullet
    if any collision occurs
    returns whether destruction is successful.
     */
    public boolean destruct(int xLoc, int yLoc) {
        //This method will be filled
        return true;
    }

    //This method moves the bullet through the map
    //in given direction
    public void move() {
        if (!isCrushed()) {
            if (dir == 0) {
                super.setxLoc(super.getxLoc() + 3);
            } else if(dir == 1) {
                super.setxLoc(super.getxLoc() - 3);
            }else if(dir == 2) {
                super.setyLoc(super.getyLoc() + 3);
            }else if(dir == 3) {
                super.setyLoc(super.getyLoc() - 3);
            }


        }
    }

    //Setters and Getters
    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCrushed() {
        return isCrushed;
    }
    public void setCrushed(boolean crushed) {
        isCrushed = crushed;
    }


    public boolean isMovableTile(){
        return true;
    }
}
