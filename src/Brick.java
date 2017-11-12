import javafx.scene.image.Image;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by kaan on 10/28/2017.
 */
public class Brick extends Destructible {

    //Variables
    private Image damagedImage;
    private final String IMG_DIR = "./MediaFiles/image1.png";

    //Constructor
    public Brick( int xLoc, int yLoc){
        super( xLoc, yLoc);
        super.setImage( new Image(Paths.get(IMG_DIR).toUri().toString()));
        super.initView();
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

    public boolean isMovableTile(){
        return false;
    }
}
