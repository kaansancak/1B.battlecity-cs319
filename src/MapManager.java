import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

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
    private GameObject[][] gameObjects;
    private TilePane tilePane;
    private ImageView temp;

    private ArrayList<Image> images;


    MapManager(){

    }
    MapManager(int level){
        tilePane = new TilePane();
        obstaclesMap = new int[TILES][TILES];
        map = new Map(level, obstaclesMap);
        gameObjects = new GameObject[TILES][TILES];
        gameStatus = true;
        mapFinished = false;
        mapLevel = level;
        tileX = (int) tilePane.getTileWidth();
        tileY = (int) tilePane.getTileHeight();
        map.setWidth((int)tilePane.getWidth());
        map.setHeight((int)tilePane.getHeight());
        images = new ArrayList<>();
        getImages();
        startsLevel();
        gameLoop();
    }

    /* NEW METHOD TO CREATE OBJECTS
       // obstacle id: 0 = Ground, 1 = Brick, 2 = Bush, 3 = IronWall,4 = Water
    */
    private void intToObject(){
        for(int i = 0; i < TILES; i++){
            for(int j = 0; j < TILES; j++) {
                if(obstaclesMap[i][j] == 0){
                    gameObjects[i][j] = null;// ground
                }
                else {
                    if (obstaclesMap[i][j] == 1) {
                        gameObjects[i][j] = new Brick(i * tileX, j * tileY);
                        gameObjects[i][j].setImage(images.get(1));
                    } else if (obstaclesMap[i][j] == 2) {
                        gameObjects[i][j] = new Bush(i * tileX, j * tileY);
                        gameObjects[i][j].setImage(images.get(2));
                    } else if (obstaclesMap[i][j] == 3) {
                        gameObjects[i][j] = new IronWall(i * tileX, j * tileY);
                        gameObjects[i][j].setImage(images.get(3));
                    } else if (obstaclesMap[i][j] == 4) {
                        gameObjects[i][j] = new Water(i * tileX, j * tileY);
                        gameObjects[i][j].setImage(images.get(4));
                    }

                    temp = new ImageView(gameObjects[i][j].getImage());
                    temp.relocate(i * tileY, j * tileY);
                    tilePane.getChildren().addAll(temp);
                }
            }
        }

    }

    private void readObstaclesMap(){
        try {
            obstaclesMap = mapManagerFileManager.getMapLevelData(mapLevel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void manageObjects(){
        for(int i = 0; i < TILES ; i++){
            for(int j = 0; j < TILES; j++) {
                if (gameObjects[i][j] instanceof Bullet){
                    collisionManager.checkCollision((Bullet)gameObjects[i][j]);
                }
            }
        }
    }

    private void updateMapObjects(Map map){
        map.addObjects(gameObjects);
    }

    private void updateMap(){
        map = new Map(mapLevel);
        startsLevel();
    }
    private void startsLevel(){
        readObstaclesMap();
        intToObject();
        collisionManager = new CollisionManager(gameObjects);
        map.addObjects(gameObjects);
    }
    private boolean stopGameLoop(){
        return isMapFinished();
    }
    private void gameLoop(){
        if(!stopGameLoop()){
            manageObjects();
            updateMapObjects(map);
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
            images = mapManagerFileManager.getScannedImages();
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
