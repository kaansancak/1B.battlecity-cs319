import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;

/**
 * Created by kaan on 10/28/2017.
 */
public class GameObject {

    //Variables
    private final int BULLET_DAMAGE = 200;
    protected int VIEW_WH = 32;
    //Location variables
    protected double xLoc;
    protected double yLoc;
    private int VIEW_H = 30;
    private int VIEW_V = 30;
    private boolean isDamaged = false;
    private boolean isDestructed = false;
    private ImageView view;
    private Point2D velocity;
    //Structure variables
    private boolean isDestructible;
    //GUI variables
    private Image image;
    private int width;
    private int height;
    public GameObject(double xLoc, double yLoc){
        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }

    public void setVIEW_WH(int VIEW_WH) {
        this.VIEW_WH = VIEW_WH;
    }

    public void setVIEW_H(int VIEW_H) {
        this.VIEW_H = VIEW_H;
    }

    public void setVIEW_V(int VIEW_V) {
        this.VIEW_V = VIEW_V;
    }

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

    public void setViewImage( Image image){
        view.setImage(image);
    }

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

    public void draw() {
        view.setFitHeight(VIEW_H);
        view.setFitWidth(VIEW_V);
        view.setTranslateX( xLoc*VIEW_WH);
        view.setTranslateY( yLoc*VIEW_WH);
    }


}
