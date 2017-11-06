import javafx.scene.image.Image;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/*import java.util.ArrayList;

/**
 * Created by kaan on 10/28/2017.
 */
public class Player extends Tank {

    // variables
    private int score;
    private int controllerId;
    private Image rightImage;
    private Image upImage;
    private Image leftImage;
    private Image downImage;

    public Image getRightImage() {
        return rightImage;
    }

    public void setRightImage(Image rightImage) {
        this.rightImage = rightImage;
    }

    public Image getUpImage() {
        return upImage;
    }

    public void setUpImage(Image upImage) {
        this.upImage = upImage;
    }

    public Image getLeftImage() {
        return leftImage;
    }

    public void setLeftImage(Image leftImage) {
        this.leftImage = leftImage;
    }

    public Image getDownImage() {
        return downImage;
    }

    public void setDownImage(Image downImage) {
        this.downImage = downImage;
    }

    private ArrayList<String> controller;

    public Player(int id, int controllerId) {
        super.setVelocity( new Point2D.Double(0,0));
        super.setId(id);
        this.controllerId = controllerId;
    }


    @Override
    public void move(int dir) {
        if ( dir == 0){
            super.setxLoc(super.getxLoc() + (int)getVelocity().getX());
            super.setImage( rightImage);
        }else if ( dir == 1){
            super.setxLoc(super.getxLoc() - (int)getVelocity().getX());
            super.setImage( leftImage);
        }else if ( dir == 2){
            super.setyLoc( super.getyLoc() + (int)getVelocity().getY());
            super.setImage( downImage);
        }else{
            super.setyLoc( super.getyLoc() - (int)getVelocity().getY());
            super.setImage( upImage);
        }
        updateView();
    }

    private void updateView(){
        super.setViewImage(super.getImage());
    }


    private void incrementScore() {
        score++;
    }

    private ArrayList<String> getController() {
        return controller;
    }

    private void setController(ArrayList<String> controller) {
        this.controller = controller;
    }

}
