import java.awt.geom.Point2D;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bot extends Tank {

    public Bot( int xLoc, int yLoc){
        super.setVelocity( new Point2D.Double(1,1));
        super.setDir(getRandomDir());
        super.setxLoc( xLoc);
        super.setyLoc( yLoc);
    }

    public boolean isStuck(){
        return false;
    }
    /*
    This method must be modified according to description
    in the design report.
     */
    public void runBot( boolean changeDirStatus){
        if( false) {
            super.setDir(getRandomDir()%4);
        }
        else if( true){
            move(super.getDir());
        }
    }
    private int getRandomDir(){
        return (int)( Math.random()%4);
    }

    public boolean isMovableTile(){
        return false;
    }

}
