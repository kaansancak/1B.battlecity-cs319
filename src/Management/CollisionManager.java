package Management;

import GameObject.GameObject;
import GameObject.MapPackage.Map;
import GameObject.MapPackage.ObstaclesObjects.Brick;
import GameObject.MapPackage.ObstaclesObjects.Destructible;
import GameObject.MapPackage.ObstaclesObjects.IronWall;
import GameObject.MapPackage.ObstaclesObjects.Undestructible;
import GameObject.TankObjects.Bot;
import GameObject.TankObjects.Bullet;
import GameObject.TankObjects.Player;
import GameObject.TankObjects.Tank;

import java.util.ArrayList;

public class CollisionManager {

    private ArrayList<Tank> tanks;
    private ArrayList<Bullet> bullets;
    private ArrayList<GameObject> gameObjects;
    private Player[] players;

    public CollisionManager(ArrayList<GameObject> gameObjects,
                            ArrayList<Bullet> bullets, ArrayList<Tank> tanks, Map map) {
        this.gameObjects = gameObjects;
        players = map.getPlayers();
        this.bullets = bullets;
        this.tanks = tanks;
    }

    public void checkCollision() {
        checkBulletCollision();
    }

    public void updateRemovals() {
        tanks.removeIf(Tank::isDead);
        bullets.removeIf(Bullet::isCrushed);
        gameObjects.removeIf(GameObject::isDestructed);
    }

    public void checkBulletCollision() {
        for (Bullet bullet : bullets) {
            for (GameObject gameObject : gameObjects) {
                if (bullet.getView().getBoundsInParent().intersects(gameObject.getView().getBoundsInParent())) {
                    if( gameObject instanceof Tank){
                        if ( gameObject instanceof Bot &&
                                bullet.getId() == 99){
                            continue;
                        }
                        if( gameObject instanceof Bot && bullet.getId() == 0) {
                            players[0].incrementScore();
                        }
                        else if( gameObject instanceof Bot && bullet.getId() == 1) {
                            players[1].incrementScore();
                        }
                        damageTank( (Tank)gameObject);
                        bullet.setCrushed( true);
                    }
                    if (gameObject instanceof Destructible) {
                        damage(gameObject, bullet.getDir());
                        bullet.setCrushed(true);
                    } else if (gameObject instanceof Undestructible) {
                        if ((gameObject.isPassableByBullets()))
                            bullet.setCrushed(false);
                        else
                            bullet.setCrushed(true);
                    }
                }
            }
        }
    }


    public void checkTankCollision() {
        for (Bullet bullet : bullets) {
            for (Tank tank : tanks) {
                if (bullet.getView().getBoundsInParent().
                        intersects(tank.getView().getBoundsInParent())) {
                    System.out.print("col");
                    damageTank(tank);
                    bullet.setCrushed(true);
                }
            }
        }

    }

    public boolean isCollided(Bullet bulletObject) {
        /*if( !isPassable((map[bulletObject.getxLoc()][bulletObject.getyLoc()]))
            && map[bulletObject.getxLoc()][bulletObject.getyLoc()] != null){
                bulletObject.setCrushed(true);
            return true;
        }*/
        return false;
    }

    // do we need another method such as TankPassable which return also false for water ?
    public boolean isPassable(GameObject gameObject) {
        return !(gameObject instanceof Brick || gameObject instanceof IronWall);
    }

    public boolean isDestructible(GameObject gameObject2) {
        return gameObject2 instanceof Tank || gameObject2 instanceof Destructible;
    }

    //Damage the object if there is a collision
    public void damage(GameObject gameObject2, int dir) {
        if (gameObject2 instanceof Tank) {
            ((Tank) gameObject2).getDamaged();
            //If destructible object damage
        } else if (gameObject2 instanceof Destructible) {
            if (gameObject2.isDamaged() == false) {
                ((Destructible) gameObject2).getDamaged(dir);
                (gameObject2).setDamaged(true);
            }else{
                ( gameObject2).setDestructed(true);
            }
        }
    }

    public  void damageTank( Tank tank){
        tank.getDamaged();
    }
}
