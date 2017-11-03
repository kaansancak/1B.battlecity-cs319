import javafx.stage.Stage;

/**
 * Created by kaan on 10/28/2017.
 */
public class Bot extends Tank {

    public Bot( int xLoc, int yLoc){
        super.setxLoc( xLoc);
        super.setyLoc( yLoc);
    }

    public boolean isStuck(){
        if( super.move())
            return false;
        return  true;
    }

    /*
    This method must be modified according to description
    in the design report.
     */
    public boolean isMonotone(){
        return  true;
    }


}
