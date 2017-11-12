import javafx.scene.image.Image;

import java.awt.geom.Point2D;

/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Tank extends GameObject {

    private final int VIEW_WH = 25;
    //Variables
    private int BULLET_DAMAGE = 200;
    private int type;
    private int id;
    private int health;
    private int dir;


    private Image rightImage;
    private Image upImage;
    private Image leftImage;
    private Image downImage;

    public Tank(int xLoc, int yLoc) {
        super(xLoc, yLoc);
    }

    public Tank() {
        super(0,0);
    }

    @Override
    public void draw() {
        super.getView().setFitWidth(VIEW_WH);
        super.getView().setFitHeight(VIEW_WH);
        super.getView().setTranslateX( super.getxLoc()*VIEW_WH);
        super.getView().setTranslateY( super.getyLoc()*VIEW_WH);
    }

    public Image getRightImage() {
        return rightImage;
    }

    public void setRightImage(Image rightImage) {
        this.rightImage = rightImage;
    }

    public Image getUpImage() {
        return upImage;
    }

    public void setUpImage(Image upImage) {
        this.upImage = upImage;
    }

    public Image getLeftImage() {
        return leftImage;
    }

    public void setLeftImage(Image leftImage) {
        this.leftImage = leftImage;
    }

    public Image getDownImage() {
        return downImage;
    }

    public void setDownImage(Image downImage) {
        this.downImage = downImage;
    }

    //Methods
    public Bullet fire(){
        Bullet tankBullet = new Bullet( id, super.getxLoc()+getImage().getHeight(), super.getyLoc()+getImage().getWidth()/2, dir);
        return tankBullet;
    }

    public boolean isAlive(){
        return ( health <= 0);
    }

    /* This method must be modified
    the direction which tank tries to move
    might be unpassable, so return type must be
    boolean but not void
     */

    public void move(int dir) {
        if ( dir == 0){
            super.setxLoc(super.getxLoc() + getVelocity().getX());
            super.setImage( rightImage);
        }else if ( dir == 1){
            super.setxLoc(super.getxLoc() - getVelocity().getX());
            super.setImage( leftImage);
        }else if ( dir == 2){
            super.setyLoc( super.getyLoc() + getVelocity().getY());
            super.setImage( downImage);
        }else{
            super.setyLoc( super.getyLoc() - getVelocity().getY());
            super.setImage( upImage);
        }
        updateView();
    }


    public void updateView(){
        super.setViewImage(super.getImage());
    }



    public void getDamaged(){
        health = health - BULLET_DAMAGE;
    }

    //Setters and Getters
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public boolean isMovableTile(){
        return false;
    }
}
