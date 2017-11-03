/**
 * Created by kaan on 10/28/2017.
 */
public class Bot extends Tank {

    public Bot( int xLoc, int yLoc){
        super.setxLoc( xLoc);
        super.setyLoc( yLoc);
    }

    public boolean isStuck(){
        return !super.move();
    }
    /*
    This method must be modified according to description
    in the design report.
     */

    public void startBot(){

    }
    public boolean isMonotone(){
        return  true;
    }

    public boolean isMovableTile(){
        return false;
    }

}
