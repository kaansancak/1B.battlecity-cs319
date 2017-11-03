import java.io.FileNotFoundException;

public class MapManager {
    private final int TILES = 20;
    private boolean gameStatus;
    private int mapLevel;
    private Map map;
    private boolean mapFinished;
    private FileManager mapManagerFileManager;
    private int[][] obstaclesMap;
    private CollisionManager collisionManager;
    private GameObject[][] gameObjects;

    MapManager(){

    }
    MapManager(int level){
        map = new Map(mapLevel);

        obstaclesMap = new int[TILES][TILES];
        gameObjects = new GameObject[TILES][TILES];
        gameStatus = true;
        mapFinished = false;
        mapLevel = level;
        readObstaclesMap();
        collisionManager = new CollisionManager(gameObjects);

    }

    private void readObstaclesMap(){
        try {
            obstaclesMap = mapManagerFileManager.getMapLevelData(mapLevel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    //private GameObject[] manageObjects(CollisionManager collisionManager, Map map){}
    private void updateMapObjects(Map map){}
    private void updateMap(){}
    private void startsLevel(int level){}
    private void stopGameLoop(){}
    private void gameLoop(){}
   // private boolean isMapFinished(){}
    private void finishLevel(){}

    public boolean isGameStatus() {
        return gameStatus;
    }


    // getter and setters
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
