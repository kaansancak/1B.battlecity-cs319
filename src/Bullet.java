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
    public Bullet( int id, double xLoc, double yLoc, int dir) {
        super(xLoc, yLoc);
        System.out.print( super.getxLoc() + " " + super.getyLoc() );
        this.id = id;
        this.dir = dir;
        isCrushed = false;
        setDirImage();
        initView();
        super.setVelocity( new Point2D.Double(0.25,0.25));
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

    @Override
    public void draw() {
        setDirImage();
        super.getView().setImage(super.getImage());
        super.getView().setTranslateX( super.getxLoc() * 27);
        super.getView().setTranslateY( super.getyLoc() * 27);
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
