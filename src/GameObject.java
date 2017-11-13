import com.sun.javafx.geom.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.text.View;
import java.awt.geom.Point2D;

/**
 * Created by kaan on 10/28/2017.
 */
public class GameObject {

    private final int VIEW_WH = 30;

    public GameObject( double xLoc, double yLoc){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }
    //Variables
    private final int BULLET_DAMAGE = 200;
    //Location variables
    private double xLoc;
    private double yLoc;
    private ImageView view;
    private Point2D velocity;

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

    public void setViewImage( Image image){
        view.setImage(image);
    }

    //Structure variables
    private boolean isDestructible;

    //GUI variables
    private Image image;
    private int width;
    private int height;


    //methods
    public void updateLocation(){
        /* This method will be filled
        it will update the location of a gameobject
         */
    }

    public void updateObject(){
        /* This method will be filled
        it will update the current status of a gameobject
        e.g destroyed or alive
         */
    }

    //Setters and getters of variables
    public double getyLoc() {
        return yLoc;
    }

    public void setyLoc(double yLoc) {
        this.yLoc = yLoc;
    }

    public boolean isDestructible() {
        return isDestructible;
    }

    public void setDestructible(boolean destructible) {
        isDestructible = destructible;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getxLoc() {

        return xLoc;
    }


    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public void setxLoc(double xLoc) {
        this.xLoc = xLoc;
    }

    public void initView(){
        view = new ImageView( getImage());
    }

    public void draw(){
        view.setFitHeight(30);
        view.setFitWidth(30);
        view.setTranslateX( xLoc*VIEW_WH);
        view.setTranslateY( yLoc*VIEW_WH);
    }
}
