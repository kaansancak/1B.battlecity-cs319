import javafx.scene.image.Image;

/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Tank extends GameObject {

    protected final int VIEW_WH = 32;
    protected Image rightImage;
    protected Image upImage;
    protected Image leftImage;
    protected Image downImage;
    protected int dir;
    protected int health;
    protected int speed;
    protected int oldSpeed;
    //Variables
    private int BULLET_DAMAGE = 200;
    private int type;
    private int id;

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
        Bullet tankBullet = new Bullet( id, xLoc, yLoc, dir);
        return tankBullet;
    }

    public boolean isAlive(){
        return ( health >= 0);
    }

    /* This method must be modified
    the direction which tank tries to move
    might be unpassable, so return type must be
    boolean but not void
     */

    public void move(int dir) {
        if ( dir == 0){
            xLoc =  xLoc + getVelocity().getX();
            super.setImage( rightImage);
        }else if ( dir == 1){
            xLoc = xLoc - getVelocity().getX();
            super.setImage( leftImage);
        }else if ( dir == 2){
            yLoc = yLoc + getVelocity().getY();
            super.setImage( downImage);
        }else{
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

    public boolean isMovableTile(){
        return false;
    }
}
