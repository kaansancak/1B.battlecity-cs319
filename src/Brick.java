import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by kaan on 10/28/2017.
 */
public class Brick extends Destructible {

    //Variables
    private Image damagedImage;

    //Constructor
    public Brick( int xLoc, int yLoc){
        super.setImage(super.getImage());
        super.setxLoc(xLoc);
        super.setyLoc(yLoc);
    }

    //Methods
    public void getDestructed(){
        if( super.isDestructed()){
            //if destructed clear the object
        }
    }

    public void getDamaged(){
        if( super.getHealth() < 600){
            super.setImage(damagedImage);
        }
    }
}
