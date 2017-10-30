import sun.security.krb5.internal.crypto.Des;

public class CollusionManager {

    private boolean objectsCollided;

    GameObject[][] map;

    public CollusionManager( GameObject[][] map){
        this.map = map;
    }


    public void checkCollision(Bullet bulletObject){
        while ( !bulletObject.isCrushed()){
            isCollided(bulletObject);
        }
        GameObject crushedObject =  map[bulletObject.getxLoc()][bulletObject.getyLoc()];
        damage(crushedObject);
    }

    public boolean isCollided(Bullet bulletObject){
        if( !isPassable((map[bulletObject.getxLoc()][bulletObject.getyLoc()]))
            && map[bulletObject.getxLoc()][bulletObject.getyLoc()] != null){
                bulletObject.setCrushed(true);
            return true;
        }
        return false;
    }

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