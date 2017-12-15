package Management;

import GameObject.MapPackage.Map;
import GameObject.TankObjects.Bot;
import UserInterface.MenuPackage.PauseMenu;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class MapManager {
    private final int TILES = 20;
    Stage stage = new Stage();
    AnimationTimer timer;
    private int tileX, tileY;
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
    private GameStatus gameStatus;
    private Random rand = new Random();
    private PauseMenu pauseMenu;
    private boolean pauseCheck = false;
    private Text text;



    MapManager(int playerCount, int level) throws Exception {
        mapManagerFileManager = new FileManager();
        mapLevel = level;
        bots = new ArrayList<>();
        this.playerCount = playerCount;
        obstaclesMap = new int[TILES][TILES];
        readObstaclesMap(level);
        map = new Map(playerCount, level, obstaclesMap);
        text = new Text("Remaining Bots: " + map.getRemainingBots()
                + "\tLevel: " + level + "\nRemaining Health: "
                + map.getPlayer(0).getHealth() + "\tScore: (dir?)"
                + map.getPlayer(0).getHealth());
        gameStatus = GameStatus.GAME_RUNNING;
        mapFinished = false;
        startsLevel();
        start(stage);
        gameLoop();
        pauseMenu = new PauseMenu(this);
        inputController = new InputController( this, map.getPlayers());
    }

    public void start(Stage stage) throws Exception{
        this.stage = stage;
        text.setTranslateY(660);
        map.getMapPane().getChildren().addAll(text);
        stage.setScene(new Scene(map.getMapPane()));
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    onUpdate();
                    addBot();
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
    private void onUpdate(){
        collisionManager.checkCollision();
        updateAllObjects();
        collisionManager.updateRemovals();
        handleBots();
        updateStatText();
        if( map.isPaused()){
            System.out.println( map.isPaused());
            timer.stop();
            if( !pauseCheck) {
                pauseMenu.showPauseMenu();
                pauseCheck = true;
                gameStatus = GameStatus.GAME_PAUSED;
            }
            if( gameStatus == GameStatus.GAME_PAUSE_RETURN){
                gameStatus = GameStatus.GAME_RUNNING;
                pauseCheck = false;
            }

        }
        if( map.isGameOver()){
            System.out.print("Game over");
            timer.stop();
            gameStatus = GameStatus.GAME_OVER;
            stage.close();
        }
        if(map.isMapFinished()){
            System.out.print( "Level finished ");
            timer.stop();
            gameStatus = GameStatus.LEVEL_FINISHED;
            stage.close();
        }
    }

    private void onGameOver(){

    }

    public void startLoop(){
        map.setPaused(false);
        timer.start();
    }

    private void updateStatText(){
        if( playerCount == 2)
            text.setText("Remaining Bots: " + map.getRemainingBots() + "\t\t\t\t\t\tPlayer1 Health: " +
                    map.getPlayer(0).getHealth() + "\t\t\t\t\t\tPlayer1 Score: " +
                    map.getPlayer(0).getScore() +  "\nLevel: " + this.mapLevel
                    + "\t\t\t\t\t\t\t\tPlayer2 Health: " + map.getPlayer(1).getHealth() + "\t\t\t\t\t\tPlayer2 Score: " +
                    map.getPlayer(1).getScore());
        else if( playerCount == 1)
            text.setText("Remaining Bots: " + map.getRemainingBots() + "\t\t\t\t\t\t\t\tPlayer1 Health: " +
                    map.getPlayer(0).getHealth() + "\nLevel: " + this.mapLevel
                    + "\t\t\t\t\t\t\t\t\t\tPlayer1 Score: " + map.getPlayer(0).getScore());

    }

    public void updateAllObjects(){
        map.updateTanks();
        map.updateBullets();
        map.updateDestructibles();
        map.updateBonuses();
    }

    public void handleBots(){
        boolean changeDirStatus = false;
        for( Bot bot: map.getBots()){
            int prev_dir = bot.getDir();
            changeDirStatus = map.tryNextMove( bot, prev_dir);
            if( changeDirStatus){
                if( Math.random() < 0.008)
                    bot.setRandomDir();
                if( Math.random() < 0.005)
                    map.fire(bot);
                bot.move( bot.getDir());
            }else{
                bot.setRandomDir();
            }
        }
    }

    private void addBot(){
        if( Math.random() < 0.002 && map.getRemainingBots() > 0){
            map.spawnBot();
        }
    }

    private void addLifeBonus(long time) {
        int type = 0;
        // if type = 0 -> lifeBonus, if type = 1 -> speedBonus
        if (time % 2000 == 0)
            map.createBonus(type);
    }
    private void addSpeedBonus(long time) {
        int type = 1;
        if( time % 5000 == 0)
            map.createBonus(type);
    }

    public Stage getStage() {
        return stage;
    }

    public Pane getMapPane() {
        return map.getMapPane();
    }

    /* NEW METHOD TO CREATE OBJECT
       // obstacle id: 0 = Ground, 1 = GameObject.GameObject.MapPackage.ObstaclesObjects.Brick, 2 = GameObject.GameObject.MapPackage.ObstaclesObjects.Bush, 3 = GameObject.GameObject.MapPackage.ObstaclesObjects.IronWall,4 = GameObject.MapPackage.ObstaclesObjects.Water
    */

    private int[][] readObstaclesMap(int level){
        try {
            obstaclesMap = mapManagerFileManager.getMapLevelData(level);
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
        map = new Map(playerCount, mapLevel, readObstaclesMap(mapLevel));
        startsLevel();
        stage.setScene(new Scene(map.getMapPane()));
        System.out.println("updated: " + mapLevel);
        inputController = new InputController( this, map.getPlayers());
        map.getMapPane().getChildren().addAll(text);
        gameLoop();
    }
    private void startsLevel(){
        readObstaclesMap(mapLevel);
        collisionManager = new CollisionManager(map.getGameObjects(), map.getBullets(), map.getTanks(), map);
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

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus( GameStatus gameStatus){
        this.gameStatus = gameStatus;
    }
    // getter and setters
    public Map getMap() {
        return map;
    }
    public boolean isMapFinished() {
        return mapFinished;
    }

}