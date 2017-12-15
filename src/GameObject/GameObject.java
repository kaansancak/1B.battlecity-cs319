package GameObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;

/**
 * Created by kaan on 10/28/2017.
 */
public abstract class GameObject {

    //Variables
    private final int BULLET_DAMAGE = 1;
    //Location variables
    protected double xLoc;
    protected double yLoc;
    protected ImageView view;
    //GUI variables
    protected Image image;

    protected boolean isDamaged = false;
    protected boolean isDestructed = false;
    protected Point2D velocity;

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

    public void setViewImage( Image image){
        view.setImage(image);
    }


    //Setters and getters of variables
    public double getyLoc() {
        return yLoc;
    }

    public void setyLoc(double yLoc) {
        this.yLoc = yLoc;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getxLoc() {

        return xLoc;
    }

    public void setxLoc(double xLoc) {
        this.xLoc = xLoc;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public void initView(){
        view = new ImageView( getImage());
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }

    public boolean isDestructed(){
        return isDestructed;
    }

    public void setDestructed( boolean destructed){
        isDestructed = destructed;
    }

    public abstract boolean isPassableByTanks();

    public abstract boolean isPassableByBullets();

    public abstract boolean isHideable();

    public void draw() {
        view.setTranslateX( xLoc);
        view.setTranslateY( yLoc);
    }
}
