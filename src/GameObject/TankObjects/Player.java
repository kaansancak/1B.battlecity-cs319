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

    // variables
    private final double PLAYER_1_START_X = 224.0;
    private final double PLAYER_2_START_X = 352.0;
    private final double PLAYER_1_START_Y = 544.0;
    private final double PLAYER_2_START_Y = 544.0;
    private final double PLAYER_NORMAL_VELOCITY = 4.0;
    private final double PLAYER_BONUS_VELOCITY = 6.0;
    private final int PLAYER_DIMENSION = 28;
    private final int PLAYER_HEALTH = 200;
    private final int PLAYER_START_DIR = 3;
    private AnimationTimer timer;
    private int score;
    private int remainingLife = 4;
    private ArrayList<String> controller;

    public Player(int id, int controllerId) {
        this.id = id;
        if( id == 0){
            this.xLoc = PLAYER_1_START_X;
            this.yLoc = PLAYER_1_START_Y;
        }else{
            this.xLoc = PLAYER_2_START_X;
            this.yLoc = PLAYER_1_START_Y;
        }
        this.dir = PLAYER_START_DIR;
        health = PLAYER_HEALTH;
        score = 0;
        initImages();
        view = new ImageView( upImage);
        view.setFitWidth( PLAYER_DIMENSION);
        view.setFitHeight( PLAYER_DIMENSION);
        super.setVelocity( new Point2D.Double(PLAYER_NORMAL_VELOCITY, PLAYER_NORMAL_VELOCITY));
    }

    protected void initImages() {
        rightImage = ( new Image(Paths.get("./MediaFiles/resources/tank_player1_right.png").toUri().toString()));
        leftImage = ( new Image(Paths.get("./MediaFiles/resources/tank_player1_left.png").toUri().toString()));
        upImage = ( new Image(Paths.get("./MediaFiles/resources/tank_player1_up.png").toUri().toString()));
        downImage = ( new Image(Paths.get("./MediaFiles/resources/tank_player1_down.png").toUri().toString()));
    }

    public void setStartCondition(){
        health = PLAYER_HEALTH;
        if( id == 0){
            this.xLoc = PLAYER_1_START_X;
            this.yLoc = PLAYER_1_START_Y;
        }else{
            this.xLoc = PLAYER_2_START_X;
            this.yLoc = PLAYER_2_START_Y;
        }
        view.setImage( upImage);
    }
    public boolean isLifeOver(){
        return remainingLife <= 0;
    }

    public int getRemainingLife(){
        return remainingLife;
    }

    public void decrementLife(){
        remainingLife--;
    }

    public void incrementHealth() {
        health += PLAYER_HEALTH;
    }
    public void incrementLife() {
        remainingLife++;
    }
    public void incrementSpeed() {
        super.setVelocity( new Point2D.Double(PLAYER_BONUS_VELOCITY, PLAYER_BONUS_VELOCITY));

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                normalizeTheSpeed( now);
            }
        };
        timer.start();

    }
    private void normalizeTheSpeed(long now) {
        if( now % 500 == 0) {
            super.setVelocity( new Point2D.Double(PLAYER_NORMAL_VELOCITY, PLAYER_NORMAL_VELOCITY));
        }
    }
    public void incrementScore() {
        score += 100;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

