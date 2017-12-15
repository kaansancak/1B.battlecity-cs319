package GameObject.TankObjects;

import GameObject.GameObject;
import javafx.scene.image.Image;

/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Tank extends GameObject {

    protected final int VIEW_WH = 32;
    private final double BULLET_SHIFT = 8;
    protected Image rightImage;
    protected Image upImage;
    protected Image leftImage;
    protected Image downImage;
    protected int dir;
    protected int health;
    protected int speed;
    protected int oldSpeed;
    protected int id;
    //Variables
    private int BULLET_DAMAGE = 200;
    private int type;

    @Override
    public void draw() {
        super.getView().setTranslateX( xLoc);
        super.getView().setTranslateY( yLoc);
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
        double bullet_x = getBullet_xLoc();
        double bullet_y = getBullet_yLoc();
        Bullet tankBullet = new Bullet( id, bullet_x, bullet_y, dir);
        return tankBullet;
    }

    private double getBullet_yLoc() {
        switch ( this.dir){
            case 0:
                return yLoc + view.getFitHeight()/2 - 5;
            case 1:
                return yLoc + view.getFitHeight()/2 - 5;
            case 2:
                return yLoc + view.getFitHeight() + 1;
            case 3:
                return yLoc - 11;
            default:
                break;
        }
        return yLoc;
    }

    private double getBullet_xLoc() {
        switch ( this.dir){
            case 0:
                return xLoc + view.getFitWidth() + 1;
            case 1:
                return xLoc - 11;
            case 2:
                return xLoc + view.getFitWidth()/2 - 5;
            case 3:
                return xLoc + view.getFitWidth()/2 - 5;
            default:
                break;
        }
        return xLoc;
    }

    public boolean isDead(){
        return ( health <= 0);
    }

    /* This method must be modified
    the direction which tank tries to move
    might be unpassable, so return type must be
    boolean but not void
     */

    public void move(int dir) {
        this.dir = dir;
        if ( dir == 0){
            xLoc =  xLoc + getVelocity().getX();
            super.setImage( rightImage);
        }else if ( dir == 1){
            xLoc = xLoc - getVelocity().getX();
            super.setImage( leftImage);
        }else if ( dir == 2){
            yLoc = yLoc + getVelocity().getY();
            super.setImage( downImage);
        }else if( dir == 3){
            yLoc = yLoc - getVelocity().getY();
            super.setImage( upImage);
        }
        updateView();
    }


    public void updateView(){
        super.setViewImage(super.getImage());
    }


    public void getDamaged(){
        health -= BULLET_DAMAGE;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getOldSpeed() {
        return oldSpeed;
    }

    public void setOldSpeed(int speed) {
        oldSpeed = speed;
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


    protected abstract void initImages();

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
