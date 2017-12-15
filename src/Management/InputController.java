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
    private KeyCode left;
    private KeyCode up;
    private KeyCode right;
    private KeyCode down;
    private KeyCode fire;


    public InputController(MapManager mapManager, Player player){
        this.mapScene = mapManager.getStage().getScene();
        this.map = mapManager.getMap();
        this.player = player;
        pauseMenu = new PauseMenu(mapManager);
        mapScene.setOnKeyPressed( this);
        mapScene.setOnKeyReleased( event -> {
            if(event.getCode() == getFire()){
                map.fire(player); //player 0 direction 0
            }
        });
        left = KeyCode.A;
        up = KeyCode.W;
        right = KeyCode.D;
        down = KeyCode.S;
        fire = KeyCode.SPACE;
    }

    @Override
    public void handle(KeyEvent e) {
        if(e.getCode() == getRight()){
            movePlayer(player,0); //player 0 direction 0
        }
        else if(e.getCode() == getLeft()){
            movePlayer(player,1); //player 0 direction 0
        }
        else if(e.getCode() == getDown()){
            movePlayer(player,2); //player 0 direction 0
        }
        else if(e.getCode() == getUp()){
            movePlayer(player,3); //player 0 direction 0
        }
        else if(e.getCode() == KeyCode.P) {
            pauseMenu.showPauseMenu();
        }
    }

    public void setLeft( KeyCode left) {
        this.left = left;
    }
    public void setUp( KeyCode up) {
        this.up = up;
    }
    public void setRight( KeyCode right) {
        this.right = right;
    }
    public void setDown( KeyCode down) {
        this.down = down;
    }
    public void setFire( KeyCode fire) {
        this.fire = fire;
    }
    public KeyCode getLeft() {
        return left;
    }
    public KeyCode getUp() {
        return up;
    }
    public KeyCode getRight() {
        return right;
    }
    public KeyCode getDown() {
        return down;
    }
    public KeyCode getFire() {
        return fire;
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
