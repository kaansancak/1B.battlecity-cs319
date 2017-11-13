import sun.security.krb5.internal.crypto.Des;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CollisionManager {

    private final ArrayList<Tank> tanks;
    private final ArrayList<Bullet> bullets;
    private ArrayList<GameObject> gameObjects;

    public CollisionManager(ArrayList<GameObject> gameObjects,
                            ArrayList<Bullet> bullets, ArrayList<Tank> tanks){
        this.gameObjects  = gameObjects;
        this.bullets = bullets;
        this.tanks = tanks;
    }

    public void checkCollision(){
        checkObstacleCollision();
        checkTankCollision();
    }

    public void updateRemovals(){
        tanks.removeIf(Tank::isAlive);
        bullets.removeIf(Bullet::isCrushed);
    }

    public void checkObstacleCollision(){
        for ( Bullet bullet : bullets){
            for ( GameObject gameObject: gameObjects){
                if (bullet.getView().getBoundsInParent().intersects(
                        gameObject.getView().getBoundsInParent())
                        ) {
                    if( gameObject instanceof Destructible){
                        //damage( gameObject);
                        bullet.setCrushed(true);
                    }
                    else if ( gameObject instanceof Undestructible){
                        bullet.setCrushed(true);
                    }
                }
            }
        }
    }

    public void checkTankCollision(){
        for ( Bullet bullet : bullets){
            for ( Tank tank : tanks){
                if( bullet.getView().getBoundsInParent().
                        intersects( tank.getView().getBoundsInParent())){
                   // damage( tank);
                    bullet.setCrushed(true);
                }
            }
        }

    }

    public boolean isCollided(Bullet bulletObject){
        /*if( !isPassable((map[bulletObject.getxLoc()][bulletObject.getyLoc()]))
            && map[bulletObject.getxLoc()][bulletObject.getyLoc()] != null){
                bulletObject.setCrushed(true);
            return true;
        }*/
        return false;
    }
    // do we need another method such as TankPassable which return also false for water ?
    public boolean isPassable( GameObject gameObject){
        if( gameObject instanceof Brick || gameObject instanceof IronWall)
            return false;
        return true;
    }

    public boolean isDestructible(GameObject gameObject2){
        if( gameObject2 instanceof Tank || gameObject2 instanceof Destructible){
            return true;
        }
        return false;
    }

    //Damage the object if there is a collision
    public void damage(GameObject gameObject2){
        //Check whether collision occured is destructible
           if( isDestructible(gameObject2)){
               //If tank damage
               if( gameObject2 instanceof Tank){
                   ((Tank) gameObject2).getDamaged();
               //If destructible object damage
               }else if( gameObject2 instanceof Destructible){
                   ((Destructible) gameObject2).getDamaged();
               }
           }
    }

}
