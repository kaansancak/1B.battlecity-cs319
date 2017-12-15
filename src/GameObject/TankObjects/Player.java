package GameObject.TankObjects;

import javafx.animation.AnimationTimer;
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

    public double oldSpeed;
    AnimationTimer timer;
    // variables
    private double PLAYER_NORMAL_VELOCITY = 3.0;
    private int score;
    private int controllerId;
    private ArrayList<String> controller;

    public Player(int id, int controllerId) {
        if( id == 1){
            this.xLoc = 4*30;
            this.yLoc = 4*30;
        }else{
            this.xLoc = 5*28;
            this.yLoc = 5*28;
        }
        health = 200;
        initImages();
        view = new ImageView( rightImage);
        view.setFitWidth( 28);
        view.setFitHeight( 28);
        super.setVelocity( new Point2D.Double(PLAYER_NORMAL_VELOCITY, PLAYER_NORMAL_VELOCITY));
        super.setId(id);
        this.controllerId = controllerId;
    }

    protected void initImages() {
        rightImage = ( new Image(Paths.get("."+"/MediaFiles/resources/tank_player1_right.png").toUri().toString()));
        leftImage = ( new Image(Paths.get("."+"/MediaFiles/resources/tank_player1_left.png").toUri().toString()));
        upImage = ( new Image(Paths.get("."+"/MediaFiles/resources/tank_player1_up.png").toUri().toString()));
        downImage = ( new Image(Paths.get("."+"/MediaFiles/resources/tank_player1_down.png").toUri().toString()));
    }

    public void incrementHealth() {
        health += 200;
    }
    public void incrementSpeed() {
        oldSpeed = PLAYER_NORMAL_VELOCITY;
        PLAYER_NORMAL_VELOCITY = PLAYER_NORMAL_VELOCITY + 2.0;
        super.setVelocity( new Point2D.Double(PLAYER_NORMAL_VELOCITY, PLAYER_NORMAL_VELOCITY));

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                normalizeTheSpeed( now, oldSpeed);
            }
        };
        timer.start();
        PLAYER_NORMAL_VELOCITY = oldSpeed;
    }
    private void normalizeTheSpeed(long now, double oldSpeed) {
        if( now % 500 == 0) {
            super.setVelocity( new Point2D.Double(oldSpeed, oldSpeed));
            PLAYER_NORMAL_VELOCITY = oldSpeed;
        }
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

    @Override
    public boolean isPassableByTanks() {
        return false;
    }

    @Override
    public boolean isPassableByBullets() {
        return false;
    }

    @Override
    public boolean isHideable() {
        return false;
    }
}

