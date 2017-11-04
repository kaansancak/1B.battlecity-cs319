import javafx.scene.layout.Pane;

import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MapManager {
    private final int TILES = 20;
    private int tileX, tileY;
    private boolean gameStatus;
    private int playerCount;
    private int mapLevel;
    private Map map;
    private boolean mapFinished;
    private FileManager mapManagerFileManager;
    private int[][] obstaclesMap;
    private CollisionManager collisionManager;
    Stage stage = new Stage();

    public void start(Stage stage) throws Exception{
        this.stage = stage;
        stage.setScene(new Scene(map.getMapPane()));
        stage.getScene().setOnKeyPressed( (KeyEvent e) ->{
            if(e.getCode() == KeyCode.A){
                movePlayer(map.getPlayer(0),0); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.W){
                movePlayer(map.getPlayer(0),1); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.D){
                movePlayer(map.getPlayer(0),2); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.S){
                movePlayer(map.getPlayer(0),3); //player 0 direction 0
            }
            if(e.getCode() == KeyCode.LEFT){
                movePlayer(map.getPlayer(1),0); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.UP){
                movePlayer(map.getPlayer(1),1); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.RIGHT){
                movePlayer(map.getPlayer(1),2); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.DOWN){
                movePlayer(map.getPlayer(1),3); //player 0 direction 0
            }
            if(e.getCode() == KeyCode.SPACE){
                map.getPlayer(0).fire(); //player 0 direction 0
            }
            if(e.getCode() == KeyCode.SHIFT){
                map.getPlayer(1).fire(); //player 0 direction 0
            }
        });


    }

    MapManager(){

    }
    MapManager(int playerCount, int level){
        mapManagerFileManager = new FileManager();
        mapLevel = level;
        this.playerCount = playerCount;
        obstaclesMap = new int[TILES][TILES];
        readObstaclesMap();
        map = new Map(playerCount, level, obstaclesMap);
        getImages();
        gameStatus = true;
        mapFinished = false;
        map.intToObject();
        startsLevel();
        gameLoop();
    }

    public void movePlayer( Player player, int dir){
        int newX = player.getxLoc();
        int newY = player.getyLoc();
        switch ( dir){
            case 0: newX++;
            case 1: newX--;
            case 2: newY++;
            case 3: newY--;
        }
        if( map.isPassableTile( newX, newY)){
            player.move(dir);
        }
    }

public Pane getMapPane(){
        return map.getMapPane();
}

    /* NEW METHOD TO CREATE OBJECT
       // obstacle id: 0 = Ground, 1 = Brick, 2 = Bush, 3 = IronWall,4 = Water
    */

    private int[][] readObstaclesMap(){
        try {
            obstaclesMap = mapManagerFileManager.getMapLevelData(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return obstaclesMap;
    }
    private void manageObjects(){
        for(int i = 0; i < TILES ; i++){
            for(int j = 0; j < TILES; j++) {
                if (map.getGameObjects()[i][j] instanceof Bullet){
                    collisionManager.checkCollision((Bullet)map.getGameObjects()[i][j]);
                }
            }
        }
    }

    private void updateMapObjects(){

    }

    private void handleBots(){

    }

    private void updateMap(){
        mapLevel++;
        map = new Map(playerCount, mapLevel, readObstaclesMap());
        startsLevel();
    }
    private void startsLevel(){
        readObstaclesMap();
        collisionManager = new CollisionManager(map.getGameObjects());
        map.addObjects(map.getGameObjects());
    }
    private boolean stopGameLoop(){
        return isMapFinished();
    }
    private void gameLoop(){
        if(!stopGameLoop()){
            updateMapObjects();
            stage.setScene(new Scene(map.getMapPane()));
            gameLoop();
        }
        else{
            finishLevel();
        }

    }
    private void finishLevel(){
        map.finishMap();
    }
    private void getImages(){
        try {
            map.setImages(mapManagerFileManager.getScannedImages());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // getter and setters
    public boolean isGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getMapLevel() {
        return mapLevel;
    }

    public void setMapLevel(int mapLevel) {
        this.mapLevel = mapLevel;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean isMapFinished() {
        return mapFinished;
    }

    public void setMapFinished(boolean mapFinished) {
        this.mapFinished = mapFinished;
    }

    public FileManager getMapManagerFileManager() {
        return mapManagerFileManager;
    }

    public void setMapManagerFileManager(FileManager mapManagerFileManager) {
        this.mapManagerFileManager = mapManagerFileManager;
    }

    public int[][] getObstaclesMap() {
        return obstaclesMap;
    }

    public void setObstaclesMap(int[][] obstaclesMap) {
        this.obstaclesMap = obstaclesMap;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public void setCollisionManager(CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
    }
}