import javafx.application.Application;
import javafx.scene.image.Image;

/**
 * Created by kaan on 10/28/2017.
 */
public abstract class GameObject {

    //Variables
    private final int BULLET_DAMAGE = 200;
    //Location variables
    private int xLoc;
    private int yLoc;

    //Structure variables
    private boolean isDestructible;

    //GUI variables
    private Image image;
    private int width;
    private int height;
//

    //methods
    public void updateLocation(){
        /* This method will be filled
        it will update the location of a gameobject
         */
    };
    public void updateObject(){
        /* This method will be filled
        it will update the current status of a gameobject
        e.g destroyed or alive
         */
    };

    //Setters and getters of variables
    public int getyLoc() {
        return yLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    public boolean isDestructible() {
        return isDestructible;
    }

    public void setDestructible(boolean destructible) {
        isDestructible = destructible;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getxLoc() {

        return xLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }
}
