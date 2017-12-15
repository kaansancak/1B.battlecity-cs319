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
            if(event.getText().equals(player[0].getController().get(4))){
                map.fire(player[0]); //player 0 direction 0
            }
            if(player.length == 2 && (event.getText().equals(player[1].getController().get(4)))){
                map.fire(player[1]); //player 0 direction 0
            }
        });
    }

    @Override
    public void handle(KeyEvent e) {
        if(e.getText().toUpperCase().equals(player[0].getController().get(0))){
            movePlayer(player[0],0); //player 0 direction 0
        }
        else if(e.getText().toUpperCase().equals(player[0].getController().get(1))){
            movePlayer(player[0],1); //player 0 direction 0
        }
        else if(e.getText().toUpperCase().equals(player[0].getController().get(2))){
            movePlayer(player[0],2); //player 0 direction 0
        }
        else if(e.getText().toUpperCase().equals(player[0].getController().get(3))){
            movePlayer(player[0],3); //player 0 direction 0
        }
        else if(e.getCode() == KeyCode.P) {
            map.setPaused( true);
        }
        if(player.length == 2){
            if(e.getText().toUpperCase().equals(player[1].getController().get(0))){
                movePlayer(player[1],0); //player 0 direction 0
            }
            else if(e.getText().toUpperCase().equals(player[1].getController().get(1))){
                movePlayer(player[1],1); //player 0 direction 0
            }
            else if(e.getText().toUpperCase().equals(player[1].getController().get(2))){
                movePlayer(player[1],2); //player 0 direction 0
            }
            else if(e.getText().toUpperCase().equals(player[1].getController().get(3))){
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