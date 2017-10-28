/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Destructible extends Obstacles{

    //Variables
    private static final int INITIAL_HEALTH = 600; //600 wil be changed
    private int health;

    //Methods

    //isDestructed Methods checks whether obstacles is destructed
    public boolean isDestructed(){
        return ( health <= 0);
    }
    //Setters and Getters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
