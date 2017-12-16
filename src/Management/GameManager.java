package Management;

import GameObject.TankObjects.Player;
import UserInterface.Frame.GameViewFrame;
import UserInterface.SettingsPackage.Settings;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class GameManager  {

    private final int GAME_START_LEVEL = 1;
    private final int GAME_FINAL_LEVEL = 2;
    private MapManager mapManager;
    private GameViewFrame gameViewFrame;
    private FileManager gameManagerFileManager;
    private Settings inputController;
    private int highestScore;
    private Player[] players;
    private int[] currentScores;
    private boolean gameRunning;
    private boolean gamePaused;
    private int currentGameLevel;
    private boolean gameFinished;
    private int gameCompletedLevels;
    private Button pauseButton;
    private Stage gameManager;
    private int player_count;
    private AnimationTimer timer;

    GameManager(){}

    public GameManager(int player_count){
        this.player_count = player_count;
        players = new Player[player_count];
        currentScores = new int[player_count];
        gameManagerFileManager = new FileManager();

        gameRunning = true;
        gamePaused = false;
        currentGameLevel = GAME_START_LEVEL;
        startMapManager();
        gameFinished = false;
        gameCompletedLevels = 0;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };

        timer.start();
    }

    private void onUpdate() {
        if( currentGameLevel > GAME_FINAL_LEVEL){
            gameViewFrame = new GameViewFrame(this, 1);
            gameViewFrame.showGameView();
            //Show game finished view
        }
        else if( mapManager.getGameStatus() == GameStatus.GAME_OVER){
            gameViewFrame = new GameViewFrame(this, 0);
            gameViewFrame.showGameView();
            //Show game over view
        }else if( mapManager.getGameStatus() == GameStatus.LEVEL_FINISHED){
            timer.stop();
            gameViewFrame = new GameViewFrame(this, 2);
            gameViewFrame.showGameView();
        }
    }

    public void initiateNextLevel() {
            currentGameLevel++;
            try {
                mapManager = new MapManager( player_count, currentGameLevel);
            }catch ( Exception e){
                e.printStackTrace();
            }
            timer.start();
    }


    private void startMapManager() {
        try{mapManager = new MapManager(player_count, currentGameLevel);} catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start (Stage stage) throws Exception{
        mapManager.start(stage);
        gameManager = stage;
    }

    private boolean isGameOver(){
        return gameCompletedLevels == GAME_FINAL_LEVEL;
    }
    private void updateCurrentScore(int player_id){}
    private void updateHighestScore(int highestScore){}
    private void startGame(){}
    private void finishGame(){}
    private boolean isGameRunning(){
        return gameRunning;
    }
    public boolean isGamePaused(){
        return gamePaused;
    }

    private void continueGame(){
        gamePaused = false;
        gameRunning = true;
    }

    public int getLevel(){
        return currentGameLevel;
    }

    public void closeActiveMapManager(){
        mapManager.getStage().close();
    }

    public void stopLoop(){
        timer.stop();
    }

    private void quitGame(){}
    private void saveHighestScore(int scoregame){ if(scoregame>highestScore)
                                                    { highestScore = scoregame; }}
    private int getHighestScore(){ return highestScore; }
    private boolean isGameFinished(){ return !gameRunning; }
    private void startLevel(MapManager mapManager ,int nextLevel){}
    private void finishLevel(MapManager mapmManager){}

}