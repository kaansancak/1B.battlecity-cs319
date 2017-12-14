import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MapManager {

    private final int TILES = 20;
    Stage stage = new Stage();
    AnimationTimer timer;
    private int tileX, tileY;
    private boolean gameStatus;
    private int playerCount;
    private int mapLevel;
    private Map map;
    private boolean mapFinished;
    private FileManager mapManagerFileManager;
    private int[][] obstaclesMap;
    private CollisionManager collisionManager;
    private ArrayList<Bot> bots;
    private InputController inputController;
    private boolean paused = false;



    MapManager(int playerCount, int level) throws Exception {
        mapManagerFileManager = new FileManager();
        mapLevel = level;
        bots = new ArrayList<>();
        this.playerCount = playerCount;
        obstaclesMap = new int[TILES][TILES];
        readObstaclesMap();
        map = new Map(playerCount, level, obstaclesMap);
        gameStatus = true;
        mapFinished = false;
        startsLevel();
        start(stage);
        gameLoop();
        inputController = new InputController( this, map.getPlayer(0));
    }

    public void start(Stage stage) throws Exception{
        this.stage = stage;
        stage.setScene(new Scene(map.getMapPane()));

            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    onUpdate();
                    addBot(now);
                    addLifeBonus(now);
                    addSpeedBonus(now);
                }
            };
            timer.start();

    }

    public boolean isPaused() {
        return paused;
    }

    /*
        boolean should be send to move method
        of bots so that it could not move at that moment
        and the bonus releases should stop
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
        if( paused == true)
            timer.stop();
        timer.start();
    }

    private void onUpdate(){
        collisionManager.checkCollision();
        updateAllObjects();
        collisionManager.updateRemovals();
        handleBots();
    }

    public void updateAllObjects(){
        map.updateTanks();
        map.updateBullets();
        map.updateDestructibles();
        map.updateBonuses();
    }

    public void handleBots(){
        for( Bot bot: map.getBots()){
            bot.runBot( map.tryNextMove(bot,bot.getDir()));
        }

    }

    private void addBot( long time){
        if( time % 100 == 0 && map.getRemainingBots() > 0){
            map.spawnBot();
        }
    }

    private void addLifeBonus(long time) {
        int type = 0;
        // if type = 0 -> lifeBonus, if type = 1 -> speedBonus
        if (time % 150 == 0)
            map.newBonus(type);
    }
    private void addSpeedBonus(long time) {
        int type = 1;
        if( time % 200 == 0)
            map.newBonus(type);
    }

    public Stage getStage() {
        return stage;
    }

    public Pane getMapPane() {
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
        for(int i = 0; i < map.getBullets().size() ; i++){
                collisionManager.checkCollision();
            }
    }

    private void updateMapObjects(){

    }

    private void updateMap(){
        mapLevel++;
        map = new Map(playerCount, mapLevel, readObstaclesMap());
        startsLevel();
    }
    private void startsLevel(){
        readObstaclesMap();
        collisionManager = new CollisionManager(map.getGameObjects(), map.getBullets(), map.getTanks());
        map.addObjects(map.getGameObjectsArray());
    }
    private boolean stopGameLoop(){
        return isMapFinished();
    }
    private void gameLoop(){
        if(!stopGameLoop()){
            mapFinished = (map.getRemainingBots()==0) && (map.getAliveBots()==0);
            map.updateObjects();
            stage.getScene();
            stage.show();
        }
        else{
            finishLevel();
        }

    }
    private void finishLevel(){
        map.finishMap();
    }

    // getter and setters
    public Map getMap() {
        return map;
    }
    public boolean isMapFinished() {
        return mapFinished;
    }

}