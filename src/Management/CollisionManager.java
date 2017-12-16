package Management;

import GameObject.GameObject;
import GameObject.MapPackage.ObstaclesObjects.Destructible;
import GameObject.MapPackage.ObstaclesObjects.Statue;
import GameObject.MapPackage.ObstaclesObjects.Undestructible;
import GameObject.TankObjects.Bot;
import GameObject.TankObjects.Bullet;
import GameObject.TankObjects.Player;
import GameObject.TankObjects.Tank;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.ArrayList;

public class CollisionManager {

    private ArrayList<Tank> tanks;
    private ArrayList<Bullet> bullets;
    private ArrayList<GameObject> gameObjects;

    public CollisionManager(ArrayList<GameObject> gameObjects,
                            ArrayList<Bullet> bullets, ArrayList<Tank> tanks) {

        this.gameObjects = gameObjects;
        this.bullets = bullets;
        this.tanks = tanks;
    }

    public void checkCollision() {
        checkBulletCollision();
    }

    public void updateRemovals() {
        tanks.removeIf(Tank::isDead);
        bullets.removeIf(Bullet::isDestructed);
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
                        if( gameObject instanceof Bot) {
                            MediaPlayer player = new MediaPlayer(new Media(Paths.get("MediaFiles/tankDestroyed.mp3").toUri().toString()));
                            player.play();
                            incrementScore( bullet.getId());
                        }
                        if( gameObject instanceof Player) {
                            if( bullet.getId() == 0 || bullet.getId() == 1)
                                continue;
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

    private void incrementScore(int id) {
        for( Tank tank: tanks){
            if( tank instanceof Player){
                if(tank.getId() == id){
                    ((Player) tank).incrementScore();
                }
            }
        }
    }

    //Damage the object if there is a collision
    public void damage(GameObject gameObject2, int dir) {
        if (gameObject2 instanceof Tank) {
            ((Tank) gameObject2).getDamaged();
            //If destructible object damage
        } else if (gameObject2 instanceof Destructible) {
            if( gameObject2 instanceof Statue){
                ((Statue) gameObject2).getDamaged();
            }else if (gameObject2.isDamaged() == false) {
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
