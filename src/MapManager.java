import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MapManager {
    private final int TILES = 20;
    private int tileX, tileY;
    private boolean gameStatus;
    private int mapLevel;
    private Map map;
    private boolean mapFinished;
    private FileManager mapManagerFileManager;
    private int[][] obstaclesMap;
    private CollisionManager collisionManager;

    MapManager(){

    }
    MapManager(int level){
        obstaclesMap = new int[TILES][TILES];
        readObstaclesMap();
        map = new Map(level, obstaclesMap);
        gameStatus = true;
        mapFinished = false;
        mapLevel = level;
        getImages();
        startsLevel();
        gameLoop();
    }

    /* NEW METHOD TO CREATE OBJECTS
       // obstacle id: 0 = Ground, 1 = Brick, 2 = Bush, 3 = IronWall,4 = Water
    */

    private int[][] readObstaclesMap(){
        try {
            obstaclesMap = mapManagerFileManager.getMapLevelData(mapLevel);
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

    private void updateMap(){
        mapLevel++;
        map = new Map(mapLevel, readObstaclesMap());
        startsLevel();
    }
    private void startsLevel(){
        readObstaclesMap();
        collisionManager = new CollisionManager(map.getGameObjects());
        map.addObjects(map.getGameObjects());
    }
    private boolean stopGameLoop(){
        if(isMapFinished()){ // user input to stop loop for pause screen
            return true;
        }
        return false;
    }
    private void gameLoop(){
        if(!stopGameLoop()){
            manageObjects();
            updateMapObjects();
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