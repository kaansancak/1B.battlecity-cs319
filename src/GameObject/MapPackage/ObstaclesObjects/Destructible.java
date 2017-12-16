package GameObject.MapPackage.ObstaclesObjects;

/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Destructible extends Obstacles {

    //Variables
    private final int BULLET_DAMAGE = 200;
    protected int health;

    //Methods

    //Setters and Getters
    public void getDamaged( int dir){
        health = health - BULLET_DAMAGE;
    }
}
