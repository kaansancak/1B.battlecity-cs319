package GameObject.MapPackage.ObstaclesObjects;

import GameObject.GameObject;

/**
 * Created by kaan on 10/28/2017.
 */
public abstract class Obstacles extends GameObject {

    //Variables
    // 0 = Ground, 1 = GameObject.GameObject.MapPackage.ObstaclesObjects.Brick, 2 = GameObject.GameObject.MapPackage.ObstaclesObjects.Bush, 3 = GameObject.GameObject.MapPackage.ObstaclesObjects.IronWall, 4 = GameObject.MapPackage.ObstaclesObjects.Water, 5 = Mini GameObject.GameObject.MapPackage.ObstaclesObjects.Brick, 6 = Small GameObject.GameObject.MapPackage.ObstaclesObjects.Brick
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
