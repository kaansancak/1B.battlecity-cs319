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
    private final int BULLET_VIEW_DIMENSION = 10;
    private final double BULLET_VELOCITY = 0.2;
    private final int BULLET_WH = 23;
    private final double SHIFT_V = 0.3333;
    private final double SHIFT_H = 0.6666;
    private boolean isCrushed = false;
    private int dir;
    private int id;


    //Constructor
    public Bullet( int id, double xLoc, double yLoc, int dir) {
        super(xLoc, yLoc);
        this.id = id;
        this.dir = dir;
        setDirImage();
        super.setVIEW_H(BULLET_VIEW_DIMENSION);
        super.setVIEW_V(BULLET_VIEW_DIMENSION);
        super.setVIEW_WH(BULLET_WH);
        super.setVelocity( new Point2D.Double(BULLET_VELOCITY,BULLET_VELOCITY));
    }

    private void setDirImage() {
        switch (dir){
            case 0: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_right.png").toUri().toString()));
                    super.setyLoc( super.getyLoc() + SHIFT_V);
                    super.setxLoc( super.getxLoc() + SHIFT_H);
                    break;
            case 1: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_left.png").toUri().toString()));
                    super.setyLoc( super.getyLoc() + SHIFT_V);
                    break;
            case 2: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_down.png").toUri().toString()));
                    super.setxLoc( super.getxLoc() + SHIFT_V);
                    super.setyLoc( super.getyLoc() + SHIFT_H);
                    break;
            case 3: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_up.png").toUri().toString()));
                    super.setxLoc( super.getxLoc() + SHIFT_V);
                    break;
            default: super.setImage(new Image(Paths.get("."+"/MediaFiles/bullet_right.png").toUri().toString()));
                    break;
        }
        super.setView( new ImageView(super.getImage()));
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
                super.setxLoc(super.getxLoc() + super.getVelocity().getX());
            } else if(dir == 1) {
                super.setxLoc(super.getxLoc() - super.getVelocity().getX());
            }else if(dir == 2) {
                super.setyLoc(super.getyLoc() + super.getVelocity().getY());
            }else if(dir == 3) {
                super.setyLoc(super.getyLoc() - super.getVelocity().getY());
            }
            draw();
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
