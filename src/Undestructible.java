/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Undestructible extends Obstacles {

    //Variables
    private int typeId2;

    public Undestructible(int xLoc, int yLoc) {
        super(xLoc, yLoc);
    }

    //Methods
    public boolean isPassableByTanks(){
        if( typeId2 == 0)
            return true;
        return false;
    }

    public boolean isPassableByBullets(){
        if( typeId2 == 0)
            return false;
        return true;
    }

    public boolean isHidable(){
        if( typeId2 == 1)
            return true;
        else
            return false;
    }

    //Setters and getters
    public int getTypeId2() {
        return typeId2;
    }

    public void setTypeId2(int typeId2) {
        this.typeId2 = typeId2;
    }
}
