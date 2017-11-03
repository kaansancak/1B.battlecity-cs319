import javafx.stage.Stage;

/**
 * Created by kaan on 10/28/2017.
 */
public class IronWall extends Undestructible{

    //Constructor
    public IronWall( int xLoc, int yLoc){
        super.setxLoc( xLoc);
        super.setyLoc( yLoc);
    }

    public boolean isMovableTile(){
        return false;
    }
}
//