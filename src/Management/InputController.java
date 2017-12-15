package Management;

import GameObject.MapPackage.Map;
import GameObject.TankObjects.Player;
import UserInterface.MenuPackage.PauseMenu;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Created by kaan on 11/12/2017.
 */
public class InputController implements EventHandler<KeyEvent> {

    private Player player;
    private Scene mapScene;
    private Map map;
    private PauseMenu pauseMenu;


    public InputController(MapManager mapManager, Player player){
        this.mapScene = mapManager.getStage().getScene();
        this.map = mapManager.getMap();
        this.player = player;
        pauseMenu = new PauseMenu(mapManager);
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
        else if(e.getCode() == KeyCode.P) {
            pauseMenu.showPauseMenu();
        }

    }

    public Stage getMapStage() {
        return map.getMapStage();
    }

    private void movePlayer(Player player, int dir){
        int oldDir = player.getDir();
        player.move(dir);
        map.tryNextMove( player, oldDir);
    }

}
