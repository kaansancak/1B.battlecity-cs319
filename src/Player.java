import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;
import java.nio.file.Paths;
import java.util.ArrayList;

/*import java.util.ArrayList;

/**
 * Created by kaan on 10/28/2017.
 */
public class Player extends Tank {

    // variables
    private int score;
    private int controllerId;


    private ArrayList<String> controller;

    public Player(int id, int controllerId) {
        health = 200;
        initImages();
        super.setImage( super.getRightImage() );
        super.setView( new ImageView(super.getImage()));
        super.setVelocity( new Point2D.Double(0.1, 0.1));
        super.setId(id);
        this.controllerId = controllerId;
    }

    protected void initImages() {
        super.setRightImage( new Image(Paths.get("."+"/MediaFiles/resources/tank_player1_right.png").toUri().toString()));
        super.setLeftImage( new Image(Paths.get("."+"/MediaFiles/resources/tank_player1_left.png").toUri().toString()));
        super.setUpImage( new Image(Paths.get("."+"/MediaFiles/resources/tank_player1_up.png").toUri().toString()));
        super.setDownImage( new Image(Paths.get("."+"/MediaFiles/resources/tank_player1_down.png").toUri().toString()));
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

