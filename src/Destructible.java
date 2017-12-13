/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Destructible extends Obstacles{

    private static final int INITIAL_HEALTH = 600; //600 wil be changed
    //Variables
    private final int BULLET_DAMAGE = 200;
    private int health;


    protected Destructible(int xLoc, int yLoc) {
        super(xLoc, yLoc);
    }

    //Methods

    //Setters and Getters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void getDamaged( int dir){
        health = health - BULLET_DAMAGE;
    }
}
