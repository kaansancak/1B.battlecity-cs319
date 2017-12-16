package GameObject.MapPackage.TilePackage;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;

/**
 * Created by kaan on 12/16/2017.
 */
public class Portal extends  Tile{

    private final String ACTIVE_IMG_DIR =  "./MediaFiles/portal.png";
    private final String DEACTIVE_IMG_DIR = "./MediaFiles/portal1.png";
    private Image activeImage;
    private Image deactiveImage;
    private AnimationTimer timer;
    private boolean activation = true;
    private boolean isPassed = false;
    public Portal( double xLoc, double yLoc) {
        super( xLoc, yLoc);
        initImages();
        view = new ImageView(activeImage);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                setActivation( now);
            }
        };
        timer.start();
    }


    public void initImages(){
        activeImage = new Image(Paths.get(ACTIVE_IMG_DIR).toUri().toString());
        deactiveImage = new Image(Paths.get(DEACTIVE_IMG_DIR).toUri().toString());
    }
    @Override
    public boolean isPassableByTanks() {
        return true;
    }

    @Override
    public boolean isPassableByBullets() {
        return true;
    }

    @Override
    public boolean isHideable() {
        return false;
    }

    @Override
    public void draw() {
        super.getView().setTranslateX( xLoc);
        super.getView().setTranslateY( yLoc);
    }

    public void setPassed( boolean passed){
        isPassed = passed;
    }

    public boolean getActivation(){
        return activation;
    }

    private void setActivation(long time) {
        if( !isPassed) {
            if (time % 400 == 0) {
                if (activation == false) {
                    view.setImage(activeImage);
                    activation = true;
                } else if( activation == true){
                    activation = false;
                    view.setImage(deactiveImage);
                }
            }
        }else if( isPassed){
                view.setImage(deactiveImage);
                activation = false;
            if( time % 400 == 0){
                isPassed = false;
                view.setImage(activeImage);
                activation = true;
            }
        }
        draw();
    }

}
