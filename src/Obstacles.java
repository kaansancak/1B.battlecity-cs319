/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Obstacles extends GameObject {

    //Variables
    // 0 = Ground, 1 = Brick, 2 = Bush, 4 = IronWall, 5 = Water
    private int typeId;

    //Methods

    //Setters and Getters

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
