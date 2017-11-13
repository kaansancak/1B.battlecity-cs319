import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Created by kaan on 11/12/2017.
 */
public class InputManager implements EventHandler<KeyEvent> {

    private Player player;
    private Scene mapScene;
    private Map map;


    public InputManager(MapManager mapManager, Player player){
        this.mapScene = mapManager.getStage().getScene();
        this.map = mapManager.getMap();
        this.player = player;
        mapScene.setOnKeyPressed( this);
        mapScene.setOnKeyReleased( event -> {
            if(event.getCode() == KeyCode.SPACE){
                map.fire(player); //player 0 direction 0
            }
        });
    }

    @Override
    public void handle(KeyEvent e) {
        if(e.getCode() == KeyCode.D){
            movePlayer(player,0); //player 0 direction 0
        }
        else if(e.getCode() == KeyCode.A){
            movePlayer(player,1); //player 0 direction 0
        }
        else if(e.getCode() == KeyCode.S){
            movePlayer(player,2); //player 0 direction 0
        }
        else if(e.getCode() == KeyCode.W){
            movePlayer(player,3); //player 0 direction 0
        }

    }

    public void movePlayer( Player player, int dir){
        double newX = player.getxLoc();
        double newY = player.getyLoc();
        switch ( dir){
            case 0: newX += player.getVelocity().getX();
            case 1: newX -= player.getVelocity().getX();
            case 2: newY += player.getVelocity().getY();
            case 3: newY -= player.getVelocity().getY();
        }
        if( map.tryNextMove( newX, newY, player.getView())){
            player.setDir(dir);
            player.move(dir);
        }
    }

}
