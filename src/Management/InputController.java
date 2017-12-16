package Management;

import GameObject.MapPackage.Map;
import GameObject.TankObjects.Player;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Created by kaan on 11/12/2017.
 */
public class InputController implements EventHandler<KeyEvent> {

    private Player player[];
    private Scene mapScene;
    private Map map;


    public InputController(MapManager mapManager, Player[] player){
        this.mapScene = mapManager.getStage().getScene();
        this.map = mapManager.getMap();
        this.player = player;
        mapScene.setOnKeyPressed( this);
        mapScene.setOnKeyReleased( event -> {
            if(event.getCode() == KeyCode.SPACE && !(player[0].isDead())){
                map.fire(player[0]); //player 0 direction 0
            }
            if(player.length == 2 && (event.getCode() == KeyCode.SHIFT && !(player[1].isDead()))){
                map.fire(player[1]); //player 0 direction 0
            }
        });
    }

    @Override
    public void handle(KeyEvent e) {
        if(e.getCode() == KeyCode.D){
            movePlayer(player[0],0); //player 0 direction 0
        }
        else if(e.getCode() == KeyCode.A){
            movePlayer(player[0],1); //player 0 direction 0
        }
        else if(e.getCode() == KeyCode.S){
            movePlayer(player[0],2); //player 0 direction 0
        }
        else if(e.getCode() == KeyCode.W){
            movePlayer(player[0],3); //player 0 direction 0
        }
        else if(e.getCode() == KeyCode.P) {
            map.setPaused( true);
        }
        if(player.length == 2){
            if(e.getCode() == KeyCode.RIGHT){
                movePlayer(player[1],0); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.LEFT){
                movePlayer(player[1],1); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.DOWN){
                movePlayer(player[1],2); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.UP){
                movePlayer(player[1],3); //player 0 direction 0
            }

        }
    }

    public Stage getMapStage() {
        return map.getMapStage();
    }

    private void movePlayer(Player player, int dir){
        int oldDir = player.getDir();
        map.tryNextMove( player, oldDir);
        player.move(dir);

    }

}