import javafx.scene.image.Image;

/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Tank extends GameObject {

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
        Bullet tankBullet = new Bullet( id, super.getxLoc()+(int)getImage().getHeight(), super.getyLoc()+(int)getImage().getWidth()/2, dir);
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
            super.setxLoc(super.getxLoc() + (int)getVelocity().getX());
            super.setImage( rightImage);
        }else if ( dir == 1){
            super.setxLoc(super.getxLoc() - (int)getVelocity().getX());
            super.setImage( leftImage);
        }else if ( dir == 2){
            super.setyLoc( super.getyLoc() + (int)getVelocity().getY());
            super.setImage( downImage);
        }else{
            super.setyLoc( super.getyLoc() - (int)getVelocity().getY());
            super.setImage( upImage);
        }
        updateView();
    }

    private void updateView(){
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
