import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
    private ArrayList<Bot> bots;
    Stage stage = new Stage();

    public void start(Stage stage) throws Exception{
        this.stage = stage;
        stage.setScene(new Scene(map.getMapPane()));

        for( Player player : map.getPlayers()){
            player.setVelocity(new Point(2,2));
        }
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

    }

    private void onUpdate(){
        listenKeys();
        checkBots();
        map.updatePlayers();
        map.updateBots();
        map.updateBullets();

        if(Math.random() < 0.001 && map.getRemainingBots() > 0){
            addBot();
            System.out.println("hola");
        }
    }

    public void checkBots(){
        for(Bot bot : map.getBots()){
            int newX = bot.getxLoc();
            int newY = bot.getyLoc();
            switch (bot.getDir()) {
                case 0:
                    newX++;
                case 1:
                    newX--;
                case 2:
                    newY++;
                case 3:
                    newY--;
            }
            if (map.isMoveableLoc(newX, newY)) {
                bot.move(bot.getDir());
            }
            else{
                bot.runBot(true); // bot is stuck
            }
        }
    }

    public void addBot(){
        Bot temp = new Bot((int)(Math.abs(Math.random()*640)), (int)(map.getMapPane().getPrefHeight()-32-Math.random()*150));
        temp.runBot(true);
        bots.add(temp);
        map.initBot(temp);
    }

    private void listenKeys() {
        stage.getScene().setOnKeyPressed( (KeyEvent e) ->{
            if(e.getCode() == KeyCode.D){
                movePlayer(map.getPlayer(0),0); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.A){
                movePlayer(map.getPlayer(0),1); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.S){
                movePlayer(map.getPlayer(0),2); //player 0 direction 0
            }
            else if(e.getCode() == KeyCode.W){
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
                map.fire(map.getPlayer(0)); //player 0 direction 0
            }
            if(e.getCode() == KeyCode.SHIFT){
                map.fire(map.getPlayer(1)); //player 0 direction 0
            }
        });


    }

    MapManager(){

    }

    MapManager(int playerCount, int level) throws Exception {
        mapManagerFileManager = new FileManager();
        mapLevel = level;
        bots = new ArrayList<>();
        this.playerCount = playerCount;
        obstaclesMap = new int[TILES][TILES];
        readObstaclesMap();
        map = new Map(playerCount, level, obstaclesMap);
        getImages();
        gameStatus = true;
        mapFinished = false;
        map.intToObject();
        map.initPlayers();
        startsLevel();
        start(stage);
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
        if( map.isMoveableLoc( map.getMovedView( newX, newY))){
            player.move(dir);
            player.setDir(dir);
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
        for(int i = 0; i < map.getBullets().size() ; i++){
            {
                    collisionManager.checkCollision(map.getBullets().get(i));
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