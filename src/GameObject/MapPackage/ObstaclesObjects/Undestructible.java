package GameObject.MapPackage.ObstaclesObjects;

/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Undestructible extends Obstacles {

    //Variables
    private int typeId2;

    //Methods

    public abstract  boolean isPassableByBullets();

    public boolean isHidable(){
        return typeId2 == 1;
    }

    //Setters and getters
    public int getTypeId2() {
        return typeId2;
    }

    public void setTypeId2(int typeId2) {
        this.typeId2 = typeId2;
    }
}
