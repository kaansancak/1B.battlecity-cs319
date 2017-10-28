/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Tank extends GameObject {

    //Variables

    private int type;
    private int id;
    private int health;
    private int dir;

    //Methods
    public void createBullet(){
        Bullet tankBullet = new Bullet( id, dir, super.getxLoc(), super.getyLoc());
    }

    public boolean isAlive(){
        return ( health <= 0);
    }

    /* This method must be modified
    the direction which tank tries to move
    might be unpassable, so return type must be
    boolean but not void
     */
    public boolean move(){
        if( dir == 0){
            super.setxLoc( super.getxLoc() + 1);
        }else
            super.setyLoc( super.getyLoc() +1 );
        return true;
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
}
