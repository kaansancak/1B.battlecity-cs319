/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Destructible extends Obstacles{

    //Variables
    private final int BULLET_DAMAGE = 200;
    private static final int INITIAL_HEALTH = 600; //600 wil be changed
    private int health;

    public Destructible(int xLoc, int yLoc) {
        super(xLoc, yLoc);
    }

    //Methods

    //isDestructed Methods checks whether obstacles is destructed
    public boolean isDestructed(){
        return ( health <= 0);
    }
    //Setters and Getters
    public int getHealth() {
        return health;
    }

    public void getDamaged(){
        health = health - BULLET_DAMAGE;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
