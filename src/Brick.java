import javafx.scene.image.Image;

import java.nio.file.Paths;

/**
 * Created by kaan on 10/28/2017.
 */
public class Brick extends Destructible {

    private final String IMG_DIR = "./MediaFiles/image1.png";
    private final double center_shift = 0.5;//center shifts 0.25 when brick gets damaged.
    private final int NORMAL_DIMENSION = 30;
    private final int DAMAGED_DIMENSION = 15;
    //Variables
    private Image damagedImage;

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

    public void getDamaged( int dir){
        super.getDamaged(dir);
        setDamagedImage( dir);
    }



    public void setDamagedImage(int dir) {

        super.setDamaged( true);
        switch ( dir){
            case 0: {
                super.setxLoc( super.getxLoc() + center_shift);
                super.setVIEW_H(NORMAL_DIMENSION);
                super.setVIEW_V(DAMAGED_DIMENSION);
                break;
            }
            case 1: {
                super.setxLoc( super.getxLoc());
                super.setVIEW_H(NORMAL_DIMENSION);
                super.setVIEW_V(DAMAGED_DIMENSION);
                break;
            }
            case 2: {
                super.setyLoc( super.getyLoc() + center_shift);
                super.setVIEW_H(DAMAGED_DIMENSION);
                super.setVIEW_V(NORMAL_DIMENSION);
                break;
            }
            case 3: {
                super.setVIEW_H(DAMAGED_DIMENSION);
                super.setVIEW_V(NORMAL_DIMENSION);
                break;
            }
            default: super.getView().setRotate( super.getView().getRotate() - 90);
                     break;
        }
        super.setViewImage(super.getImage());


    }


}
